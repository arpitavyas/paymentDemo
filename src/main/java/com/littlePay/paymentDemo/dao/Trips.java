package com.littlePay.paymentDemo.dao;

import java.util.Date;

public class Trips {

    Date Started;
    Date Finished;
    long DurationSecs;
    String FromStopId;
    String ToStopId;
    float ChargeAmount;
    String CompanyId;
    String BusID;
    String PAN;
    String Status;

    public Trips() {
    }

    public Trips(Date started, Date finished, long durationSecs, String fromStopId, String toStopId, float chargeAmount, String companyId, String busID, String PAN, String status) {
        Started = started;
        Finished = finished;
        DurationSecs = durationSecs;
        FromStopId = fromStopId;
        ToStopId = toStopId;
        ChargeAmount = chargeAmount;
        CompanyId = companyId;
        BusID = busID;
        this.PAN = PAN;
        Status = status;
    }

    public Date getStarted() {
        return Started;
    }

    public void setStarted(Date started) {
        Started = started;
    }

    public Date getFinished() {
        return Finished;
    }

    public void setFinished(Date finished) {
        Finished = finished;
    }

    public long getDurationSecs() {
        return DurationSecs;
    }

    public void setDurationSecs(long durationSecs) {
        DurationSecs = durationSecs;
    }

    public String getFromStopId() {
        return FromStopId;
    }

    public void setFromStopId(String fromStopId) {
        FromStopId = fromStopId;
    }

    public String getToStopId() {
        return ToStopId;
    }

    public void setToStopId(String toStopId) {
        ToStopId = toStopId;
    }

    public float getChargeAmount() {
        return ChargeAmount;
    }

    public void setChargeAmount(float chargeAmount) {
        ChargeAmount = chargeAmount;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getBusID() {
        return BusID;
    }

    public void setBusID(String busID) {
        BusID = busID;
    }

    public String getPAN() {
        return PAN;
    }

    public void setPAN(String PAN) {
        this.PAN = PAN;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
