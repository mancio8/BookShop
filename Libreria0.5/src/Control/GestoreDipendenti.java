package Control;
import java.util.Iterator;
import java.util.List;

import Model.Dipendente;
import Model.Proprietario;
import Model.Utente;

public class GestoreDipendenti{
	public static GestoreDipendenti getInstance() {
		if(instance == null) {
			instance = new GestoreDipendenti();
		}
		return instance;
	}

	private GestoreDipendenti() {
		persistenza = GestoreDB.getInstance();
		lista = persistenza.getDipendente();
	}

	public boolean aggiungiDipendente(Dipendente d) {
		boolean validate = true;
		Iterator<Dipendente> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equalsIgnoreCase(d.getEmail())) {
				validate = false;
			}
		}
		if(validate) {
			persistenza.addDipendente(d);
		}
		lista=persistenza.getDipendente();
		return validate;
	}
	public Dipendente getDipendente(String email) {
		lista = DipendenteDAO.getInstance().getDipendenti();
		Iterator<Dipendente> it = lista.iterator();
		Dipendente d= null;
		while(it.hasNext()) {
			d = it.next();
			if(d.getEmail().equalsIgnoreCase(email)) {
				return d;
			}
		}
		return null;
	}
	public Boolean isDipendente(Dipendente d) {
		List<Dipendente> lista =persistenza.getDipendente();
		Iterator<Dipendente> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equalsIgnoreCase(d.getEmail())) {
				return true;
			}
		}
		return false;
	}
	public void EliminaRichiesta(String idR, String email) {
		persistenza.removeRichiesta(idR, email);
	}
	public String newEmail(String email) {
		return email;
	}

	private static GestoreDipendenti instance;
	private GestoreDB persistenza;
	private List<Dipendente> lista;

}




