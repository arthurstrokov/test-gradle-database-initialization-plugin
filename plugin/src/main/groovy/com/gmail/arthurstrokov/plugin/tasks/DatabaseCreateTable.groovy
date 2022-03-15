package com.gmail.arthurstrokov.plugin.tasks

import com.gmail.arthurstrokov.plugin.util.InputService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class DatabaseCreateTable extends DefaultTask {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/test";
    private static final String SQL_TABLE_DROP = "DROP TABLE IF EXISTS EMPLOYEE";

    @TaskAction
    def databaseCreateTable() {
        def schema = InputService.readFromFile("schema.sql").get(0);

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, "root", "root");
             Statement statement = conn.createStatement();
        ) {
            println("Connected to the database!");

            statement.execute(SQL_TABLE_DROP);
            println("Table dropped in given database...");

            statement.execute(schema);
            println("Table created in given database...");

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
