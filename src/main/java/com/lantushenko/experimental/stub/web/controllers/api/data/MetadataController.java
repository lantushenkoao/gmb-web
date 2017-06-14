package com.lantushenko.experimental.stub.web.controllers.api.data;

import com.lantushenko.experimental.stub.dao.FieldDescription;
import com.lantushenko.experimental.stub.services.MetadataService;
import com.lantushenko.experimental.stub.web.serialize.json.FieldDescriptionDto;
import com.lantushenko.experimental.stub.web.serialize.json.StationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = {"/api/data/metadata"})
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @RequestMapping(value = "stations", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<StationDto> listStations() {
        return metadataService.listStations().stream()
                .map(StationDto::new)
                .collect(Collectors.toList());
    }


    @RequestMapping(value = "fielddescriptions", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<FieldDescriptionDto> listFieldDescriptions() {
        return metadataService.listFieldDescriptions().stream()
                .map(FieldDescriptionDto::new)
                .collect(Collectors.toList());
    }
}
