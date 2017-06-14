package com.lantushenko.experimental.stub.web.serialize.json;

import com.lantushenko.experimental.stub.dao.Station;

public class StationDto extends AbstractDto{
    private Integer code;

    private String name;

    public StationDto(Station station){
        super.setId(station.getId());
        code = station.getCode();
        name = station.getName();
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
