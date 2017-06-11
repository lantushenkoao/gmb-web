package com.lantushenko.experimental.stub.web.serialize.json;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.ALWAYS)
public class AjaxStatusDto {

    private boolean isOk;
    private String message;

    public AjaxStatusDto() {
    }

    public AjaxStatusDto(boolean isOk, String message) {
        this.isOk = isOk;
        this.message = message;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean isOk) {
        this.isOk = isOk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
