package ism.maecdsd.bd;

import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.Article;
import ism.maecdsd.repository.interfaces.DemandeDetteRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeDetteRepositoryBd implements DemandeDetteRepository {

    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/maecdsd";
        String user = "postgres";
        String password = "Massourang9@";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void insert(DemandeDette demande) {
        String query = "INSERT INTO demande_dette (date, montant, client_id, status) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, demande.getDate());
            stmt.setFloat(2, demande.getMontant());
            stmt.setInt(3, demande.getClient().getId());  
            stmt.setBoolean(4, demande.isStatus());

            int affectedRows = stmt.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int demandeId = generatedKeys.getInt(1);
                        for (Article article : demande.getArticles()) {
                            String articleQuery = "INSERT INTO demande_dette_article (demande_dette_id, article_id) VALUES (?, ?)";
                            try (PreparedStatement articleStmt = connection.prepareStatement(articleQuery)) {
                                articleStmt.setInt(1, demandeId); 
                                articleStmt.setInt(2, article.getId());  
                                articleStmt.executeUpdate();
                            }
                        }
                    } else {
                        throw new SQLException("Échec de la récupération de l'ID généré.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DemandeDette> lister() {
        List<DemandeDette> demandes = new ArrayList<>();
        String query = "SELECT * FROM demande_dette"; 

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                DemandeDette demande = new DemandeDette(
                    rs.getString("date"),
                    rs.getFloat("montant"),
                    new Client(rs.getInt("client_id")),  
                    rs.getBoolean("status")
                );
                demandes.add(demande);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return demandes;
    }

    @Override
    public List<DemandeDette> listerDemandeDetteParStatus(boolean status) {
        List<DemandeDette> demandes = new ArrayList<>();
        String query = "SELECT * FROM demande_dette WHERE status = ?"; 

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setBoolean(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DemandeDette demande = new DemandeDette(
                    rs.getString("date"),
                    rs.getFloat("montant"),
                    new Client(rs.getInt("client_id")),  
                    rs.getBoolean("status")
                );
                demandes.add(demande);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return demandes;
    }


    public void ajouter(DemandeDette demande) {
        String query = "INSERT INTO demande_dette (date, montant, client_id, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, demande.getDate());
            stmt.setFloat(2, demande.getMontant());
            stmt.setInt(3, demande.getClient().getId());  
            stmt.setBoolean(4, demande.isStatus());

            stmt.executeUpdate();

            for (Article article : demande.getArticles()) {
                String articleQuery = "INSERT INTO demande_dette_article (demande_dette_id, article_id) VALUES (?, ?)";
                try (PreparedStatement articleStmt = connection.prepareStatement(articleQuery)) {
                    articleStmt.setInt(1, getDerniereDemandeDetteId(connection));  
                    articleStmt.setInt(2, article.getId());
                    articleStmt.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getDerniereDemandeDetteId(Connection connection) throws SQLException {
         String query = "SELECT currval(pg_get_serial_sequence('demande_dette', 'id'))"; 

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Impossible de récupérer l'ID de la dernière demande de dette insérée.");
            }
        }
    }

    public void mettreAJour(DemandeDette demande) {
        String query = "UPDATE demande_dette SET date = ?, montant = ?, client_id = ?, status = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, demande.getDate());
            stmt.setFloat(2, demande.getMontant());
            stmt.setInt(3, demande.getClient().getId());
            stmt.setBoolean(4, demande.isStatus());
            stmt.setInt(5, demande.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(DemandeDette demandeDette) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}

