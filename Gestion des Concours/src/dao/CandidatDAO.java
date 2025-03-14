
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Candidat;

public class CandidatDAO {
    public void inscrireCandidat(Candidat candidat) {
        String sql = "INSERT INTO Candidat (nom, prenom, email) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, candidat.getNom());
            stmt.setString(2, candidat.getPrenom());
            stmt.setString(3, candidat.getEmail());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Candidat> getAllCandidats() {
        List<Candidat> candidats = new ArrayList<>();
        String sql = "SELECT * FROM Candidat";

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                candidats.add(new Candidat(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return candidats;
    }

    public void ajouterCandidat(Candidat candidat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
