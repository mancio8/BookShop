package Model;

import java.util.Date;

public class CartaFed {
public CartaFed(String codice, Date datas , int punti, Cliente e_cliente){
	this.codice = codice;
	this.datas = datas;
	this.punti = punti;
	this.e_cliente = e_cliente;
}



public String getCodice() {
	return codice;
}
public void setCodice(String codice) {
	this.codice = codice;
}
public Date getData() {
	return datas;
}
public void setData(Date datas) {
	this.datas = datas;
}
public Cliente getCliente() {
	return e_cliente;
}
public void setCliente(Cliente e_cliente) {
	this.e_cliente= e_cliente;
}
public int getPunti() {
	return punti;
}
public void setPunti(int punti) {
	this.punti=punti;
}
private String codice;
private Date datas;
private int punti;
private Cliente e_cliente;
}
