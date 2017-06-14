package com.lantushenko.experimental.stub.dao.impl;


import com.lantushenko.experimental.stub.dao.FieldDescription;

public class FieldDescriptionImpl extends AbstractEntityImpl<FieldDescriptionImpl> implements FieldDescription {

    private String tableName;
    private String columnName;
    private String description;

    public FieldDescriptionImpl(){
        super(FieldDescriptionImpl.class);
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
