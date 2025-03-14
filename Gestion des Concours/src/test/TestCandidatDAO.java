package test;

import dao.CandidatDAO;
import models.Candidat;

public class TestCandidatDAO {
    public static void main(String[] args) {
        CandidatDAO candidatDAO = new CandidatDAO();
        
        Candidat candidat = new Candidat(0, "Ali", "Ahmed", "ali.ahmed@email.com");
        candidatDAO.ajouterCandidat(candidat);

        System.out.println(" Candidat ajouté avec succès !");
    }
}
