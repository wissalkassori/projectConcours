package services;

import beans.Resultat;
import beans.Concours;
import beans.Candidat;
import connexion.Connexion;
import dao.IDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service pour gérer les résultats des concours dans la base de données.
 * Implémente les méthodes CRUD pour l'entité Resultat.
 */
public class ResultatService implements IDao<Resultat> {

    private final Connexion connexion;
    private final ConcoursService cs;
    private final CandidatService cds;

    public ResultatService() {
        connexion = Connexion.getInstance();
        cs = new ConcoursService();
        cds = new CandidatService();
    }

    @Override
    public boolean create(Resultat o) {
        String req = "INSERT INTO Resultat (concours_id, candidat_id, note) VALUES (?, ?, ?)"; // Use the correct column names
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getConcours().getId());  
            ps.setInt(2, o.getCandidat().getId());  
            ps.setFloat(3, o.getNote());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Resultat o) {
        String req = "DELETE FROM Resultat WHERE concours_id = ? AND candidat_id = ?"; // Use the correct column names
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getConcours().getId()); 
            ps.setInt(2, o.getCandidat().getId()); 
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Resultat o) {
        String req = "UPDATE Resultat SET note = ? WHERE concours_id = ? AND candidat_id = ?"; // Use the correct column names
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setFloat(1, o.getNote());
            ps.setInt(2, o.getConcours().getId());  
            ps.setInt(3, o.getCandidat().getId());  
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la mise à jour : " + ex.getMessage());
        }
        return false;
    }

    public Resultat findById(int concoursId, int candidatId) {
        String req = "SELECT * FROM Resultat WHERE concours_id = ? AND candidat_id = ?"; // Use the correct column names
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, concoursId);
            ps.setInt(2, candidatId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Concours concours = cs.findById(rs.getInt("concours_id")); 
                Candidat candidat = cds.findById(rs.getInt("candidat_id")); 
                return new Resultat(concours, candidat, rs.getFloat("note"));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la recherche : " + ex.getMessage());
        }
        return null;
    }

    public List<Resultat> findAll() {
    List<Resultat> resultats = new ArrayList<>();
    String req = "SELECT * FROM Resultat";
    try {
        PreparedStatement ps = connexion.getCn().prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int concoursId = rs.getInt("concours_id");
            int candidatId = rs.getInt("candidat_id");
            float note = rs.getFloat("note");

            Concours concours = cs.findById(concoursId);
            Candidat candidat = cds.findById(candidatId);

            if (concours != null && candidat != null) {
                resultats.add(new Resultat(concours, candidat, note));
            } else {
                System.err.println("Concours ou Candidat null pour le résultat : concoursId=" + concoursId + ", candidatId=" + candidatId);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des résultats : " + ex.getMessage());
    }
    return resultats;
}
public List<Resultat> findByConcours(Concours c) {
    List<Resultat> resultats = new ArrayList<>();
    String req = "SELECT * FROM Resultat where concours = ?";
    try {
        PreparedStatement ps = connexion.getCn().prepareStatement(req);
        ps.setInt(1,c.getId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int concoursId = rs.getInt("concours_id");
            int candidatId = rs.getInt("candidat_id");
            float note = rs.getFloat("note");

            Concours concours = cs.findById(concoursId);
            Candidat candidat = cds.findById(candidatId);

            if (concours != null && candidat != null) {
                resultats.add(new Resultat(concours, candidat, note));
            } else {
                System.err.println("Concours ou Candidat null pour le résultat : concoursId=" + concoursId + ", candidatId=" + candidatId);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des résultats : " + ex.getMessage());
    }
    return resultats;
}
    @Override
    public Resultat findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void ajouter(Resultat resultat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   
    }

