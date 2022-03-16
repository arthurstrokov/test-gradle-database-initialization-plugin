
package com.gmail.arthurstrokov.plugin.tasks;

import com.gmail.arthurstrokov.plugin.util.SqlService;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A class that represents an algorithm
 * for creating database table from file.
 *
 * @author avistate
 * @version 1.0
 */
public class DatabaseDropTable extends DefaultTask {
    /**
     * A method that represents an algorithm
     * for delete data from database table.
     */
    @TaskAction
    public void databaseDropTable() throws ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        try (Connection conn = DriverManager.getConnection(
                SqlService.DATABASE_URL, "root", "root");
             Statement statement = conn.createStatement()
        ) {
            System.out.println(("Connected to the database!"));
            statement.execute(SqlService.SQL_TABLE_DROP);
            System.out.println("Table dropped in given database...");
        } catch (SQLException e) {
            System.err.format(
                    "SQL State: %s\n%s", e.getSQLState(), e.getMessage()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
