package resources.database;

import config.Configurator;
import annotations.Component;
import annotations.Inject1;
import config.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {
    
    @Value(propertyName = "db.url")
    private String url;
    
    @Value(propertyName = "db.user")
    private String user;
    
    @Value(propertyName = "db.password")
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