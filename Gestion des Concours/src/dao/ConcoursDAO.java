package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Concours;
import dao.Database;

public class ConcoursDAO {

    public void ajouterConcours(Concours concours) {
        String sql = "INSERT INTO Concours (intitule, date, lieu) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, concours.getIntitule());
            stmt.setDate(2, Date.valueOf(concours.getDate()));
            stmt.setString(3, concours.getLieu());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    concours.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout du concours", e);
        }
    }

    public List<Concours> getAllConcours() {
        List<Concours> concoursList = new ArrayList<>();
        String sql = "SELECT * FROM Concours";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Concours concours = new Concours(
                    rs.getInt("id"),
                    rs.getString("intitule"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("lieu")
                );
                concoursList.add(concours);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la récupération des concours", e);
        }
        return concoursList;
    }
}
