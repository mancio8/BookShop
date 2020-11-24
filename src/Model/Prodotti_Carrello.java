package Model;

public class Prodotti_Carrello extends Carrello {

    public Prodotti_Carrello (String idC, Cliente cliente, Prodotto prodotto, int quantita, Prodotto prezzo_unitario, double prezzoTot){
        super(idC, cliente);
        this.prodotto = prodotto;
        this.quantita= quantita;
        this.prezzo_unitario = prezzo_unitario;
        this. prezzoTot = prezzoTot;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }
    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public double getPrezzoTot() {
        return prezzoTot;
    }
    public void setPrezzoTot(double prezzoTot) {
        this.prezzoTot = prezzoTot;
    }

    public Prodotto getPrezzo_u() {
        return prezzo_unitario;
    }
    public void setPrezzo_u(Prodotto prezzo_unitario) {
        this.prezzo_unitario = prezzo_unitario;
    }

    public int getQuantita(){
        return quantita;
    }
    public void setQuantita(int quantita){
        this.quantita= quantita;
    }

    private Prodotto prodotto;
    private Prodotto prezzo_unitario;
    private double prezzoTot;
    private int quantita;
}
