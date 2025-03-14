package test;

import dao.ConcoursDAO;
import models.Concours;
import java.time.LocalDate; 

public class TestConcoursDAO {
    public static void main(String[] args) {
        ConcoursDAO concoursDAO = new ConcoursDAO();


        Concours concours = new Concours(0, "Concours ENS", LocalDate.of(2025, 5, 20), "Marrakech");

        concoursDAO.ajouterConcours(concours);
        System.out.println("Concours ajouté avec succès !");
    }
}
