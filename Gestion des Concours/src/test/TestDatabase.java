package test;

import dao.Database;
import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabase {
    public static void main(String[] args) throws SQLException {
        Connection conn = Database.getConnection();
        if (conn != null) {
            System.out.println(" Connexion à la base de données réussie !");
        } else {
            System.out.println(" Erreur de connexion !");
        }
    }
}
