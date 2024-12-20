package ism.maecdsd.services.implement;

import java.util.List;

import ism.maecdsd.entity.Paiement;
import ism.maecdsd.repository.implement.PaiementRepositoryImpl;
import ism.maecdsd.services.interfaces.PaiementService;

public class PaiementServiceImpl implements PaiementService {

    private final PaiementRepositoryImpl paiementRepositoryImpl;

    public PaiementServiceImpl(PaiementRepositoryImpl paiementRepositoryImpl) {
        this.paiementRepositoryImpl = paiementRepositoryImpl;
    }

    @Override
    
    public void add(Paiement paiement){
        paiementRepositoryImpl.insert(paiement);
    }
    @Override

    public List<Paiement> getAll(){
        return paiementRepositoryImpl.lister();
    }

    

}
