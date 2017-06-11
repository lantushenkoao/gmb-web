package com.lantushenko.experimental.stub.dao;

/**
 * Base class for all DAO objects stored in database.
 * Each object should have ID defined (implement an analog
 * of this class for value objects if neccessary)
 */
public interface AbstractEntity {

    Long getId();

    void setId(Long id);

    /**
     * Returns false if the object has ID. In general it means
     * that object is retrieved from database
     */
    boolean isTransient();

}