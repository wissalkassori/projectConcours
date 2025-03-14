package test;

import dao.ResultatDAO;
import models.Resultat;

public class TestResultatDAO {
    public static void main(String[] args) {
        ResultatDAO resultatDAO = new ResultatDAO();

        // Ajouter un résultat test (Assurez-vous que les ID existent dans la BD)
        Resultat resultat = new Resultat(0, 1, 1, (float) 15.5);
        resultatDAO.ajouterResultat(resultat);

        System.out.println("✅ Résultat ajouté avec succès !");
    }
}
