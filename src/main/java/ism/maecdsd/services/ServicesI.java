package ism.maecdsd.services;

import java.util.List;


public interface ServicesI <T> {
    
       public void add(T data);

       public List<T> getAll();
    
    }
