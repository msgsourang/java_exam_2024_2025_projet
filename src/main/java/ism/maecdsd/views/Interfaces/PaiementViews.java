package ism.maecdsd.views.Interfaces;

import java.util.List;

import ism.maecdsd.entity.Dette;
import ism.maecdsd.entity.Paiement;

public interface PaiementViews {

      public Paiement saisitPaiement(Dette dette);
        public void lister(List<Paiement>paiements);
}
