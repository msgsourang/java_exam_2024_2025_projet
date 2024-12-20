package ism.maecdsd.services.implement;

import java.util.List;

import ism.maecdsd.entity.Article;
import ism.maecdsd.repository.interfaces.ArticleRepository;
import ism.maecdsd.services.interfaces.ArticleService;

public class ArticleServiceImpl implements ArticleService {

    protected  final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @Override

    public void add(Article article){

        articleRepository.insert(article);
    }

    @Override 
    public List<Article> list(){
        return articleRepository.lister();
    }

    
    @Override 
    public List<Article> listArticlesDispo(){
        return articleRepository.getArticlesDispo();
    }

    @Override

    public  void updateQteStock(int montant, Article article){

        article.setQteStock(montant);

    }

    @Override
    public Article getArticleByLibelle(String libelle){
        return articleRepository.getArticlesByLibelle(libelle);
    }
    
    





}
