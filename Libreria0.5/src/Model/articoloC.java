package Model;

public class articoloC extends Prodotto {

	public articoloC(String id, double prezzo, int quantita,Proprietario proprietario, String tipo) {
		super(id, prezzo, quantita, proprietario);
		this.tipo = tipo;
	}
public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
private String tipo;
}
