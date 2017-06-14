package com.lantushenko.experimental.stub.repositories;

import com.lantushenko.experimental.stub.dao.FieldDescription;
import com.lantushenko.experimental.stub.dao.Station;

import java.util.List;

public interface MetadataRepository {
    List<Station> listStations();
    List<FieldDescription> listFieldDescriptions();
}
