package ism.maecdsd.repository.interfaces;

import java.util.List;

import ism.maecdsd.entity.Article;
import ism.maecdsd.repository.Repository;

public interface ArticleRepository extends Repository<Article> {

    public List<Article> getArticlesDispo();
    public Article getArticlesByLibelle(String libelle);
}
