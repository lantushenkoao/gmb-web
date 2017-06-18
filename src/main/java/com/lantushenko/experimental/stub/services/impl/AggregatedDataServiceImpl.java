package com.lantushenko.experimental.stub.services.impl;

import com.lantushenko.experimental.stub.repositories.AggregatedDataRepository;
import com.lantushenko.experimental.stub.web.serialize.json.ChartDataDto;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "aggregatedDataService")
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional(readOnly = true)
public class AggregatedDataServiceImpl implements com.lantushenko.experimental.stub.services.AggregatedDataService {

    @Autowired
    private AggregatedDataRepository aggregatedDataRepository;

    @Override
    public List<ChartDataDto> load(Date periodStart, Date periodEnd,
                                   String tableName,
                                   String columnName,
                                   List<String> stationCodes,
                                   Integer bucketsCount){

        List<ChartDataDto> result = new ArrayList<>();
        long totalMilis = periodEnd.getTime() - periodStart.getTime();
        long bucketLength = totalMilis / bucketsCount;

        for(DateTime bucketStart = new DateTime(periodStart);
            bucketStart.isBefore(new DateTime(periodEnd));
            bucketStart = bucketStart.plus(bucketLength)){

            final Date dBucketStart = bucketStart.toDate();
            Date bucketEnd = bucketStart.plus(bucketLength).toDate();

            result.addAll(aggregatedDataRepository.load(dBucketStart, bucketEnd,
                    tableName,
                    columnName,
                    stationCodes).stream().map((a)->{
                return new ChartDataDto(a.getStation().getCode(), a.getStation().getName(),
                        dBucketStart, bucketEnd, a.getAvgData());
            }).collect(Collectors.toList()));
        }

        return result;
    }
}
