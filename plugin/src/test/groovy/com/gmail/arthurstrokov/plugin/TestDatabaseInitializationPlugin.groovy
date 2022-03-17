package com.gmail.arthurstrokov.plugin

import com.gmail.arthurstrokov.plugin.tasks.DatabaseCreateTable
import com.gmail.arthurstrokov.plugin.util.SqlService
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.jupiter.api.Test

import java.sql.*

import static org.junit.jupiter.api.Assertions.*

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
    void testDatabase() throws ClassNotFoundException, SQLException {
        String sqlDropTable = "DROP TABLE IF EXISTS TEST";
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS TEST( ID serial, NAME varchar(100) NOT NULL, PRIMARY KEY (NAME))";
        String sqlInsertData = "INSERT INTO TEST(NAME) VALUES ('test') ON CONFLICT (NAME) DO NOTHING";
        String sqlSelect = "SELECT * FROM TEST";

        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(SqlService.DATABASE_URL, "root", "root")) {
            Statement statement = connection.createStatement();
            assertTrue(isDatabaseConnected(connection));
            statement.execute(sqlDropTable);
            statement.execute(sqlCreateTable);
            statement.execute(sqlInsertData);
            ResultSet resultSet = statement.executeQuery(sqlSelect);
            List<String> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(resultSet.getString("NAME"));
            }
            assertEquals("test", list.get(0));
            assertNotEquals("tes1t", list.get(0));
            statement.execute(sqlDropTable);
            connection.close();
            assertFalse(isDatabaseConnected(connection));
        }
    }
}
