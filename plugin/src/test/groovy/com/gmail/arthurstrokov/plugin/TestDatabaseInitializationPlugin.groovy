package com.gmail.arthurstrokov.plugin

import com.gmail.arthurstrokov.plugin.tasks.DatabaseCreateTable
import com.gmail.arthurstrokov.plugin.util.SqlService
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertTrue

class TestDatabaseInitializationPlugin {

    static boolean isDatabaseConnected(Connection connection) throws SQLException {
        return connection != null && !connection.isClosed();
    }

    @Test
    void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build();
        def task = project.task('databaseCreateTable', type: DatabaseCreateTable)
        assert (task instanceof DatabaseCreateTable)
    }

    @Test
    void testConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(SqlService.DATABASE_URL, "root", "root")) {
            assertTrue(isDatabaseConnected(connection));
            connection.close();
            assertFalse(isDatabaseConnected(connection));
        }
    }
}
