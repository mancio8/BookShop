package Control;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import Model.CartaFed;
import Model.Cliente;
import Model.Dipendente;
import Model.Prodotto;
import Model.Proprietario;
import Model.Utente;

public class GestoreProprietario {
	public static GestoreProprietario getInstance() {
		if(instance == null) {
			instance = new GestoreProprietario();
		}
		return instance;
	}

	private GestoreProprietario() {
		persistenza = GestoreDB.getInstance();
		lista = persistenza.getProprietario();
	}

	public boolean aggiungiProprietario(Proprietario p) {
		boolean validate = true;
		Iterator<Proprietario> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equals(p.getEmail())) {
				validate = false;
			}
		}
		if(validate) {
			persistenza.addProprietario(p);
		}
		lista=persistenza.getProprietario();
		return validate;
	}
	public boolean aggiungiCliente(Cliente c) {
		boolean validate = true;
		Iterator<Cliente> it = clienti.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equals(c.getEmail())) {
				validate =false;
			}
		}
		if(validate) {
			persistenza.addCliente(c);
		}
		clienti=persistenza.getCliente();
		return validate;
	}
	public boolean aggiungiDipendente(Dipendente d) {
		boolean validate = true;
		Iterator<Dipendente> it = dipendenti.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equals(d.getEmail())) {
				validate =false;
			}
		}
		if(validate) {
			persistenza.addDipendente(d);
		}
		dipendenti=persistenza.getDipendente();
		return validate;
	}
	public boolean aggiungiProdotto(Prodotto p) {
		boolean validate = true;
		Iterator<Prodotto> it = listap.iterator();
		while(it.hasNext()) {
			if(it.next().getId().equalsIgnoreCase(p.getId())) {
				validate = false;
			}
		}
		if(validate) {
			persistenza.addProdotto(p);
		}
		listap =persistenza.getProdotto();
		return validate;
	}
	public void removeProprietario(Proprietario p) {

	}
	public Proprietario getProprietario(String email) {
		lista = ProprietarioDAO.getInstance().getProprietario();
		Iterator<Proprietario> it = lista.iterator();
		Proprietario p= null;
		while(it.hasNext()) {
			p = it.next();
			if(p.getEmail().equals(email)) {
				return p;
			}
		}
		return null;
	}
	public void cambiaPrezzo(double prezzo , Prodotto p) {
		Iterator<Prodotto> it = listap.iterator();
		while(it.hasNext()) {
			if(it.next().getId().equalsIgnoreCase(p.getId())) {
				p.setPrezzo(prezzo);
				persistenza.updateProdotto(p);
				break;
			}
		}
	}
	public void cambiaQuantita(int q, Prodotto p) {
		Iterator<Prodotto> it = listap.iterator();
		while(it.hasNext()) {
			if(it.next().getId().equalsIgnoreCase(p.getId())) {
				p.setQuantita(q);
				persistenza.updateProdotto(p);
				break;
			}
		}
	}
	public void cambiaPunti(int punti,CartaFed c) {
		Iterator<CartaFed> it = carte.iterator();
		while(it.hasNext()) {
			if(it.next().getCodice().equalsIgnoreCase(c.getCodice())) {
				c.setPunti(punti);
				persistenza.updateCarta(c);
				break;
			}
		}
	}
	public Boolean isProprietario(Utente u) {
		List<Proprietario> lista =persistenza.getProprietario();
		Iterator<Proprietario> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equalsIgnoreCase(u.getEmail())) {
				return true;
			}
		}
		return false;
	}
	public void cambiaData(Date data,CartaFed c) {
		Iterator<CartaFed> it = carte.iterator();
		while(it.hasNext()) {
			if(it.next().getCodice().equalsIgnoreCase(c.getCodice())) {
				c.setData(data);
				persistenza.updateCarta(c);
				break;
			}
		}
	}
	public List<Prodotto> getProdotti(){
		return listap;
	}
	public String newEmail(String email) {
		return email;
	}

	private static GestoreProprietario instance;
	private GestoreDB persistenza;
	private List<Proprietario> lista;
	private List<Prodotto> listap;
	private List<CartaFed> carte;
	private List<Cliente> clienti;
	private List<Dipendente> dipendenti;
}

