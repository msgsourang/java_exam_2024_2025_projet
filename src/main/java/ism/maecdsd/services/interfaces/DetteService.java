package ism.maecdsd.services.interfaces;

import java.util.List;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.services.ServicesI;

public interface DetteService extends ServicesI<Dette> {

        public List<Dette> getAllDetteNonSolde();
        public Dette findDetteId(int id);
        public List<Dette> getAllDetteNonSolde(Client client);
        public void ArchiverAllDetteNonSolde(List<Dette> dettes);
        public List<Dette> getDetteSolde();


}
