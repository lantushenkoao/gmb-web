package com.lantushenko.experimental.stub.web.serialize.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

@JsonSerialize
public class ChartDataDto {
    private Integer stationCode;
    private String stationName;
    private Date bucketStart;
    private Date buckedEnd;
    private Double data;

    public ChartDataDto(Integer stationCode, String stationName, Date bucketStart, Date buckedEnd, Double data) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.bucketStart = bucketStart;
        this.buckedEnd = buckedEnd;
        this.data = data;
    }

    public Integer getStationCode() {
        return stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public Date getBucketStart() {
        return bucketStart;
    }

    public Date getBuckedEnd() {
        return buckedEnd;
    }

    public Double getData() {
        return data;
    }
}
