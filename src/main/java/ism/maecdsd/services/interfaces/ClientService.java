package ism.maecdsd.services.interfaces;

import java.util.List;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.services.ServicesI;

public interface ClientService extends ServicesI<Client>{

      public List<Client> getAllClientsUsers();
      public List<Client> getAllClientsNotUsers();
      public Client findClientbyTel(String tel);
      public List<Dette> getClientsDetteNonSolde(Client client);
}
