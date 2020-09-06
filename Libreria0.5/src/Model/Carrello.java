package Model;

import java.util.LinkedList;

public class Carrello {
public Carrello(String idC , String prodotto, int quantita, double prezzoTot, String email) {
	this.idC = idC;
	this.prodotto = prodotto;
	this.quantita= quantita;
	this. prezzoTot = prezzoTot;
	this.email = email;
}

public int getQuantita(){
	return quantita;
}
public void setQuantita(int quantita){
	this.quantita= quantita;
}
public String getIdC() {
	return idC;
}
public void setIdC(String idC) {
	this.idC = idC;
}
public String getProdotto() {
	return prodotto;
}
public void setProdotto(String prodotto) {
	this.prodotto = prodotto;
}

public double getPrezzoTot() {
		return prezzoTot;
	}
	public void setPrezzoTot(double prezzoTot) {
		this.prezzoTot = prezzoTot;
	}
	public String getEmail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
private String idC;
private String prodotto;
private double prezzoTot;
private String email;
private int quantita;
}
