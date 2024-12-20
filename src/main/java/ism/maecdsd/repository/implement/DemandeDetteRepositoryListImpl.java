package ism.maecdsd.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.repository.interfaces.DemandeDetteRepository;

public class DemandeDetteRepositoryListImpl extends RepositoryListImpl<DemandeDette> implements DemandeDetteRepository {

    @Override
    public List<DemandeDette> listerDemandeDetteParStatus(boolean status){
        return list.stream().filter(DemandeDette->DemandeDette.isStatus()==status).collect(Collectors.toList());
    }

    @Override
    public void update(DemandeDette demandeDette) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
