package ism.maecdsd.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Client {
    private int id;
    private String surname;
    private String tel;
    private String adresse;
    private float montantDus;
    private User user;

    private List<Dette> dettes=new ArrayList<>();

    public Client(int id, String surname, String tel, String adresse, User user) {
        this.id = id;
        this.surname = surname;
        this.tel = tel;
        this.adresse = adresse;
        this.montantDus=calculMontant();
        this.user = user;
    }
    public Client(String surname, String tel, String adresse) {
        this.surname = surname;
        this.tel = tel;
        this.adresse = adresse;
        this.montantDus=calculMontant();
    }


    public Client(int int1) {
        //TODO Auto-generated constructor stub
    }
    public void addDette(Dette dette){
        dettes.add(dette);
    }

    public float calculMontant(){
        float sum =0;
        for (int i = 0; i <dettes.size(); i++) {
            float x=dettes.get(i).getMontantRestant();
            sum=sum+x;
        } 
        return sum;
    }
    
    }


