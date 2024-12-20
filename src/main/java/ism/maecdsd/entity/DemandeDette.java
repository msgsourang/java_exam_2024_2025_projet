package ism.maecdsd.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@EqualsAndHashCode
@ToString
public class DemandeDette {

    private int id;
    private String date;
    private float montant;
    private Client client;
    private boolean  status; 

    
    private List<Article> articles=new ArrayList<>();



    public DemandeDette(int id, String date, float montant, Client client, boolean status) {
        this.id = id;
        this.date = date;
        this.montant = montant;
        this.client = client;
        this.status = status;
    }

    public DemandeDette(String date, float montant, Client client, boolean status) {
        this.date = date;
        this.montant = montant;
        this.client = client;  
        this.status = status;

}

    public DemandeDette(String date2, float montant2, User user, boolean status2) {
        //TODO Auto-generated constructor stub
    }

    public DemandeDette(String date2, Float montant2, Client client2) {
        //TODO Auto-generated constructor stub
    }
}
