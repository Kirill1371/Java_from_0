package ru.senla.javacourse.tarasov.hotel.db.entity.database;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import ru.senla.javacourse.tarasov.hotel.impl.annotations.Component;
import ru.senla.javacourse.tarasov.hotel.impl.annotations.Inject;

@Component
public class DatabaseConnection {
    private final Properties properties = new Properties();

    @Inject
    public DatabaseConnection() {
        try (FileInputStream input = new FileInputStream("C:/Users/Kirill/IdeaProjects/Test/src/main/java/com/test/resources/database/database.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties file", e);
        }
    }

    public Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        System.out.println("Attempting to connect to the database...");
        return DriverManager.getConnection(url, user, password);
    }
}

