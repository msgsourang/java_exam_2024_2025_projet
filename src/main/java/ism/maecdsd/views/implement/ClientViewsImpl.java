package ism.maecdsd.views.implement;
import java.util.List;
import  java.util.Scanner;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.User;
import ism.maecdsd.views.Interfaces.ClientViews;
import ism.maecdsd.views.Interfaces.UserViews;

public class ClientViewsImpl implements ClientViews {


    private final  Scanner sc;
    private final UserViews userViews;
  

    public ClientViewsImpl( Scanner sc, UserViews userViews) {
      
        this.sc = sc;
        this.userViews = userViews;
    }

    

    

   
    @Override
    public Client saisie(){
        System.out.println("Surname :");
        String surname=sc.next();
        System.out.println("Telephone :");
        String telephone=sc.next();
        System.out.println("Adresse :");
        String adresse=sc.next();
        System.out.println("Voulez vous creez un compte utilisateur au client?");
        System.out.println("1 : Oui");
        System.out.println("2 : Non");
        System.out.print("Choisissez une option: ");
        int choix=sc.nextInt();
        switch (choix) {
            case 1 -> {
                Client client=new Client(surname, telephone, adresse);
                User user=userViews.saisitUserClient(client);
                client.setUser(user);
                return client;
            }
            case 2 -> {
                Client client2=new Client(surname, telephone, adresse);
                return client2;
            }
            default -> {
            }
        }
return null;


    }

    @Override
    public void lister(List<Client> clients){
        for (int i = 0; i <clients.size(); i++) {
            Client client=clients.get(i);
            System.out.println(client);
        }
    }

    
    @Override
public void getClientByPhone(Client client){
    
    if(client!= null){
        System.out.println(client);
    }else{
        System.out.println("Le client n'existe pas");
    }

}

@Override
public String saisitTel(){
    System.out.println("Le tel :");
    String tel2=sc.next();
    return tel2;
}
}
