package Model;

import java.util.Date;

public class Pagamento {
	public Pagamento(Cliente email, Date data , double sconto, double prezzo_totale) {
		this.email = email;
		this.data = data;
		this.sconto = sconto;
		this.prezzo_totale = prezzo_totale;
	}
    public Cliente getEmail() {
		return email;
	}
	public void setEmail(Cliente email) {
		this.email = email;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getSconto() {
		return sconto;
	}
	public void setSconto(double sconto) {
		this.sconto = sconto;
	}
	public double getPrezzo_T() {
		return prezzo_totale;
	}
	public void setPrezzo_t(double prezzo_totale) {
		this.prezzo_totale = prezzo_totale;
	}


private Cliente email;
private Date data;
private double sconto;
private double prezzo_totale;
}
