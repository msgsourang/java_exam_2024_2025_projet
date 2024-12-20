package ism.maecdsd.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import ism.maecdsd.entity.Article;
import ism.maecdsd.repository.interfaces.ArticleRepository;

public class ArticleRepositoryImpl extends RepositoryListImpl<Article> implements ArticleRepository {


    @Override
    public List<Article> getArticlesDispo(){

        return list.stream().filter(article->article.getQteStock()!=0).collect(Collectors.toList());
    }

    @Override
    public Article getArticlesByLibelle(String libelle){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLibelle().equalsIgnoreCase(libelle)) {
                return list.get(i);
            }
        }return null;
    }
}
