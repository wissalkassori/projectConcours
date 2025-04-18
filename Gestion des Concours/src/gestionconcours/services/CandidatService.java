package services;

import beans.Candidat;
import beans.Resultat;
import connexion.Connexion;
import dao.IDao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a
 */
public class CandidatService implements IDao<Candidat> {

    private final Connexion connexion;

    public CandidatService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Candidat o) {
        String req = "insert into Candidat (id, nom, prenom, email) values (null, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Candidat o) {
        String req = "delete from Candidat where id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Candidat o) {
        String req = "update Candidat set nom = ?, prenom = ?, email = ? where id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getEmail());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Candidat findById(int id) {
        String req = "select * from Candidat where id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Candidat(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null; // Return null if no Candidat is found
    }

    @Override
    public List<Candidat> findAll() {
        List<Candidat> candidats = new ArrayList<>();
        String req = "select * from Candidat";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                candidats.add(new Candidat(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return candidats;
    }

    public void create(int i, String mathématiques, java.util.Date date, String marrakech) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void setCandidat(int i, Candidat candidat) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public boolean create(Resultat resultat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
