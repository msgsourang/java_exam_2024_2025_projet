package ism.maecdsd.services.implement;
import java.util.List;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.repository.interfaces.DetteRepository;
import ism.maecdsd.services.interfaces.DetteService;


public class DetteServiceImpl implements DetteService {

    
    private final DetteRepository detteRepository;

    public DetteServiceImpl(DetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }
    @Override
    public void add(Dette dette){
        detteRepository.insert(dette);
    }
    @Override
      public List<Dette> getAll(){
       return  detteRepository.lister();
    }

    @Override
    public List<Dette> getAllDetteNonSolde(){
        return  detteRepository.listDetteNonSolde();
     }
     @Override
    public List<Dette> getAllDetteNonSolde(Client client){
        return  detteRepository.listDetteNonSolde(client);
     }

     @Override
     public Dette findDetteId(int id){

        return detteRepository.findDette(id);
     }

     @Override
     public void ArchiverAllDetteNonSolde(List<Dette> dettes){

        for (int i = 0; i < dettes.size(); i++) {
            dettes.get(i).setStatus(false);
        }
     }

     @Override
     public List<Dette> getDetteSolde(){
        return detteRepository.listDettesSoldes();
     }


    

}
