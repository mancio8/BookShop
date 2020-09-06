package Model;

import javafx.collections.ObservableArray;

public class Prodotto {
	public Prodotto (String id, double prezzo , int quantita,Proprietario proprietario) {
		this.id = id;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.proprietario = proprietario;
	}

public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
    
protected Proprietario proprietario;
protected String id;
protected double prezzo;
protected int quantita;
}
