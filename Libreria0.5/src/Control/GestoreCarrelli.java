package Control;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import Model.Carrello;
import Model.Prodotto;

public class GestoreCarrelli {
    public static GestoreCarrelli getInstance() {
        if(instance == null) {
            instance = new GestoreCarrelli();
        }
        return instance;
    }
    private GestoreCarrelli() {
        persistenza = GestoreDB.getInstance();
        carrelli = persistenza.getCarrello();
    }
    /*public void cambiaPrezzo(Carrello c) {
        prodotti = c.getProdotto();
        Iterator<Prodotto> it = prodotti.iterator();
        while(it.hasNext()) {
            double pp = it.next().getPrezzo();
            c.setPrezzoTot(c.getPrezzoTot()+ pp);
            persistenza.updateCarrello(c);
            break;
        }
    }*/
    /*public void cambiaQuantita(Carrello c,Prodotto p) {
        prodotti = c.getProdotto();
        Iterator<Prodotto> it = prodotti.iterator();
        while(it.hasNext()) {
            p = it.next();
            p.setQuantita(p.getQuantita()-1);
            persistenza.updateProdotto(p);
            break;
        }
    }*/
    private static GestoreCarrelli instance;
    private GestoreDB persistenza;
    private List<Carrello> carrelli;
    private List<Prodotto> prodotti;
}
