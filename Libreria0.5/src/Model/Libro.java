package Model;

public class Libro extends Prodotto {

	public Libro(String id, double prezzo, int quantita, Proprietario proprietario, String titolo, String autore,String casaEd, String isbn) {
		super(id, prezzo, quantita, proprietario);
		this.titolo = titolo;
		this.autore = autore;
		this.casaEd =casaEd;
		this.isbn = isbn;
	}
public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public String getCasaEd() {
		return casaEd;
	}
	public void setCasaEd(String casaEd) {
		this.casaEd = casaEd;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
private String titolo;
private String autore;
private String casaEd;
private String isbn;
}
