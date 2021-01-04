package com.littlePay.paymentDemo.helper;

import com.littlePay.paymentDemo.dao.Taps;
import com.littlePay.paymentDemo.dao.Trips;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvHelper {

    public static String TYPE = "text/csv";

    //generate trips.csv file
    public static ByteArrayInputStream tripsToCSV(List<Trips> trips) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        SimpleDateFormat targetDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            List<? extends Serializable> header = Arrays.asList(
                    "Started", "Finished", "DurationSecs", "FromStopId", "ToStopId", "ChargeAmount", "CompanyId", "BusID", "PAN", "Status");
            csvPrinter.printRecord(header);

            for (Trips trip : trips) {
                List<? extends Serializable> data = Arrays.asList(
                        trip.getStarted()!=null?targetDateFormat.format(trip.getStarted()):trip.getStarted(),
                        trip.getFinished()!=null?targetDateFormat.format(trip.getFinished()):trip.getFinished(),
                        String.valueOf(trip.getDurationSecs()),
                        trip.getFromStopId(),
                        trip.getToStopId(),
                        trip.getChargeAmount(),
                        trip.getCompanyId(),
                        trip.getBusID(),
                        trip.getPAN(),
                        trip.getStatus());

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }


    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    //read records from taps.csv file
    public static List<Taps> csvToTaps(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Taps> taps = new ArrayList();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Taps tap = new Taps(
                        Integer.parseInt(csvRecord.get("ID")),
                        new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(csvRecord.get("DateTimeUTC")),
                        csvRecord.get("TapType"),
                        csvRecord.get("StopId"),
                        csvRecord.get("CompanyId"),
                        csvRecord.get("BusId"),
                        csvRecord.get("PAN")
                );
                taps.add(tap);
            }
            return taps;

        } catch (Exception e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}