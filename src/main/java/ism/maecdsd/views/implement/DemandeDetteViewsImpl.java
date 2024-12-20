package ism.maecdsd.views.implement;

import java.util.List;
import java.util.Scanner;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.User;
import ism.maecdsd.views.Interfaces.DemandeDetteViews;

public class DemandeDetteViewsImpl implements DemandeDetteViews {

 private final Scanner sc;

    public DemandeDetteViewsImpl(Scanner sc) {
        this.sc = sc;
    }


@Override
public DemandeDette saisie(){

System.out.println("La date :");
String date=sc.next();
System.out.println("Montant de la dette :");
Float montant=sc.nextFloat();
Client client = new Client(0); 
    return new DemandeDette(date, montant, client);
}


@Override
public void lister(List<DemandeDette> demandeDettes){

    for (int i = 0; i <demandeDettes.size() ; i++) {
        System.out.println(demandeDettes.get(i).toString());
    }
}



    public DemandeDette saisirDemandeDette(float montant, User user) {
        System.out.println("Demande de dette");
        System.out.print("Date de la demande : ");
        String date = sc.next(); 
        System.out.print("Montant de la demande : " + montant);
        System.out.print("Entrez l'état de la demande (true pour en cours, false pour annulé) : ");
        boolean status = sc.nextBoolean();

        return new DemandeDette(date, montant, user, status);  
}
}


