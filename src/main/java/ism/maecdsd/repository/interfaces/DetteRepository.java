package ism.maecdsd.repository.interfaces;

import java.util.List;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.repository.Repository;

public interface DetteRepository extends Repository<Dette> {

    Dette findDette(int id);
    List<Dette> listDetteNonSolde();
    public List<Dette> listDetteNonSolde(Client client);
    List<Dette> listDettesSoldes();
    void update(DemandeDette demandeDette);
}
