package beans;

public class Resultat {

    private Concours concours;
    private Candidat candidat;
    private float note;

    // Constructeur par défaut
    public Resultat() {
    }

    // Constructeur avec paramètres
    public Resultat(Concours concours, Candidat candidat, float note) {
        this.concours = concours;
        this.candidat = candidat;
        this.note = note;
    }

    public Concours getConcours() {
        return concours;
    }

    public void setConcours(Concours concours) {
        this.concours = concours;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "concours=" + (concours != null ? concours.getIntitule() : "null") +
                ", candidat=" + (candidat != null ? candidat.getNom() + " " + candidat.getPrenom() : "null") +
                ", note=" + note +
                '}';
    }

    public Object getLieu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getIntitule() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}