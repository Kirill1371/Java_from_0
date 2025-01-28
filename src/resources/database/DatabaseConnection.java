package resources.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "src/resources/database/database.properties";

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading database properties file: " + e.getMessage());
            throw new RuntimeException(e);
        }

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        System.out.println("Attempting to connect to the database...");
        return DriverManager.getConnection(url, user, password);
    }
}
