package io.github.miolivc.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String url = "jdbc:postgresql://localhost:5432/Banco-da-Gente";
    private static final String user = "postgres";
    private static final String password = "123";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.org.postgresql");
        return DriverManager.getConnection(url, user, password);
    }

}
