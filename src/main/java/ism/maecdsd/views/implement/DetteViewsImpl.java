package ism.maecdsd.views.implement;

import java.util.List;
import java.util.Scanner;

import ism.maecdsd.entity.Article;
import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.entity.Paiement;
import ism.maecdsd.views.Interfaces.ArticleViews;
import ism.maecdsd.views.Interfaces.DetteViews;
import ism.maecdsd.views.Interfaces.PaiementViews;

public class DetteViewsImpl implements DetteViews {

    private final Scanner sc;
    private final ArticleViews articleViews;
    private final PaiementViews paiementViews;
  

    public DetteViewsImpl(Scanner sc,ArticleViews articleViews,PaiementViews paiementViews) {
        this.sc = sc;
        this.articleViews=articleViews;
        this.paiementViews=paiementViews;
    }


   @Override
 public Dette saisitDette(Client client,int id){

            
        System.out.println("Montant de la dette");
        float montant=sc.nextInt();
        System.out.println("Le montant verser:");
        float montantVerser=sc.nextInt();
        System.out.println("Entrer Date");
        String date=sc.next();
        Dette dette=new Dette(id,date, montantVerser, client);
        System.out.println(" Enregistrer un paiement :");
        System.out.println("1 : Oui");
        System.out.println("2 : Non");
        System.out.print("Choisissez une option: ");
        int choix2=sc.nextInt();
        if (choix2==1) {
            Paiement paiement=paiementViews.saisitPaiement(dette);

            dette.setPaiement(paiement);
            dette.setMontantVerser(dette.getMontantVerser()+paiement.getMontant());
            client.addDette(dette);
            return dette;
        }else{
            client.addDette(dette);
            return dette;
        }
    }
    @Override
    public void listerDetteNonSolde(List<Dette> dettes){
        for (int i = 0; i <dettes.size() ; i++) {
            System.out.println(dettes.get(i).toString());
        }
    }


    @Override
    public void lister(List<Dette> dettes){
        for (int i =0;i < dettes.size(); i++){
            System.out.println(dettes.get(i).toString());
        }  
        }

    }



































