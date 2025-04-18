package gui;

import connexion.Connexion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import services.ConcoursService;

public class Graphe extends JInternalFrame {

    private DefaultPieDataset dataset;
    private ConcoursService concoursService;

    public Graphe() {
        super("Répartition des Concours", true, true, true, true);
        setSize(700, 500);
        setLayout(new BorderLayout());

        concoursService = new ConcoursService(); 
        dataset = new DefaultPieDataset();

        System.out.println("Initialisation du graphique...");

        // Remplir les données du dataset
        remplirDataset();

        // Vérification si le dataset est vide
        if (dataset.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Aucune donnée disponible pour afficher le graphique.", "Information", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Aucune donnée disponible pour le graphique.");
            return;
        }

        // Création du graphique
        JFreeChart pieChart = ChartFactory.createPieChart(
                "Répartition des Concours",
                dataset,
                true,  // Légende
                true,  // Info tooltip
                false  // Pas de URLs interactifs
        );

        // Panel du graphique
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(600, 400));

        add(chartPanel, BorderLayout.CENTER);
        
        // Forcer la mise à jour de l'affichage
        setVisible(true);
        revalidate();
        repaint();

        System.out.println("Graphique affiché avec succès !");
    }

    private void remplirDataset() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            System.out.println("Connexion à la base de données...");
            con = Connexion.getInstance().getConnection();
            if (con == null) {
                System.out.println("Échec de connexion à la base de données !");
                JOptionPane.showMessageDialog(this, "Connexion à la base de données impossible.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("Connexion réussie !");

            String query = "SELECT intitule, COUNT(*) AS nombre FROM concours GROUP BY intitule";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            System.out.println("Récupération des données...");
            boolean hasData = false;
            while (rs.next()) {
                String intitule = rs.getString("intitule");
                int nombre = rs.getInt("nombre");
                dataset.setValue(intitule, nombre);
                System.out.println("Donnée ajoutée : " + intitule + " -> " + nombre);
                hasData = true;
            }

            if (!hasData) {
                System.out.println("Aucune donnée trouvée dans la base.");
            }

        } catch (SQLException e) {
            System.out.println("Erreur SQL : " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException ex) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + ex.getMessage());
            }
        }
    }
}
