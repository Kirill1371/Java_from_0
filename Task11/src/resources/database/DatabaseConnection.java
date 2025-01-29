package resources.database;

import config.Configurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import annotations.Component;
import annotations.Inject1;
import config.ConfigProperty1;

@ConfigProperty1(configFileName = "database.properties")
@Component
public class DatabaseConnection {
    
    @ConfigProperty1(propertyName = "db.url")
    private String url;
    
    @ConfigProperty1(propertyName = "db.user")
    private String user;
    
    @ConfigProperty1(propertyName = "db.password")
    private String password;
    
    @Inject1
    public DatabaseConnection() {
        Configurator.configure(this);
    }

    public Connection getConnection() throws SQLException {
        System.out.println("Attempting to connect to the database...");
        return DriverManager.getConnection(url, user, password);
    }
}