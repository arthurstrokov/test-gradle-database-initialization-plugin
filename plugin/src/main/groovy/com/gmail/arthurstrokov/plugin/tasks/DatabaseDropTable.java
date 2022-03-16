package com.gmail.arthurstrokov.plugin.tasks;

import com.gmail.arthurstrokov.plugin.util.SqlService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDropTable extends DefaultTask {

    @TaskAction
    public void databaseDropTable() throws ClassNotFoundException {

        Class.forName("org.postgresql.Driver"); // It doesn't work without it.
        try (Connection conn = DriverManager.getConnection(
                SqlService.DATABASE_URL, "root", "root");
             Statement statement = conn.createStatement()
        ) {
            System.out.println(("Connected to the database!"));
            statement.execute(SqlService.SQL_TABLE_DROP);
            System.out.println("Table dropped in given database...");
        } catch (SQLException e) {
            String.format("SQL State: %s %s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
