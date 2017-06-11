package com.lantushenko.experimental.stub.web.serialize.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Arrays;
import java.util.List;


@JsonSerialize
public class ErrorDto {

    private final List<String> errorMessages;

    public ErrorDto(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public ErrorDto(String... errorMessages) {
        this.errorMessages = Arrays.asList(errorMessages);
    }

    @JsonProperty
    public List<String> getErrorMessages() {
        return errorMessages;
    }
}
