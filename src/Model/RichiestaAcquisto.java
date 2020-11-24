package Model;

import java.util.Date;

public class RichiestaAcquisto {
public RichiestaAcquisto(String idA , Date data , double prezzo, String tipoPagamento,  Dipendente d, Cliente c) {
	this.idA = idA;
	this.prezzo = prezzo;
	this.data = data;
	this.tipoPagamento = tipoPagamento;

	this.d = d;
	this.c = c;
}

public Cliente getC() {
	return c;
}
public void setC(Cliente c) {
	this.c = c;
}
public String getTipoPagamento() {
	return tipoPagamento;
}
public void setTipoPagamento(String tipoPagamento) {
	this.tipoPagamento = tipoPagamento;
}


public String getIdA() {
	return idA;
}
public void setIdA(String idA) {
	this.idA = idA;
}
public Date getData() {
	return data;
}
public void setData(Date data) {
	this.data = data;
}
public Dipendente getDipendente() {
	return d;
}
public void setDipendente(Dipendente d) {
	this.d = d;
}
public double getPrezzo() {
	return prezzo;
}
public void setPrezzo(double prezzo) {
	prezzo = prezzo - sconto;
	this.prezzo = prezzo;
}


private String tipoPagamento;
private double sconto;
private String idA;
private Date data;
private double prezzo;
private Dipendente d;
private Cliente c;
}
