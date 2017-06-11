package com.lantushenko.experimental.stub.web.serialize.json;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * Base class for all DAO objects serialized to json
 */
@JsonSerialize
public abstract class AbstractDto {

    private Object id;

    @JsonProperty("id")
    public String getId() {
        return id.toString();
    }

    @JsonProperty("id")
    public void setId(Object value) {
        id = value;
    }
}
