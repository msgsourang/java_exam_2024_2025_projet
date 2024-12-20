package ism.maecdsd.bd;

import ism.maecdsd.bd.DatabaseConnection;
import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.repository.interfaces.DetteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetteRepositoryBd implements DetteRepository {

    private Connection connection = DatabaseConnection.getConnection();

    @Override
    public void insert(Dette dette) {
        try {
            String query = "INSERT INTO dettes (client_id, date, montant, montant_verser, montant_restant, status) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, dette.getClient().getId());
            stmt.setString(2, dette.getDate());
            stmt.setFloat(3, dette.getMontant());
            stmt.setFloat(4, dette.getMontantVerser());
            stmt.setFloat(5, dette.getMontantRestant());
            stmt.setBoolean(6, dette.isStatus());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Dette> lister() {
        List<Dette> dettes = new ArrayList<>();
        try {
            String query = "SELECT * FROM dettes";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dette dette = new Dette(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getFloat("montant_verser"),
                        null 
                );
                dette.setMontant(rs.getInt("montant"));
                dette.setMontantRestant(rs.getFloat("montant_restant"));
                dette.setStatus(rs.getBoolean("status"));
                dettes.add(dette);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dettes;
    }

    public List<Dette> findByClient(int clientId) {
        List<Dette> dettes = new ArrayList<>();
        try {
            String query = "SELECT * FROM dettes WHERE client_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Dette dette = new Dette(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getFloat("montant_verser"),
                        null
                );
                dette.setMontant(rs.getInt("montant"));
                dette.setMontantRestant(rs.getFloat("montant_restant"));
                dette.setStatus(rs.getBoolean("status"));
                dettes.add(dette);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dettes;
    }
    @Override
public Dette findDette(int id) {
    try {
        String query = "SELECT * FROM dettes WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Dette(
                    rs.getInt("id"),
                    rs.getString("date"),
                    rs.getFloat("montant_verser"),
                    null 
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

@Override
public List<Dette> listDetteNonSolde() {
    List<Dette> dettes = new ArrayList<>();
    try {
        String query = "SELECT * FROM dettes WHERE montant_restant > 0";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Dette dette = new Dette(
                    rs.getInt("id"),
                    rs.getString("date"),
                    rs.getFloat("montant_verser"),
                    null 
            );
            dette.setMontant(rs.getInt("montant"));
            dette.setMontantRestant(rs.getFloat("montant_restant"));
            dette.setStatus(rs.getBoolean("status"));
            dettes.add(dette);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dettes;
}

@Override
public List<Dette> listDetteNonSolde(Client client) {
    List<Dette> dettes = new ArrayList<>();
    try {
        String query = "SELECT * FROM dettes WHERE client_id = ? AND montant_restant > 0";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, client.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Dette dette = new Dette(
                    rs.getInt("id"),
                    rs.getString("date"),
                    rs.getFloat("montant_verser"),
                    client
            );
            dette.setMontant(rs.getInt("montant"));
            dette.setMontantRestant(rs.getFloat("montant_restant"));
            dette.setStatus(rs.getBoolean("status"));
            dettes.add(dette);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dettes;
}

@Override
public List<Dette> listDettesSoldes() {
    List<Dette> dettes = new ArrayList<>();
    try {
        String query = "SELECT * FROM dettes WHERE montant_restant = 0";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Dette dette = new Dette(
                    rs.getInt("id"),
                    rs.getString("date"),
                    rs.getFloat("montant_verser"),
                    null 
            );
            dette.setMontant(rs.getInt("montant"));
            dette.setMontantRestant(rs.getFloat("montant_restant"));
            dette.setStatus(rs.getBoolean("status"));
            dettes.add(dette);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return dettes;
}

@Override
public void update(DemandeDette demandeDette) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
}

}
