package com.lantushenko.experimental.stub.dao.impl;

public class StationImpl extends AbstractEntityImpl<StationImpl> implements com.lantushenko.experimental.stub.dao.Station {

    private Integer code;
    private String name;

    public StationImpl() {
        super(StationImpl.class);
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
