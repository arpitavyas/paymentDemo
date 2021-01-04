package com.littlePay.paymentDemo.dao;

import java.util.Date;

public class Taps {

    int id;
    Date DateTimeUTC;
    String tapType;
    String stopId;
    String companyId;
    String busId;
    String pan;

    String status ;

    public Taps(int id, Date dateTimeUTC, String tapType, String stopId, String companyId, String busId, String pan) {
        this.id = id;
        DateTimeUTC = dateTimeUTC;
        this.tapType = tapType;
        this.stopId = stopId;
        this.companyId = companyId;
        this.busId = busId;
        this.pan = pan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateTimeUTC() {
        return DateTimeUTC;
    }

    public void setDateTimeUTC(Date dateTimeUTC) {
        DateTimeUTC = dateTimeUTC;
    }

    public String getTapType() {
        return tapType;
    }

    public void setTapType(String tapType) {
        this.tapType = tapType;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
