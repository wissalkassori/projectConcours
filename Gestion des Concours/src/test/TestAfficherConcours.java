package test;

import dao.ConcoursDAO;
import models.Concours;
import java.util.List;

public class TestAfficherConcours {
    public static void main(String[] args) {
        ConcoursDAO concoursDAO = new ConcoursDAO();
        List<Concours> concoursList = concoursDAO.getAllConcours();

        System.out.println("? Liste des concours :");
        concoursList.stream().forEach((c) -> {
            System.out.println("? " + c.getIntitule() + " - " + c.getLieu() + " - " + c.getDate());
        });
    }
}
