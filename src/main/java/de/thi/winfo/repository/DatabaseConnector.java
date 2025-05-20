package de.thi.winfo.repository;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DatabaseConnector {
    private static final String DROP_TABLE_STATEMENT = "DROP TABLE IF EXISTS `student`;";
    private static final String CREATE_TABLE_STATEMENT = """
            CREATE TABLE IF NOT EXISTS student
            (
                id             VARCHAR(6) PRIMARY KEY,
                first_name     VARCHAR(100),
                last_name      VARCHAR(100),
                date_of_birth  DATE
            );""";

    private static final String INSERT_TESTDATA_STATEMENT = """
            INSERT INTO student (id, first_name, last_name, date_of_birth)
            VALUES ('AL1815', 'Ada', 'Lovelace', '1815-12-10')
            """;

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlite:database/example.db");
    }

    public static void setupDatabase() {
        String uri = "jdbc:sqlite:database/example.db";

        try (Connection connection = DriverManager.getConnection(uri)){
            Statement statement = connection.createStatement();

            statement.executeUpdate(DROP_TABLE_STATEMENT);
            statement.executeUpdate(CREATE_TABLE_STATEMENT);
            statement.executeUpdate(INSERT_TESTDATA_STATEMENT);
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // this is only for test reasons, to use the DB in your app, take a look into the Application runner
        Class.forName("org.sqlite.JDBC");
        new DatabaseConnector().setupDatabase();
    }
}
