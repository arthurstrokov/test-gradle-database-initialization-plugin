
package com.gmail.arthurstrokov.plugin.util;

/**
 * A class that represents a data.
 *
 * @author avistate
 * @version 1.0
 */
public interface SqlService {
    /**
     *
     */
    String DATABASE_URL = "jdbc:postgresql://localhost:5432/test";
    /**
     *
     */
    String USER = "root";
    /**
     *
     */
    String PASS = "root";
    /**
     *
     */
    String SQL_TABLE_DROP = "DROP TABLE IF EXISTS EMPLOYEE";
    /**
     *
     */
    String SQL_SELECT = "SELECT * FROM EMPLOYEE";
}
