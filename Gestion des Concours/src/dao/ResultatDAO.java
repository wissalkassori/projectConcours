package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Resultat;

public class ResultatDAO {

    public void ajouterResultat(Resultat resultat) {
        String sql = "INSERT INTO Resultat (concours_id, candidat_id, note) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, resultat.getConcoursId());
            stmt.setInt(2, resultat.getCandidatId());
            stmt.setDouble(3, resultat.getNote());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    resultat.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void enregistrerResultat(Resultat resultat) {
        ajouterResultat(resultat);
    }

    public List<Resultat> getResultatsParConcours(int concoursId) {
        List<Resultat> resultats = new ArrayList<>();
        String sql = "SELECT * FROM Resultat WHERE concours_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, concoursId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Resultat resultat;
                    resultat = new Resultat(
                            rs.getInt("id"),
                            rs.getInt("concours_id"),
                            rs.getInt("candidat_id"), (float) rs.getDouble("note"));
                    resultats.add(resultat);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultats;
    }

    public List<Resultat> getResultatsParCandidat(int candidatId) {
        List<Resultat> resultats = new ArrayList<>();
        String sql = "SELECT * FROM Resultat WHERE candidat_id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, candidatId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Resultat resultat = new Resultat(
                        rs.getInt("id"),
                        rs.getInt("concours_id"),
                        rs.getInt("candidat_id"), (float) rs.getDouble("note"));
                    resultats.add(resultat);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultats;
    }
}
