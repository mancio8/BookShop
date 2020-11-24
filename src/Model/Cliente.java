package Model;

public class Cliente extends Utente {

	public Cliente(String nome, String cognome, String email, String password, CartaFed cf,RichiestaAcquisto richiesta, Carrello carrello) {
		super(nome, cognome, email, password);
		this.cf = cf;
		this.richiesta = richiesta;
		this.carrello= carrello;
	}
	public Cliente getCliente(){
		Cliente c = new Cliente(nome,cognome,email,password,cf,null,carrello);
		return c;
	}


	public RichiestaAcquisto getRichiesta() {
		return richiesta;
	}
	public void setRichiesta(RichiestaAcquisto richiesta) {
		this.richiesta = richiesta;
	}
	public CartaFed getCF() {
		return cf;
	}
	public void setCF(CartaFed cf) {
		this.cf = cf;
	}
	public Carrello getCarrello(){
		return carrello;
	}
	public void setCarrello(Carrello c){
		this.carrello = c;
	}

private CartaFed cf;
private RichiestaAcquisto richiesta;
private Carrello carrello;


}
