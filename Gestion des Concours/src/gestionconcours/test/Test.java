package test;

import beans.Candidat;
import beans.Concours;
import beans.Resultat;
import java.util.Date;
import services.CandidatService;
import services.ConcoursService;
import services.ResultatService;

public class Test {
    public static void main(String[] args) {

        CandidatService candidatService = new CandidatService();
        ConcoursService concoursService = new ConcoursService();
        ResultatService resultatService = new ResultatService();

        Concours concours = new Concours("Concours d'Ingénierie", new java.sql.Date(new Date().getTime()), "Salle 101");
        concoursService.create(concours);
        

        Candidat candidat = new Candidat("Rania", "Amal", "rania.amal@example.com");
        candidatService.create(candidat);

        Candidat c = candidatService.findById(candidat.getId());
        if (c != null) {
            System.out.println("Nom du candidat avec ID " + c.getId() + " : " + c.getNom());
        } else {
            System.out.println("Aucun candidat trouvé avec l'ID " + candidat.getId());
        }

        if (c != null) {
            candidatService.delete(c);
            System.out.println("Candidat supprimé avec succès.");
        }

        System.out.println("Liste des candidats après suppression :");
        for (Candidat cand : candidatService.findAll()) {
            System.out.println(cand.getNom());
        }

        Candidat candidatToAdd = candidatService.findById(candidat.getId());
        if (concours != null && candidatToAdd != null) {

            Resultat resultat = new Resultat(concours, candidatToAdd, 15.5f);
            resultatService.create(resultat);
            System.out.println("Résultat ajouté avec succès pour le candidat " + candidatToAdd.getNom());

            System.out.println("Liste des résultats :");
            for (Resultat res : resultatService.findAll()) {
                System.out.println("Concours ID: " + res.getConcours() + ", Candidat ID: " + res.getCandidat() + ", Note: " + res.getNote());
            }
        }
    }
}
