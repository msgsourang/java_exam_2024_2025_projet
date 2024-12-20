package ism.maecdsd.repository.implement;

import java.util.ArrayList;
import java.util.List;

import ism.maecdsd.repository.Repository;

public class RepositoryListImpl <T> implements Repository<T> {

    protected  final List<T> list=new ArrayList<>();
    
    @Override
    public  void insert(T data){
        list.add(data);

    }
    @Override
    public List<T> lister(){
        return list;
    }



}
