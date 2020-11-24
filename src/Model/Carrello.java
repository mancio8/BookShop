package Model;

public class Carrello {
	public Carrello(String idC , Cliente cliente) {
	this.idC = idC;
	this.cliente = cliente;
	}

	public String getIdC() {
	return idC;
}
	public void setIdC(String idC) {
	this.idC = idC;
}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente email) {
		this.cliente = email;
	}


	protected String idC;
	protected Cliente cliente;
}
