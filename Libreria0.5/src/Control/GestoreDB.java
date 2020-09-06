package Control;

import Model.*;

import java.util.List;

public class GestoreDB {

	private ProprietarioDAO proprietarioDAO;
	private UtenteDAO utenteDAO;
	private ClienteDAO clienteDAO;
	private DipendenteDAO dipendenteDAO;
	private ArticoliCDAO articoloCDAO;
	private LibroDAO libroDAO;
	private ProdottoDAO prodottoDAO;
	private CartaFedDAO cartaDAO;
	private RichiestaDAO richiestaDAO;
	private CarrelloDAO carrelloDAO;
	private SingInDAO singInDAO;
	private static GestoreDB instance;
	

	private GestoreDB() {
		proprietarioDAO= ProprietarioDAO.getInstance();
		proprietarioDAO.load();
		utenteDAO = UtenteDAO.getInstance();
		utenteDAO.load();
		clienteDAO = ClienteDAO.getInstance();
		clienteDAO.load();
		dipendenteDAO = DipendenteDAO.getInstance();
		dipendenteDAO.load();
		articoloCDAO = ArticoliCDAO.getInstance();
		articoloCDAO.load();
		libroDAO = LibroDAO.getInstance();
		libroDAO.load();
		prodottoDAO = ProdottoDAO.getInstance();
		prodottoDAO.load();
		cartaDAO = CartaFedDAO.getInstance();
		cartaDAO.load();
		richiestaDAO = RichiestaDAO.getInstance();
		richiestaDAO.load();

		carrelloDAO = CarrelloDAO.getInstance();
		carrelloDAO.load();
		singInDAO = SingInDAO.getInstance();
		singInDAO.load();;
	}
	
	public static GestoreDB getInstance() {
		if(instance == null) {
			instance =new GestoreDB();
		}
		return instance;
	}
	
	public List<Proprietario> getProprietario(){
		return proprietarioDAO.getProprietario();
	}
	public void addProprietario(Proprietario p) {
		proprietarioDAO.add(p);
	}
	public void deleteProprietario(Proprietario p) {
		proprietarioDAO.delete(p);
	}
	public void addDipendente(Dipendente d) {
		dipendenteDAO.add(d);
	}
	public void deleteDipendente(Dipendente d) {
		dipendenteDAO.delete(d);
	}
	public List<Dipendente> getDipendente(){
		return dipendenteDAO.getDipendenti();
	}
	
	public void addCliente(Cliente c) {
		clienteDAO.add(c);
	}
	public void deleteCliente(Cliente c) {
		clienteDAO.delete(c);
	}
	public List<Cliente> getCliente(){
		return clienteDAO.getCliente();
	}
	public void addUtente (Utente u) {
		utenteDAO.add(u);
	}
	public void deleteUtente(Utente u) {
		utenteDAO.delete(u);
	}
	public List<Utente> getUtente(){
		return utenteDAO.getUtente();
	}
	public void addRichiesta(RichiestaAcquisto r) {
		richiestaDAO.add(r);
	}
	public void deleteRichiesta(RichiestaAcquisto r) {
		richiestaDAO.delete(r);
	}
	public List<RichiestaAcquisto> getRichiesta(){
		return richiestaDAO.getRichiesta();
	}
	public void addLibro(Libro l) {
		libroDAO.add(l);
	}
	public void deleteLibro(Libro l) {
		libroDAO.delete(l);
	}
	public List<Libro> getLibro(){
		return libroDAO.getLibro();
	}
	public void addArticolo(articoloC a) {
		articoloCDAO.add(a);
	}
	public void deleteArticolo(articoloC a) {
		articoloCDAO.delete(a);
	}
	public List<articoloC> getArticolo(){
		return articoloCDAO.getArticolo();
	}
	public void addCarta(CartaFed c) {
		cartaDAO.add(c);
	}
	public void deleteCarta(CartaFed c) {
		cartaDAO.delete(c);
	}
	public void updateCarta(CartaFed c) {
		cartaDAO.update(c);
	}
	public List<CartaFed> getCarta(){
		return cartaDAO.getCarta();
	}
	public void addProdotto(Prodotto p) {
		prodottoDAO.add(p);
	}
	public void deleteProdotto(Prodotto p) {
		prodottoDAO.delete(p);
	}
	public void updateProdotto(Prodotto p) {
		prodottoDAO.update(p);
	}
	public List<Prodotto> getProdotto(){
		return prodottoDAO.getProdotto();
	}
	public void removeRichiesta(String richiesta , String dipendente) {
		dipendenteDAO.remove(richiesta,dipendente);
	}
	public  List<Carrello> getCarrello(){
		return carrelloDAO.getCarrello();
	}
	public void addCarrello(Carrello c) {
		carrelloDAO.add(c);
	}
	public void deleteCarrello(Carrello c) {
		carrelloDAO.delete(c);
	}
	public  List<SingIn> getSingIn(){
		return singInDAO.getSingIn();
	}
	public void addSingIn(SingIn s) {
		singInDAO.add(s);
	}
	public void deleteSingIn(SingIn s) {
		singInDAO.delete(s);
	}


	public void removeProdotto(String id , String carrello) {
		dipendenteDAO.remove(id,carrello);
	}
}
