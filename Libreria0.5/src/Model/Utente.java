package Model;

public class Utente {
	public Utente(String nome, String cognome, String email , String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		
	}
public String getNome() {
	return nome;
}
public String getCognome() {
	return cognome;
}
public String getEmail() {
	return email;
}
public String getPassword() {
	return password;
}
public void setNome(String nome) {
	this.nome = nome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public void setEmail(String email) {
	this.email = email;
}
public void setPassword(String password) {
	this.password = password;
}
protected String nome;
protected String cognome;
protected String email;
protected String password;
}
