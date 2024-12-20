package ism.maecdsd.repository.interfaces;

import java.util.List;

import ism.maecdsd.entity.Client;
import ism.maecdsd.repository.Repository;



public interface ClientRepository extends Repository<Client> {

   public List<Client>  listClientsUser();
   public List<Client>  listClientsNotUser();
   public Client getClientByPhone(String tel);


}
