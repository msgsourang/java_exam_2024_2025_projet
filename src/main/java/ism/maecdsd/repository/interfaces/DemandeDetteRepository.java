package ism.maecdsd.repository.interfaces;

import java.util.List;

import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.repository.Repository;

public interface DemandeDetteRepository extends Repository<DemandeDette> {

        public List<DemandeDette> listerDemandeDetteParStatus(boolean status);

        public void update(DemandeDette demandeDette);


}
