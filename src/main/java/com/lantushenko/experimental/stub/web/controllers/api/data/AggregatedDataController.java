package com.lantushenko.experimental.stub.web.controllers.api.data;

import com.lantushenko.experimental.stub.services.AggregatedDataService;
import com.lantushenko.experimental.stub.web.serialize.json.ChartDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = {"/api/data/aggregated"})
public class AggregatedDataController {

    @Autowired
    private AggregatedDataService aggregatedDataService;

    @RequestMapping(value = "avg", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public List<ChartDataDto> load(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date periodStart,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date periodEnd,
            @RequestParam String tableName,
            @RequestParam String columnName,
            @RequestParam Integer bucketsCount,
            @RequestParam List<String> stationCodes

    ){
        return aggregatedDataService.load(periodStart, periodEnd, tableName, columnName, stationCodes, bucketsCount);
    }
}
