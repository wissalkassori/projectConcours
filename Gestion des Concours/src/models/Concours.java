package models;

import java.time.LocalDate;
import java.util.Date;

public final class Concours {
    private int id;
    private String intitule;
    private LocalDate date;
    private String lieu;
    public Concours(int id, String intitule, LocalDate date, String lieu) {
        this.id = id;
        setIntitule(intitule);
        setDate(date);
        setLieu(lieu);
    }

   
    public Concours(String intitule, LocalDate date, String lieu) {
        this(0, intitule, date, lieu);
    }

    public Concours(int i, String intitule, Date date, String lieu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() { return id; }
    public String getIntitule() { return intitule; }
    public LocalDate getDate() { return date; }
    public String getLieu() { return lieu; }

    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("L'ID ne peut pas être négatif !");
        this.id = id;
    }

    public void setIntitule(String intitule) {
        if (intitule == null || intitule.trim().isEmpty()) {
            throw new IllegalArgumentException("L'intitulé ne peut pas être vide !");
        }
        this.intitule = intitule;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("La date ne peut pas être nulle !");
        }
        this.date = date;
    }

    public void setLieu(String lieu) {
        if (lieu == null || lieu.trim().isEmpty()) {
            throw new IllegalArgumentException("Le lieu ne peut pas être vide !");
        }
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Concours{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", date=" + date +
                ", lieu='" + lieu + '\'' +
                '}';
    }
}
