package ism.maecdsd.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import ism.maecdsd.entity.Client;
import ism.maecdsd.entity.DemandeDette;
import ism.maecdsd.entity.Dette;
import ism.maecdsd.repository.interfaces.DetteRepository;

public class DetteRepositoryListImpl extends RepositoryListImpl<Dette> implements DetteRepository  {

    

@Override
public Dette findDette(int id) {
    
   for (int i = 0; i <list.size() ; i++) {
       if (list.get(i).getId()==id) {
        return list.get(i);
       }
   }return  null;
}

@Override
public List<Dette> listDetteNonSolde(){
    return list.stream().filter(dette->dette.getMontantRestant()==0).collect(Collectors.toList());
}
@Override
public List<Dette> listDetteNonSolde(Client client){

    return client.getDettes().stream().filter(dette->dette.getMontantRestant()==0).collect(Collectors.toList());
}

@Override
public  List<Dette> listDettesSoldes(){
    return list.stream().filter(dette->dette.getMontantRestant()!=0).collect(Collectors.toList());
    
}

@Override
public void update(DemandeDette demandeDette) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
}




}
