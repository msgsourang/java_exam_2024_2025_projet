package ism.maecdsd;

import java.util.List;
import java.util.Scanner;

import ism.maecdsd.entity.Article;
import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.entity.Paiement;
import ism.maecdsd.entity.Role;
import ism.maecdsd.entity.User;
import ism.maecdsd.repository.factory.RepositoryFactory;
import ism.maecdsd.repository.implement.PaiementRepositoryImpl;
import ism.maecdsd.repository.interfaces.ArticleRepository;
import ism.maecdsd.repository.interfaces.ClientRepository;
import ism.maecdsd.repository.interfaces.DemandeDetteRepository;
import ism.maecdsd.repository.interfaces.DetteRepository;
import ism.maecdsd.repository.interfaces.UserRepository;
import ism.maecdsd.services.implement.ArticleServiceImpl;
import ism.maecdsd.services.implement.ClientServiceImpl;
import ism.maecdsd.services.implement.DemandeDetteServiceImpl;
import ism.maecdsd.services.implement.DetteServiceImpl;
import ism.maecdsd.services.implement.PaiementServiceImpl;
import ism.maecdsd.services.implement.UserServiceImpl;
import ism.maecdsd.services.interfaces.ArticleService;
import ism.maecdsd.services.interfaces.ClientService;
import ism.maecdsd.services.interfaces.DemandeDetteService;
import ism.maecdsd.services.interfaces.DetteService;
import ism.maecdsd.services.interfaces.PaiementService;
import ism.maecdsd.services.interfaces.UserService;
import ism.maecdsd.views.Interfaces.ArticleViews;
import ism.maecdsd.views.Interfaces.ClientViews;
import ism.maecdsd.views.Interfaces.DetteViews;
import ism.maecdsd.views.Interfaces.PaiementViews;
import ism.maecdsd.views.Interfaces.UserViews;
import ism.maecdsd.views.implement.ArticleViewsImpl;
import ism.maecdsd.views.implement.ClientViewsImpl;
import ism.maecdsd.views.implement.DemandeDetteViewsImpl;
import ism.maecdsd.views.implement.DetteViewsImpl;
import ism.maecdsd.views.implement.PaiementViewsImpl;
import ism.maecdsd.views.implement.UserViewsImpl;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean useDatabase = true;  
        DemandeDetteRepository demandeDetteRepository = RepositoryFactory.createDemandeDetteRepository(useDatabase);
        DemandeDetteService demandeDetteService = new DemandeDetteServiceImpl(demandeDetteRepository);
        DemandeDetteViewsImpl demandeDetteViews = new DemandeDetteViewsImpl(sc);
        
        ClientRepository clientRepository = RepositoryFactory.createClientRepository();
        ClientService clientService = new ClientServiceImpl(clientRepository);
        UserRepository userRepository = RepositoryFactory.createUserRepository();
        UserService userService = new UserServiceImpl(userRepository);
        DetteRepository detteRepository = RepositoryFactory.createDetteRepository();
        DetteService detteService = new DetteServiceImpl(detteRepository);
        PaiementRepositoryImpl paiementRepository = new PaiementRepositoryImpl();
        PaiementService paiementService = new PaiementServiceImpl(paiementRepository);
        ArticleRepository articleRepository = RepositoryFactory.createArticleRepository();
        ArticleService articleService = new ArticleServiceImpl(articleRepository);
        
        UserViews userViews = new UserViewsImpl(sc);
        ClientViews clientViews = new ClientViewsImpl(sc, userViews);
        PaiementViews paiementViews = new PaiementViewsImpl(sc);
        ArticleViews articleViews = new ArticleViewsImpl(sc);
        DetteViews detteViews = new DetteViewsImpl(sc, articleViews, paiementViews);

        int id = 0;

        User user0 = new User("boutiquier", "pass123", Role.BOUTIQUIER, true);
        User user1 = new User("admin", "pass123", Role.ADMIN, true);
        userService.add(user0);
        userService.add(user1);

        System.out.println("---- Connexion ----");
        System.out.println("Login");
        String login = sc.next();
        System.out.println("Password");
        String password = sc.next();
        User userConnect = userService.getUserByLoginPasword(login, password);

        
        if (userConnect != null && userConnect.getRole() == Role.BOUTIQUIER) {
            System.out.println("Bienvenue " + userConnect.toString());
            int choix0 = 0;
            do {
                System.out.println("---- Interface Boutiquier ----");
                System.out.println("1- Creer un client");
                System.out.println("2- Lister les clients");
                System.out.println("3- Lister les clients avec compte");
                System.out.println("4- Lister les clients sans compte");
                System.out.println("5- Rechercher Clients par telephone");
                System.out.println("6- Creer dette");
                System.out.println("7- Enregistrer paiement pour Dette");
                System.out.println("8- Lister Dettes non soldees d'un client");
                System.out.println("9- Lister les demandes de dettes");
                System.out.println("10- Lister les demandes de dettes par statut");
                System.out.println("11- Deconnexion");
                System.out.print("Choisissez une option: ");
                choix0 = sc.nextInt();

                switch (choix0) {
                    case 1 -> {
                        Client client2 = clientViews.saisie();
                        clientService.add(client2);
                        if (client2.getUser() != null) {
                            userService.add(client2.getUser());
                        }
                    }
                    case 2 -> {
                        clientViews.lister(clientService.getAll());
                    }
                    case 3 -> {
                        clientViews.lister(clientService.getAllClientsUsers());
                    }
                    case 4 -> {
                        clientViews.lister(clientService.getAllClientsNotUsers());
                    }
                    case 5 -> {
                        String tel2 = clientViews.saisitTel();
                        clientViews.getClientByPhone(clientService.findClientbyTel(tel2));
                    }
                    case 6 -> {
                        System.out.println("Le client a qui attribuer existe?");
                        System.out.println("1 : Oui");
                        System.out.println("2 : Non");
                        System.out.print("Choisissez une option: ");
                        int choix4 = sc.nextInt();
                        if (choix4 == 1) {
                            System.out.println("Entrez le tel du client");
                            String tel2 = sc.next();
                            System.out.println(clientService.findClientbyTel(tel2));
                            if (clientService.findClientbyTel(tel2) != null) {
                                detteService.add(detteViews.saisitDette(clientService.findClientbyTel(tel2), id));
                            }
                        } else {
                            Client client2 = clientViews.saisie();
                            clientService.add(client2);
                            if (client2.getUser() != null) {
                                userService.add(client2.getUser());
                            }
                            detteService.add(detteViews.saisitDette(client2, id));
                        }
                    }
                    case 7 -> {
                        System.out.println("Id de la dette");
                        int id2 = sc.nextInt();
                        Dette detteX = detteService.findDetteId(id2);
                        if (detteX != null) {
                            Paiement paiement = paiementViews.saisitPaiement(detteX);
                            detteX.setPaiement(paiement);
                            paiementService.add(paiement);
                        } else {
                            System.out.println("La dette n'existe pas");
                        }
                    }
                    case 8 -> {
                        System.out.println("Entrez le tel du client");
                        String tel2 = sc.next();
                        System.out.println(clientService.findClientbyTel(tel2));
                        Client client4 = clientService.findClientbyTel(tel2);
                        if (client4 != null) {
                            detteViews.listerDetteNonSolde(detteService.getAllDetteNonSolde(client4));
                        } else {
                            System.out.println("Le client n'existe pas");
                        }
                    }
                    case 9 -> {
                        System.out.println(demandeDetteService.getAll());
                    }
                    case 10 -> {
                        System.out.println("Filtrer demande par état 1 En cours/2 annulé");
                        int tel2 = sc.nextInt();
                        switch (tel2) {
                            case 1 -> System.out.println(demandeDetteService.getAllDemandeDettesByStatus(true));
                            case 2 -> System.out.println(demandeDetteService.getAllDemandeDettesByStatus(false));
                            default -> System.out.println("Erreur");
                        }
                    }
                    case 11 -> {
                        System.out.println("Déconnexion réussie.");
                        break;
                    }
                    default -> {
                    }
                }
            } while (choix0 != 11);

        } else if (userConnect != null && userConnect.getRole() == Role.ADMIN) {
            System.out.println("Bienvenue " + userConnect.toString());

            int choix0 = 0;
            do {
                System.out.println("---- Interface Admin ----");
                System.out.println("1- Créer un compte utilisateur à un client n’ayant pas de compte");
                System.out.println("2- Créer un compte utilisateur avec un rôle Boutiquier ou Admin");
                System.out.println("3- Désactiver/Activer un compte utilisateur");
                System.out.println("4- Afficher les comptes utilisateurs actifs ou par rôle.");
                System.out.println("5- Créer article");
                System.out.println("6- Lister des articles et filtrer par disponibilité (qteStock != 0)");
                System.out.println("7- Mettre à jour la qté en stock d’un article");
                System.out.println("8- Archiver les dettes soldées");
                System.out.println("9- Deconnexion");
                System.out.print("Choisissez une option: ");

                choix0 = sc.nextInt();

                switch (choix0) {
                    case 1 -> {
                        System.out.println("Entrez le tel du client");
                        String tel2 = sc.next();
                        System.out.println(clientService.findClientbyTel(tel2));
                        Client client4 = clientService.findClientbyTel(tel2);
                        if (client4.getUser() == null) {
                            userService.add(userViews.saisitUserClient(client4));
                        } else {
                            System.out.println("Le client a déjà un compte");
                        }
                    }
                    case 2 -> userService.add(userViews.saisie());
                    case 3 -> {
                        System.out.println("Connexion");
                        System.out.println("Entrez login");
                        String login2 = sc.next();
                        System.out.println("Entrez Password");
                        String password2 = sc.next();
                        User userConnect2 = userService.getUserByLoginPasword(login2, password2);

                        if (userConnect2 != null) {
                            userService.changeStatus(userViews.getStatus(), userConnect2);
                        } else {
                            System.out.println("Ce user n'existe pas");
                        }
                    }
                    case 4 -> {
                        System.out.println("Vous voulez afficher les users par 1/role 2/status");
                        int choix5 = sc.nextInt();
                        switch (choix5) {
                            case 1 -> {
                                System.out.println("1/ Client");
                                System.out.println("2/ Boutiquier");
                                System.out.println("3/ Admin");
                                int choix6 = sc.nextInt();
                                switch (choix6) {
                                    case 1 -> userViews.lister(userService.getAllUserByRole(Role.CLIENT));
                                    case 2 -> userViews.lister(userService.getAllUserByRole(Role.BOUTIQUIER));
                                    case 3 -> userViews.lister(userService.getAllUserByRole(Role.ADMIN));
                                    default -> throw new AssertionError();
                                }
                            }
                            case 2 -> {
                                System.out.println("1: Actif");
                                System.out.println("2: Inactif");
                                System.out.print("Choisissez une option: ");
                                int choix7 = sc.nextInt();
                                switch (choix7) {
                                    case 1 -> userViews.lister(userService.getAllUserByStaus(true));
                                    case 2 -> userViews.lister(userService.getAllUserByStaus(false));
                                    default -> throw new AssertionError();
                                }
                            }
                            default -> {
                            }
                        }
                    }
                    case 5 -> articleService.add(articleViews.saisie());
                    case 6 -> articleViews.lister(articleService.listArticlesDispo());
                    case 7 -> {
                        System.out.println("Entrez le nom de l'article");
                        String libelle = sc.next();
                        Article article2 = articleService.getArticleByLibelle(libelle);
                        if (article2 != null) {
                            System.out.println("Entrez la quantité en stock");
                            int montant = sc.nextInt();
                            articleService.updateQteStock(montant, article2);
                        } else {
                            System.out.println("L'article n'existe pas");
                        }
                    }
                    case 8 -> detteService.ArchiverAllDetteNonSolde(detteService.getDetteSolde());
                    default -> throw new AssertionError();
                    case 9 -> {
                        System.out.println("Déconnexion réussie.");
                        break;
                    }

                }

            } while (choix0 != 9);
        } else if (userConnect != null && userConnect.getRole() == Role.CLIENT) {
            System.out.println("Bienvenue " + userConnect.toString());
            int choix0 = 0;
            do {
                System.out.println("---- Interface Client ----");
                System.out.println("1- Lister mes dettes non soldées");
                System.out.println("2- Faire une demande de dette");
                System.out.println("3- Lister mes demandes de dette");
                System.out.println("4- Lister mes demandes de dette par état");
                System.out.println("5- Envoyer une relance pour une demande annulée");
                System.out.println("6- Deconnexion");
                System.out.print("Choisissez une option: ");
                choix0 = sc.nextInt();

                switch (choix0) {
                    case 1 -> {
                        System.out.println("Entrez votre téléphone pour lister vos dettes non soldées");
                        String tel2 = sc.next();
                        Client client = clientService.findClientbyTel(tel2);
                        if (client != null) {
                            List<Dette> dettesNonSoldees = clientService.getClientsDetteNonSolde(client);
                            detteViews.listerDetteNonSolde(dettesNonSoldees);
                        } else {
                            System.out.println("Client non trouvé");
                        }
                    }
                    case 2 -> {
                        System.out.println("Faire une demande de dette");
                        System.out.println("Entrez le montant de la dette : ");
                        float montant = sc.nextFloat();
                        DemandeDette demandeDette = demandeDetteViews.saisirDemandeDette(montant, userConnect);
                        demandeDetteService.add(demandeDette);
                    }
                    case 3 -> {
                        System.out.println("Lister mes demandes de dette");
                        List<DemandeDette> demandes = demandeDetteService.getAllDemandeDettesByClient(userConnect);
                        for (DemandeDette demande : demandes) {
                            System.out.println(demande.toString());
                        }
                    }
                    case 4 -> {
                        System.out.println("Filtrer demande par état (1 : En cours / 2 : Annulé)");
                        System.out.println("1 : En cours");
                        System.out.println("2 : Annulé");
                        System.out.print("Choisissez une option: ");
                        int etat = sc.nextInt();
                        boolean statut = etat == 1;
                        List<DemandeDette> demandesFiltrees = demandeDetteService.getAllDemandeDettesByStatus(statut);
                        for (DemandeDette demande : demandesFiltrees) {
                            System.out.println(demande.toString());
                        }
                    }
                    case 5 -> {
                        System.out.println("Entrez l'id de la demande annulée à relancer: ");
                        int idRelance = sc.nextInt();
                        DemandeDette demande = demandeDetteService.getDemandeDetteById(idRelance);
                        if (demande != null && !demande.isStatus()) {
                            demande.setStatus(true); 
                            demandeDetteService.update(demande);
                            System.out.println("Demande relancée avec succès.");
                        } else {
                            System.out.println("Demande non trouvée ou déjà en cours.");
                        }
                    }
                    case 6 -> {
                        System.out.println("Déconnexion réussie.");
                        break;
                    }
                    default -> {
                    }
                }
            } while (choix0 != 6);
        } else {
            System.out.println("Identifiants incorrects ou rôle non autorisé.");
        }

        sc.close();
    }
}