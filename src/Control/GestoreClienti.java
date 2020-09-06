package Control;
import java.util.Iterator;
import java.util.List;

import Model.Carrello;
import Model.Cliente;
import Model.Prodotto;
import Model.RichiestaAcquisto;
import Model.Utente;

public class GestoreClienti {
	public static GestoreClienti getInstance() {
		if(instance == null) {
			instance = new GestoreClienti();
		}
		return instance;
	}

	private GestoreClienti() {
		persistenza = GestoreDB.getInstance();
		lista = persistenza.getCliente();
	}

	public boolean aggiungiCliente(Cliente c) {
		boolean validate = true;
		Iterator<Cliente> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equalsIgnoreCase(c.getEmail())) {
				validate = false;
			}
		}
		if(validate) {
			c.getCF().setPunti(0);
			persistenza.addCliente(c);
		}
		lista=persistenza.getCliente();
		return validate;
	}
	public void removeCliente(Cliente c) {

	}
	public Cliente getCliente(String email) {
		lista = ClienteDAO.getInstance().getCliente();
		Iterator<Cliente> it = lista.iterator();
		Cliente c = null;
		while(it.hasNext()) {
			c = it.next();
			if(c.getEmail().equalsIgnoreCase(email)) {
				return c;
			}
		}
		return null;
	}
	public void creaRichiesta(RichiestaAcquisto r) {
		persistenza.addRichiesta(r);
	}
	/*public void aggiungiProdotto(Prodotto p , Carrello c ) {
		Iterator<Carrello> it = carrelli.iterator();
		while(it.hasNext()) {
			if(it.next().getIdC().equalsIgnoreCase(c.getIdC())) {
				c.add(p);
				persistenza.updateCarrello(c);
				break;
			}
		}
		carrelli = persistenza.getCarrello();
	}*/
	public Boolean isCliente(Utente u) {
		List<Cliente> lista =persistenza.getCliente();
		Iterator<Cliente> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equalsIgnoreCase(u.getEmail())) {
				return true;
			}
		}
		return false;
	}
	public void removeProdotto(String id, String carrello) {
		persistenza.removeProdotto(id,carrello);
	}
	public String newEmail(String email) {
		return email;
	}

	private static GestoreClienti instance;
	private GestoreDB persistenza;
	private List<Cliente> lista;
	private List<Carrello> carrelli;
	private List<RichiestaAcquisto> richieste;

}
