package Control;

import DAO.GestoreDB;
import Model.Cliente;
import Model.Dipendente;
import Model.Proprietario;
import Model.Utente;

import java.util.Iterator;
import java.util.List;

public class GestoreUtenti {
public static GestoreUtenti getInstance() {
	if(instance == null) {
		instance = new GestoreUtenti();
	}
	return instance;
}

private GestoreUtenti() {
	persistenza = GestoreDB.getInstance();
	lista = persistenza.getUtente();
}
public Boolean addUtente(Utente u) {
	persistenza.addUtente(u);
	lista = persistenza.getUtente();
	return true;
}
public Boolean login(Utente u) {
	Iterator<Utente> it = lista.iterator();
	while(it.hasNext()) {
		Utente ut = it.next();
		if(ut.getEmail().equals(u.getEmail()) && ut.getPassword().equals(u.getPassword())){
			return true;
		}
	}
	return false;
}
public Utente getUtente(String email) {
	Iterator<Utente> it = lista.iterator();
	while(it.hasNext()) {
		Utente ut = it.next();
		if(ut.getEmail().equalsIgnoreCase(email)){
			return ut;
		}
	}
	return null;
}
	public Boolean isProprietario(Utente u) {
		List<Proprietario> lista =persistenza.getProprietario();
		Iterator<Proprietario> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equals(u.getEmail())) {
				return true;
			}
		}
		return false;
	}

	public Boolean isCliente(Utente u) {
		List<Cliente> lista =persistenza.getCliente();
		Iterator<Cliente> it = lista.iterator();
		while(it.hasNext()) {
			if(it.next().getEmail().equals(u.getEmail())) {
				return true;
			}
		}
		return false;
	}
    /*public Boolean isDipendente(Utente u) {
        List<Dipendente> lista =persistenza.getDipendente();
        Iterator<Dipendente> it = lista.iterator();
        while(it.hasNext()) {
            if(it.next().getEmail().equals(u.getEmail())) {
                return true;
            }
        }
        return false;
    }*/
public Boolean isDipendente(Utente u){
    List<Dipendente> listaD = persistenza.getDipendente();
    Iterator<Dipendente> itD = listaD.iterator();
    while(itD.hasNext()){
        if(itD.next().getEmail().equalsIgnoreCase(u.getEmail())){
            return true;
        }
    }
    return false;
}
private static GestoreUtenti instance;
private GestoreDB persistenza;
private List<Utente>lista;
}
