package services;

import beans.Concours;
import connexion.Connexion;
import dao.IDao;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service pour gérer les concours dans la base de données.
 * Implémente les méthodes CRUD pour l'entité Concours.
 */
public class ConcoursService implements IDao<Concours> {

    private final Connexion connexion;

    public ConcoursService() {
        connexion = Connexion.getInstance();
    }

    @Override
    public boolean create(Concours o) {
        String req = "insert into Concours (Id, Intitule, Date, Lieu) values (null, ?, ?, ?)";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getIntitule());
            ps.setDate(2, new Date(o.getDate().getTime()));
            ps.setString(3, o.getLieu());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Concours o) {
        String req = "delete from Concours where id = ?";
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
    public boolean update(Concours o) {
        String req = "update Concours set intitule = ?, date = ?, lieu = ? where id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setString(1, o.getIntitule());
            ps.setDate(2, new Date(o.getDate().getTime()));
            ps.setString(3, o.getLieu());
            ps.setInt(4, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public Concours findById(int id) {
        String req = "select * from Concours where id = ?";
        try {
            PreparedStatement ps = connexion.getCn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Concours(rs.getInt("id"), rs.getString("intitule"), rs.getDate("date"), rs.getString("lieu"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null; // Return null if no Concours is found
    }

    @Override
    public List<Concours> findAll() {
         List<Concours> concoursList = new ArrayList<>();
    String req = "SELECT * FROM Concours";
    try {
        PreparedStatement ps = connexion.getCn().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        // Debug: Print the number of rows fetched
        System.out.println("Fetching all Concours records...");

        while (rs.next()) {
            int id = rs.getInt("id");
            String intitule = rs.getString("intitule");
            Date date = rs.getDate("date");
            String lieu = rs.getString("lieu");

            // Debug: Print each Concours record
            System.out.println("Concours: id=" + id + ", intitule=" + intitule + ", date=" + date + ", lieu=" + lieu);

            // Create a Concours object and add it to the list
            Concours concours = new Concours(id, intitule, date, lieu);
            concoursList.add(concours);
        }
        

        // Debug: Print the total number of Concours records fetched
        System.out.println("Total Concours records fetched: " + concoursList.size());

    } catch (SQLException ex) {
        // Log the error
        System.err.println("Error fetching Concours records: " + ex.getMessage());
    }

    return concoursList;
    }
    

    public void create(String rami, String amal, String ramiamalexamplecom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setConcours(int i, Concours concours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Iterable<Concours> findByConcours(Concours concours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
