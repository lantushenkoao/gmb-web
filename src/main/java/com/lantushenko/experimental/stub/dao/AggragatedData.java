package com.lantushenko.experimental.stub.dao;

import java.util.Date;

public interface AggragatedData {
    Double getAvgData();

    Date getPeriodStart();

    Date getPeriodEnd();

    Station getStation();
}
