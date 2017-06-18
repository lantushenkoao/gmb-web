package com.lantushenko.experimental.stub.services;

import com.lantushenko.experimental.stub.web.serialize.json.ChartDataDto;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 18/06/2017.
 */
public interface AggregatedDataService {
    List<ChartDataDto> load(Date periodStart, Date periodEnd,
                            String tableName,
                            String columnName,
                            List<String> stationCodes,
                            Integer bucketsCount);
}
