package com.gmail.arthurstrokov.plugin.tasks;

import com.gmail.arthurstrokov.plugin.util.InputService;
import com.gmail.arthurstrokov.plugin.util.SqlService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.*;
import java.util.List;

public class DatabaseInsertData extends DefaultTask {

    @TaskAction
    public void databaseInsertData() throws ClassNotFoundException {
        List<String> data = InputService.readFromFile("data.sql");

        Class.forName("org.postgresql.Driver"); // It doesn't work without it.
        try (Connection conn = DriverManager.getConnection(
                SqlService.DATABASE_URL, SqlService.USER, SqlService.PASS
        );
             Statement statement = conn.createStatement()
        ) {
            System.out.println(("Connected to the database!"));

            for (String sql : data) {
                statement.execute(sql);
            }

            ResultSet resultSet;
            resultSet = statement.executeQuery(SqlService.SQL_SELECT);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("NAME"));
                System.out.println(resultSet.getString("CREATED_DATE"));
            }
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
