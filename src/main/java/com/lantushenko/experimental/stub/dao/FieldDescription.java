package com.lantushenko.experimental.stub.dao;

/**
 * Created by admin on 11/06/2017.
 */
public interface FieldDescription extends AbstractEntity {
    String getTableName();

    String getColumnName();

    String getDescription();
}
