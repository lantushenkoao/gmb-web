package com.lantushenko.experimental.stub.repositories;

import com.lantushenko.experimental.stub.dao.AggragatedData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AggregatedDataRepository {

    List<AggragatedData> load(@Param("periodStart") Date periodStart,
                              @Param("periodEnd") Date periodEnd,
                              @Param("tableName") String tableName,
                              @Param("columnName") String columnName,
                              @Param("stationCodes") List<String> stationIds);
}
