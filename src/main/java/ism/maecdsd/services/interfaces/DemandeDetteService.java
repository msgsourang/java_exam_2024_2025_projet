package ism.maecdsd.services.interfaces;

import java.util.List;

import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.User;
import ism.maecdsd.services.ServicesI;

public interface DemandeDetteService extends ServicesI<ism.maecdsd.entity.DemandeDette> {

        public List<DemandeDette> getAllDemandeDettesByStatus(Boolean status);
        List<DemandeDette> getAllDemandeDettesByClient(User client);
        DemandeDette getDemandeDetteById(int id);
        void update(DemandeDette demandeDette);

}
