package Model;

import java.util.LinkedList;
import Model.RichiestaAcquisto;

public class Dipendente extends Utente {

	public Dipendente(String nome, String cognome, String email, String password, LinkedList<RichiestaAcquisto> richieste) {
		super(nome, cognome, email, password);
		this.richieste = richieste;
	}

	public LinkedList getRichieste() {
		return richieste;
	}

	public void setRichiesta(LinkedList<RichiestaAcquisto> richieste) {
		this.richieste = richieste;
	}

	public void addRichiesta(RichiestaAcquisto r) {
		richieste.add(r);
	}

	public void removeRichiesta(RichiestaAcquisto r) {
		richieste.remove(r);
	}


	public void setR(RichiestaAcquisto r) {
		this.r = r;
	}

	private LinkedList<RichiestaAcquisto> richieste = new LinkedList<RichiestaAcquisto>();
	private RichiestaAcquisto r;
}
