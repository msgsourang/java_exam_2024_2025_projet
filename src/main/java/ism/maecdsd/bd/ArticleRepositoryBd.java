package ism.maecdsd.bd;

import ism.maecdsd.bd.DatabaseConnection;
import ism.maecdsd.entity.Article;
import ism.maecdsd.repository.interfaces.ArticleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryBd implements ArticleRepository {

    private Connection connection = DatabaseConnection.getConnection();

    @Override
    public void insert(Article article) {
        try {
            String query = "INSERT INTO articles (libelle, qte_stock, montant) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, article.getLibelle());
            stmt.setInt(2, article.getQteStock());
            stmt.setInt(3, article.getMontant());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> lister() {
        List<Article> articles = new ArrayList<>();
        try {
            String query = "SELECT * FROM articles";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Article article = new Article(
                        rs.getInt("qte_stock"),
                        rs.getString("libelle"),
                        rs.getInt("montant")
                );
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
public List<Article> getArticlesDispo() {
    List<Article> articles = new ArrayList<>();
    try {
        String query = "SELECT * FROM articles WHERE qte_stock > 0";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Article article = new Article(
                    rs.getInt("qte_stock"),
                    rs.getString("libelle"),
                    rs.getInt("montant")
            );
            articles.add(article);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return articles;
}

@Override
public Article getArticlesByLibelle(String libelle) {
    try {
        String query = "SELECT * FROM articles WHERE libelle = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, libelle);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Article(
                    rs.getInt("qte_stock"),
                    rs.getString("libelle"),
                    rs.getInt("montant")
            );
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
}
