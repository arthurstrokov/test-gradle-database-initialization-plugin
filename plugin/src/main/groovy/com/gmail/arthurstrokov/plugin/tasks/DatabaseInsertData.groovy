package com.gmail.arthurstrokov.plugin.tasks

import com.gmail.arthurstrokov.plugin.util.InputService
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.sql.*

class DatabaseInsertData extends DefaultTask {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/test";
    private static final String SQL_SELECT = "SELECT * FROM EMPLOYEE";

    @TaskAction
    def databaseInsertData() {
        def data = InputService.readFromFile("data.sql");

        try (Connection conn = DriverManager.getConnection(DATABASE_URL, "root", "root");
             Statement statement = conn.createStatement();
        ) {
            println("Connected to the database!");

            for (String sql : data) {
                statement.execute(sql);
            }

            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                println(resultSet.getString("NAME"));
                println(resultSet.getString("CREATED_DATE"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
