package ism.maecdsd.services.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.User;
import ism.maecdsd.repository.interfaces.DemandeDetteRepository;
import ism.maecdsd.services.interfaces.DemandeDetteService;

public class DemandeDetteServiceImpl implements DemandeDetteService {

    private final DemandeDetteRepository demandeDetteRepository;

    public DemandeDetteServiceImpl(DemandeDetteRepository demandeDetteRepository) {
        this.demandeDetteRepository = demandeDetteRepository;
    }

    @Override

     public void add(DemandeDette demandeDette){
        demandeDetteRepository.insert(demandeDette);
    }
    @Override

      public List<DemandeDette> getAll(){
       return  demandeDetteRepository.lister();
    }
    @Override

    public List<DemandeDette> getAllDemandeDettesByStatus(Boolean status){
        return  demandeDetteRepository.listerDemandeDetteParStatus(status);
     }
    
      @Override
    public List<DemandeDette> getAllDemandeDettesByClient(User client) {
        return demandeDetteRepository.lister().stream()
            .filter(d -> d.getClient().equals(client)) 
            .collect(Collectors.toList());
    }

    @Override
    public DemandeDette getDemandeDetteById(int id) {
        Optional<DemandeDette> demande = demandeDetteRepository.lister().stream()
            .filter(d -> d.getId() == id)
            .findFirst();
        return demande.orElse(null);
    }
    @Override
    public void update(DemandeDette demandeDette) {
        demandeDetteRepository.update(demandeDette); 
    }

}
