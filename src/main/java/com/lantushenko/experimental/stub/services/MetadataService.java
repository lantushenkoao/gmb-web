package com.lantushenko.experimental.stub.services;

import com.lantushenko.experimental.stub.dao.FieldDescription;
import com.lantushenko.experimental.stub.dao.Station;

import java.util.List;

public interface MetadataService {
    List<Station> listStations();

    List<FieldDescription> listFieldDescriptions();
}
