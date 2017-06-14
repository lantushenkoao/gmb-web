package com.lantushenko.experimental.stub.web.serialize.json;

import com.lantushenko.experimental.stub.dao.FieldDescription;

public class FieldDescriptionDto extends AbstractDto{
    private String tableName;
    private String columnName;
    private String description;

    public FieldDescriptionDto(FieldDescription description){
        super.setId(description.getId());
        tableName = description.getTableName();
        columnName = description.getColumnName();
        this.description = description.getDescription();
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getDescription() {
        return description;
    }
}
