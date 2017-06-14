package com.lantushenko.experimental.stub.repositories;

import com.lantushenko.experimental.stub.dao.AggragatedData;

import java.util.Date;
import java.util.List;

public interface AggregatedDataRepository {

    List<AggragatedData> load(Date periodStart, Date periodEnd,
                              String tableName,
                              String columnName,
                              List<String> stationIds);
}
