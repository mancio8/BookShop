package Model;

import java.util.LinkedList;

public class Proprietario extends Utente {

	public Proprietario(String nome, String cognome, String email, String password, LinkedList<Prodotto> prodotti) {
		super(nome, cognome, email, password);
		this.prodotti = prodotti;
	}
    public LinkedList<Prodotto> getProdotti() {
		return prodotti;
	}
	public void setProdotti(LinkedList<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	public void addProdotti(Prodotto p) {
		prodotti.add(p);
	}
	public void removeProdotti(Prodotto p) {
		prodotti.remove(p);
	}
	public void updateProd(Prodotto p) {
		if(p.getClass().isInstance(Prodotto.class)) {
			
		}
    	}
	private LinkedList<Prodotto> prodotti = new LinkedList<Prodotto>();
}
	

