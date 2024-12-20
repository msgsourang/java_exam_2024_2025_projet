package ism.maecdsd.views.implement;
import java.util.List;
import java.util.Scanner;

import ism.maecdsd.entity.Dette;
import ism.maecdsd.entity.Paiement;
import ism.maecdsd.views.Interfaces.PaiementViews;

public class PaiementViewsImpl implements PaiementViews {

    private final Scanner sc;

    public PaiementViewsImpl(Scanner sc) {
        this.sc = sc;
    }

    @Override
    public Paiement saisitPaiement(Dette dette){
        System.out.println("Date du paiement(yyyy-dd-mm) :");
        String date=sc.next();
        System.out.println("Le montant du paiement :");
        int x=sc.nextInt();
        Paiement paiement=new Paiement(date, dette, x);
        return paiement;



    }

    @Override
    public void lister(List<Paiement>paiements){
    
    for(int i=0;i<paiements.size();i++){
        System.out.println(paiements.get(i).toString());
    }

    }

   
    }
  
    

