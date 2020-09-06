package Model;

public class Cliente extends Utente {

	public Cliente(String nome, String cognome, String email, String password, CartaFed cf,RichiestaAcquisto richiesta, String carrello) {
		super(nome, cognome, email, password);
		this.cf = cf;
		this.richiesta = richiesta;
		this.carrello= carrello;
	}
	public Cliente getCliente(){
		Cliente c = new Cliente(nome,cognome,email,password,cf,null,null);
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
	public String getCarrello(){
		return carrello;
	}
	public void setCarrello(String c){
		this.carrello = c;
	}
private CartaFed cf;
private RichiestaAcquisto richiesta;
private String carrello;
}
