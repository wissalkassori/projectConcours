/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Hp
 */
import java.sql.Date;

public class Concours {

    private int id;
    private String intitule;
    private Date date;
    private String lieu;

    public Concours(String Intitule, String Date, String Lieu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Concours(int id, String intitule, String date, String lieu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Concours(int id, String intitule, Date date, String lieu) {
        super();
        this.id = id;
        this.intitule = intitule;
        this.date = date;
        this.lieu = lieu;
    }
      public Concours( String intitule, Date date, String lieu) {
        this.intitule = intitule;
        this.date = date;
        this.lieu = lieu;
    }

    public Object getDat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
