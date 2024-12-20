package ism.maecdsd.views.implement;

import java.util.List;
import java.util.Scanner;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.Role;
import ism.maecdsd.entity.User;
import ism.maecdsd.views.Interfaces.UserViews;

public class UserViewsImpl implements UserViews {

private final Scanner sc;

public UserViewsImpl(Scanner sc) {
    this.sc = sc;
}

@Override
public User saisie(){
    System.out.println("Login : ");
    String login=sc.next();
    System.out.println("Mot de passe :");
    String password=sc.next();
    System.out.println("Role :");
    System.out.println("1- ADMIN");
    System.out.println("2- BOUTIQUIER ");
    System.out.println("3- CLIENT ");
    System.out.print("Choisissez une option: ");
    int choix=sc.nextInt();
    Role role;
    switch (choix) {
        case 1 -> role=Role.ADMIN;
        case 2 -> role=Role.BOUTIQUIER;
        case 3 -> role=Role.CLIENT;
        
    
        default -> {
            System.out.println("Erreur");
            return null;
        }

        
    }

    User user=new User(login, password, role, true);
    return user;
}

@Override
public User saisitUserClient(Client client){
    System.out.println("Login");
    String login=sc.next();
    System.out.println("Mot de passe : ");
    String password=sc.next();

    User user=new User(login, password, client, Role.CLIENT, true);
    return user;
}

@Override
public  void lister(List<User> users){

   for (int i = 0; i < users.size(); i++) {
       System.out.println(users.get(i).toString());
   }

     
}
@Override
public boolean  getStatus(){

        System.out.println("1-Activer");
        System.out.println("2- Desactiver");
        int status = sc.nextInt();
    
        return switch (status) {
            case 1 -> true;
            case 2 -> false;
            default -> false;
        };

}








}
