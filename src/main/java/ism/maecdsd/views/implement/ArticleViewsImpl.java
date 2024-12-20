package ism.maecdsd.views.implement;

import java.util.List;
import java.util.Scanner;

import ism.maecdsd.entity.Article;
import ism.maecdsd.views.Interfaces.ArticleViews;

public class ArticleViewsImpl implements ArticleViews {
    private final Scanner sc;

    public ArticleViewsImpl(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public Article saisie(){

        System.out.println("Quelle quantité ?");
        int qteStock=sc.nextInt();
        System.out.println("Le nom de l'article :");
        String libelle=sc.next();
        System.out.println("Prix :");
        int montant=sc.nextInt();
        Article article=new Article(qteStock, libelle, montant);
        return article;
    
    }


    @Override
    public void lister(List<Article> articles){
        for (int i = 0; i <articles.size(); i++) {
            Article article=articles.get(i);
            System.out.println(article);
        }
    }
}
