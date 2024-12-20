package ism.maecdsd.views.Interfaces;

import ism.maecdsd.entity.Client;
import ism.maecdsd.views.Views;

public interface ClientViews extends Views<Client> {


    public void getClientByPhone(Client client);
    public String saisitTel();

    
}
