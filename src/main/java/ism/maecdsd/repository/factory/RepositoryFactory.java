package ism.maecdsd.repository.factory;

import ism.maecdsd.bd.*;
import ism.maecdsd.repository.implement.*;
import ism.maecdsd.repository.interfaces.*;

public class RepositoryFactory {

    private static final boolean useDatabase = true; // false pour Liste

    public static ClientRepository createClientRepository() {
        if (useDatabase) {
            return new ClientRepositoryBd(); 
        } else {
            return new ClientRepositoryListImpl();
        }
    }

    public static ArticleRepository createArticleRepository() {
        if (useDatabase) {
            return new ArticleRepositoryBd();
        } else {
            return new ArticleRepositoryImpl();
        }
    }

    public static DetteRepository createDetteRepository() {
        if (useDatabase) {
            return new DetteRepositoryBd();
        } else {
            return new DetteRepositoryListImpl();
        }
    }

    public static UserRepository createUserRepository() {
        if (useDatabase) {
            return new UserRepositoryBd(); 
        } else {
            return new UserRepositoryListImpl(); 
        }
    }
    public static DemandeDetteRepository createDemandeDetteRepository(boolean useDatabase) {
        if (useDatabase) {
            return new DemandeDetteRepositoryBd();  
        } else {
            return new DemandeDetteRepositoryListImpl();  
        }
    }

}
