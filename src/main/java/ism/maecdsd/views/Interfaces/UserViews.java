package ism.maecdsd.views.Interfaces;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.User;
import ism.maecdsd.views.Views;

public interface UserViews extends Views<User> {


    public User saisitUserClient(Client client);
    public boolean  getStatus();

}
