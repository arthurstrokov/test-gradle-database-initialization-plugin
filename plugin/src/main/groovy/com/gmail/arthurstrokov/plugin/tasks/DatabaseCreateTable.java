package com.gmail.arthurstrokov.plugin.tasks;

import com.gmail.arthurstrokov.plugin.util.InputService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.gmail.arthurstrokov.plugin.util.SqlService.DATABASE_URL;
import static com.gmail.arthurstrokov.plugin.util.SqlService.SQL_TABLE_DROP;

public class DatabaseCreateTable extends DefaultTask {

    @TaskAction
    public void databaseCreateTable() throws ClassNotFoundException {
        String schema = InputService.readFromFile("schema.sql").get(0);

        Class.forName("org.postgresql.Driver");
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, "root", "root");
             Statement statement = conn.createStatement();
        ) {
            System.out.println(("Connected to the database!"));

            statement.execute(SQL_TABLE_DROP);
            System.out.println("Table dropped in given database...");

            statement.execute(schema);
            System.out.println("Table created in given database...");

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
