package Model;

public class Pagamento {
	public Pagamento(String idP, String tipo , double sconto, Carrello carrello) {
		this.idP = idP;
		this.tipo = tipo;
		this.sconto = sconto;
	}
    public String getIdP() {
		return idP;
	}
	public void setIdP(String idP) {
		this.idP = idP;
	}
    public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getSconto() {
		return sconto;
	}
	public void setSconto(double sconto) {
		this.sconto = sconto;
	}
    public Carrello getCarrello() {
	return carrello;
    }
    public void setCarrello(Carrello carrello) {
	this.carrello = carrello;
    }
private Carrello carrello;
private String idP;
private String tipo;
private double sconto;
}
