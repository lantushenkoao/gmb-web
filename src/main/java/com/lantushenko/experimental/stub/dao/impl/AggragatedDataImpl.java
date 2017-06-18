package com.lantushenko.experimental.stub.dao.impl;

import com.lantushenko.experimental.stub.dao.Station;

import java.util.Date;

public class AggragatedDataImpl implements com.lantushenko.experimental.stub.dao.AggragatedData {

    private Double avgData;
    private Date periodStart;
    private Date periodEnd;
    private Station station;

    @Override
    public Double getAvgData() {
        return avgData;
    }

    public void setAvgData(Double avgData) {
        this.avgData = avgData;
    }

    @Override
    public Date getPeriodStart() {
        return periodStart;
    }

    @Override
    public Date getPeriodEnd() {
        return periodEnd;
    }

    @Override
    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
