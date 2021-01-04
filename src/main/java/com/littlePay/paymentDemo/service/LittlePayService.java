package com.littlePay.paymentDemo.service;

import com.littlePay.paymentDemo.dao.Taps;
import com.littlePay.paymentDemo.dao.Trips;
import com.littlePay.paymentDemo.helper.CsvHelper;
import com.littlePay.paymentDemo.model.TransportCostModel;
import com.littlePay.paymentDemo.repository.TransportCostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class LittlePayService {

    @Autowired
    TransportCostRepository transportCostRepository;

    public ByteArrayInputStream loadTapsAndGenerateTrips(InputStream inputStream) {
        List<Taps> taps = CsvHelper.csvToTaps(inputStream);
        List<Trips> tripsList = generateTripsFromTaps(taps);
        ByteArrayInputStream in = CsvHelper.tripsToCSV(tripsList);
        return in;
    }

    private List<Trips> generateTripsFromTaps(List<Taps> taps) {
        List<Trips> trips =new ArrayList<>();
        Iterator<Taps> it = taps.iterator();

        while ( it.hasNext() ) {
            Taps taps1 = it.next();
            if (taps1.getTapType().equals("ON")) {

                int flag =0;

                for (int i = 0; i < taps.size(); i++) {
                    if (taps.get(i).getTapType().equals("OFF") &&
                        taps1.getBusId().equals(taps.get(i).getBusId()) &&
                        taps1.getPan().equals(taps.get(i).getPan()) &&
                        (taps.get(i).getDateTimeUTC().after(taps1.getDateTimeUTC()) || taps.get(i).getDateTimeUTC().equals(taps1.getDateTimeUTC()))
                       ) {

                        Trips t = new Trips();
                        t.setStarted(taps1.getDateTimeUTC());
                        t.setFinished(taps.get(i).getDateTimeUTC());
                        long duration = (Math.abs(taps.get(i).getDateTimeUTC().getTime()-taps1.getDateTimeUTC().getTime())/1000);
                        t.setDurationSecs(duration);
                        t.setFromStopId(taps1.getStopId());
                        t.setToStopId(taps.get(i).getStopId());
                        t.setCompanyId(taps1.getCompanyId());
                        t.setBusID(taps1.getBusId());
                        t.setPAN(taps1.getPan());
                        if (taps1.getStopId().equals(taps.get(i).getStopId())) {
                            t.setStatus("Cancelled");
                            t.setChargeAmount(0.0f);
                        } else {
                            t.setStatus("Complete");
                            t.setChargeAmount(calculateChargeAmount(taps1.getStopId(), taps.get(i).getStopId()));
                        }
                        flag = 1;
                        trips.add(t);
                        it.remove();
                        break;
                    }
                }

                if (flag == 0 ) {
                    Trips t = new Trips();
                    t.setStarted(taps1.getDateTimeUTC());
                    t.setFinished(null);
                    t.setDurationSecs(0);
                    t.setFromStopId(taps1.getStopId());
                    t.setToStopId(null);
                    t.setCompanyId(taps1.getCompanyId());
                    t.setBusID(taps1.getBusId());
                    t.setPAN(taps1.getPan());
                    t.setChargeAmount(calculateChargeAmount(taps1.getStopId(), null));
                    t.setStatus("InComplete");
                    trips.add(t);
                    it.remove();
                }
            }

        }
        return trips;
    }

    public float calculateChargeAmount(String stopA, String stopB) {
            if (stopB == null) {
                return transportCostRepository.getTransportCostByPairNameMax(stopA);
            } else {
                TransportCostModel model = transportCostRepository.findTransportCostByPairName(stopA+"-"+stopB);
                return  model.getPairCost();
            }
    }

}
