package com.lantushenko.experimental.stub.repositories;

public interface SchemaVersionRepository extends AbstractRepository {

    Integer getVersion();

    void insertVersion();

    void updateVersion(Integer version);
}
