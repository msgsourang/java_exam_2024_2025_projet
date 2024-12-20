package ism.maecdsd.bd;

import java.util.List;

import ism.maecdsd.entity.Client;
import ism.maecdsd.repository.interfaces.ClientRepository;

public class ClientRepositoryBd implements ClientRepository {

    @Override
    public void insert(Client data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public List<Client> lister() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lister'");
    }

    @Override
    public List<Client> listClientsUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listClientsUser'");
    }

    @Override
    public List<Client> listClientsNotUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listClientsNotUser'");
    }

    @Override
    public Client getClientByPhone(String tel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientByPhone'");
    }

}
