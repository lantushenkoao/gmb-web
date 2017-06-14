package com.lantushenko.experimental.stub.services.impl;

import com.lantushenko.experimental.stub.dao.FieldDescription;
import com.lantushenko.experimental.stub.dao.Station;
import com.lantushenko.experimental.stub.repositories.MetadataRepository;
import com.lantushenko.experimental.stub.services.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value = "stationService")
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional(readOnly = true)
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    private MetadataRepository metadataRepository;

    @Override
    public List<Station> listStations(){
        return metadataRepository.listStations();
    }

    @Override
    public List<FieldDescription> listFieldDescriptions(){
        return metadataRepository.listFieldDescriptions();
    }
}
