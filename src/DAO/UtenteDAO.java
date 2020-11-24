package DAO;


import Model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UtenteDAO extends BaseDAO implements DAO<Utente> {
    
	private UtenteDAO() {
    	 utenti = new LinkedList<Utente>();
     }
	
	public static UtenteDAO getInstance() {
		if(instance == null) {
			instance = new UtenteDAO();
		}
		return instance;
	}
	public void load() {
		LinkedList<Utente> utenti = new LinkedList<Utente>();
		Utente u = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
			preparedStatement.execute();
			result = preparedStatement.getResultSet();
			
			while(result.next()) {
				u = new Utente(result.getString(1), result.getString(2), result.getString(3), result.getString(4) );
				utenti.add(u);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				result.close();
			} catch(Exception rse) {
				rse.printStackTrace();
			}
			try {
				preparedStatement.close();
			} catch(Exception sse) {
				sse.printStackTrace();
			} 
			try {
				conn.close();
			} catch(Exception cse) {
				cse.printStackTrace();
			}
		}

		this.utenti=utenti;
	}
	
	@Override
	public boolean add(Utente utente) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.setString(4, utente.getPassword());
			preparedStatement.executeUpdate();
			load();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch(Exception sse) {
				sse.printStackTrace();
			} 
			try {
				conn.close();
			} catch(Exception cse) {
				cse.printStackTrace();
			}
		}
			return false;
		}
	
	public boolean delete(Utente utente) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(3, utente.getEmail());
			preparedStatement.execute();
			load();
			return true;
	} catch(SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			preparedStatement.close();
		} catch(Exception sse) {
			sse.printStackTrace();
		} 
		try {
			conn.close();
		} catch(Exception cse) {
			cse.printStackTrace();
		}
	}
		return false;
	}
		public LinkedList<Utente> getUtente(){
			return utenti;
		}
		public Utente getUtente(String email){
			Utente utente = null;
			for(Utente u: utenti) {
				if(u.getEmail().equalsIgnoreCase(email)) {
					utente = u;
					break;
				}
			}
			return utente;
		}
	 private static UtenteDAO instance;
	 private LinkedList<Utente> utenti;
	 


private static final String CREATE_QUERY ="INSERT INTO utenti VALUES(?,?,?,?)";
private static final String READ_ALL_QUERY = "SELECT * FROM utenti";
private static final String DELETE_QUERY= "DELETE FROM utenti WHERE email =(?,?,?) ";


}
