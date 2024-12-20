package ism.maecdsd.bd;

import ism.maecdsd.bd.DatabaseConnection;
import ism.maecdsd.entity.User;
import ism.maecdsd.entity.Role;
import ism.maecdsd.repository.interfaces.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryBd implements UserRepository {

    private Connection connection = DatabaseConnection.getConnection();
    
    @Override
    public User getUserByLoginPasword(String login, String password) {
        try {
            String query = "SELECT * FROM users WHERE login = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                String roleString = rs.getString("role");
    
                roleString = roleString != null ? roleString.trim().toUpperCase() : "";
    
                Role role = null;
                try {
                    role = Role.valueOf(roleString);
                } catch (IllegalArgumentException e) {
                    System.out.println("Erreur : rôle inconnu dans la base de données : " + roleString);
                }
    
                if (role != null) {
                    return new User(
                            rs.getString("login"),
                            rs.getString("password"),
                            role,
                            rs.getBoolean("status")
                    );
                } else {
                    System.out.println("Erreur : rôle non valide : " + roleString);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    

@Override
public void insert(User user) {
    try {
        String query = "INSERT INTO users (login, password, client_id, role, status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, user.getLogin());
        stmt.setString(2, user.getPassword());
        
        if (user.getClient() != null) {
            stmt.setInt(3, user.getClient().getId()); 
        } else {
            stmt.setNull(3, java.sql.Types.INTEGER);
        }

        stmt.setString(4, user.getRole().name());
        stmt.setBoolean(5, user.isStatus()); 
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @Override
    public List<User> lister() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM users";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")),
                        rs.getBoolean("status")
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findByLogin(String login) {
        try {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("login"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role")),
                        rs.getBoolean("status")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
public List<User> listUserByStatus(Boolean status) {
    List<User> users = new ArrayList<>();
    try {
        String query = "SELECT * FROM users WHERE status = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setBoolean(1, status);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(
                    rs.getString("login"),
                    rs.getString("password"),
                    Role.valueOf(rs.getString("role")),
                    rs.getBoolean("status")
            );
            users.add(user);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return users;
}

@Override
public List<User> listUserByRole(Role role) {
    List<User> users = new ArrayList<>();
    try {
        String query = "SELECT * FROM users WHERE role = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, role.name());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(
                    rs.getString("login"),
                    rs.getString("password"),
                    Role.valueOf(rs.getString("role")),
                    rs.getBoolean("status")
            );
            users.add(user);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return users;
}



}
