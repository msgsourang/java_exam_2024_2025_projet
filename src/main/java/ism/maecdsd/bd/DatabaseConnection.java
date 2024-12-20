package ism.maecdsd.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/maecdsd"; 
    private static final String DB_USER = "postgres"; 
    private static final String DB_PASSWORD = "Massourang9@"; 

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connexion à la base de données réussie !");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Échec de la connexion à la base de données : " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
