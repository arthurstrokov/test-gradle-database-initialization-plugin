package com.gmail.arthurstrokov.plugin.util;

public class SqlService {

    public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/test";
    public static final String USER = "root";
    public static final String PASS = "root";
    public static final String SQL_TABLE_DROP = "DROP TABLE IF EXISTS EMPLOYEE";
    public static final String SQL_SELECT = "SELECT * FROM EMPLOYEE";

    private SqlService() {
    }
}
