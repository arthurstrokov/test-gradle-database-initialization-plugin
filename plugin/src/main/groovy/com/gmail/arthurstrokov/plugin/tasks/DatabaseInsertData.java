
package com.gmail.arthurstrokov.plugin.tasks;

import com.gmail.arthurstrokov.plugin.util.InputService;
import com.gmail.arthurstrokov.plugin.util.SqlService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * A class that represents an algorithm
 * for insert data to database table from file.
 *
 * @author avistate
 * @version 1.0
 */
public class DatabaseInsertData extends DefaultTask {
    /**
     * A method that represents an algorithm
     * for insert data to database table from file.
     */
    @TaskAction
    public void databaseInsertData() throws ClassNotFoundException {
        List<String> data = InputService.readFromFile("data.sql");

        Class.forName("org.postgresql.Driver");
        try (Connection conn = DriverManager.getConnection(
                SqlService.DATABASE_URL, SqlService.USER, SqlService.PASS
        );
             Statement statement = conn.createStatement()
        ) {
            System.out.println("Connected to the database!");

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
            System.err.format(
                    "SQL State: %s\n%s", e.getSQLState(), e.getMessage()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
