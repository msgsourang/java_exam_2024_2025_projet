package ism.maecdsd.views.Interfaces;

import java.util.List;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.Dette;

public interface DetteViews{

    public Dette saisitDette(Client client,int id);
    public void listerDetteNonSolde(List<Dette> dettes);
    public void lister(List<Dette> dettes);
    
}
