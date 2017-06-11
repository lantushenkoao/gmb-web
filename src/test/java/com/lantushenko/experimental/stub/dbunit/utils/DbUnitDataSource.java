package com.lantushenko.experimental.stub.dbunit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;

import org.apache.commons.lang.ArrayUtils;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.InputSource;

/**
 * Responsible for filling database with test data
 * and comparing tables contents to expected state
 */
public class DbUnitDataSource implements InitializingBean {

    public static final String DATASET_FILE_EXTENSION = ".xml";
    public static final String DATASET_FOLDER = "/datasets/";

    private IDatabaseConnection connection;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public void insert(String dataSetName) throws Exception {
        execute(dataSetName, DatabaseOperation.INSERT);
    }

    public void deleteAll(String dataSetName) throws Exception {
        execute(dataSetName, DatabaseOperation.DELETE_ALL);
    }

    private void execute(String dataSetName, DatabaseOperation operation) throws Exception {
        IDataSet dataSet = new FlatXmlDataSet(
                new ClassPathResource(DATASET_FOLDER + dataSetName + DATASET_FILE_EXTENSION).getInputStream());
        operation.execute(connection, dataSet);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        connection = new DatabaseConnection(conn);
    }

    public void setUrl(String url) {
        this.dbUrl = url;
    }

    public void setUser(String user) {
        this.dbUser = user;
    }

    public void setPassword(String password) {
        this.dbPassword = password;
    }
}
