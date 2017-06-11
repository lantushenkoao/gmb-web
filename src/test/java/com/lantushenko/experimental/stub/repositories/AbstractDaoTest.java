package com.lantushenko.experimental.stub.repositories;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.lantushenko.experimental.stub.dbunit.utils.DataSets;
import com.lantushenko.experimental.stub.dbunit.utils.DbUnitDataSource;

/**
 * Base class for all repository related tests.
 * Requires database connection defined in test-settings.xml
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "/tests-config.xml", "/tests-context.xml"})
public class AbstractDaoTest {

    @Autowired
    private DbUnitDataSource dbUnitDataSource;

    protected DbUnitDataSource getDbUnitDataSource() {
        return dbUnitDataSource;
    }

    @Before
    public void before() throws Exception {
        dbUnitDataSource.deleteAll(DataSets.DATASET_CLEAN_ALL);
    }

}
