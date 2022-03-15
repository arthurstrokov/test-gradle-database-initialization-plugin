package com.gmail.arthurstrokov.plugin.tasks;

import com.gmail.arthurstrokov.plugin.util.InputService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.*;
import java.util.List;

import static com.gmail.arthurstrokov.plugin.util.SqlService.*;

public class DatabaseInsertData extends DefaultTask {

    @TaskAction
    public void databaseInsertData() throws ClassNotFoundException {
        List<String> data = InputService.readFromFile("data.sql");

        Class.forName("org.postgresql.Driver");
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, USER, PASS);
             Statement statement = conn.createStatement();
        ) {
            System.out.println("Connected to the database!");

            for (String sql : data) {
                statement.execute(sql);
            }

            ResultSet resultSet;
            resultSet = statement.executeQuery(SQL_SELECT);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("NAME"));
                System.out.println(resultSet.getString("CREATED_DATE"));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
