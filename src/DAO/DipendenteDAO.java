package DAO;


import Model.Dipendente;
import Model.RichiestaAcquisto;

import java.sql.*;
import java.util.LinkedList;

public class DipendenteDAO extends BaseDAO implements DAO<Dipendente> {
    
	private DipendenteDAO() {
    	 dipendenti = new LinkedList<Dipendente>();
     }
	
	public static DipendenteDAO getInstance() {
		if(instance == null) {
			instance = new DipendenteDAO();
		}
		return instance;
	}
	public void load() {
		LinkedList<Dipendente> dipendenti = new LinkedList<Dipendente>();
		Dipendente d = null;
		RichiestaAcquisto r = null;
		RichiestaDAO richieste = RichiestaDAO.getInstance();
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
			preparedStatement.execute();
			result = preparedStatement.getResultSet();
			
			while(result.next()) {
				d= new Dipendente(result.getString(1), result.getString(2), result.getString(3), result.getString(4),null );
				//r = richieste.getRichieste(result.getString(5));
				//r.setDipendente(d);
				dipendenti.add(d);
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
		this.dipendenti = dipendenti;
	}
	
	@Override
	public boolean add(Dipendente dipendente) {
		Connection conn = null;
		RichiestaDAO rdao = RichiestaDAO.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, dipendente.getNome());
			preparedStatement.setString(2, dipendente.getCognome());
			preparedStatement.setString(3, dipendente.getEmail());
			preparedStatement.setString(4, dipendente.getPassword());
			preparedStatement.execute();
			LinkedList<RichiestaAcquisto> richieste = rdao.getRichiesta();
			for(RichiestaAcquisto r: richieste) {
				Connection conn2 = super.createConnection();
				PreparedStatement ps2 = conn2.prepareStatement("INSERT INTO visibilit� VALUES(?,?,?,?,?)");
				ps2.setString(1, r.getIdA());
				ps2.setDate(2, (Date) r.getData());
				ps2.setDouble(3, r.getPrezzo());
				ps2.setString(4, r.getTipoPagamento());

			}
			rdao.load();
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
	
	public boolean delete(Dipendente dipendente) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(3, dipendente.getEmail());
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
	public void remove(String richiesta, String dipendente) {
		Connection conn = super.createConnection();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE richiesta FROM dipendente WHERE id =?, email =?");
			ps.setString(1, richiesta);
			ps.setString(2, dipendente);
			ps.execute();
			load();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		public LinkedList<Dipendente> getDipendenti(){
			return dipendenti;
		}
		public Dipendente getDipendente(String email){
			Dipendente dipendente = null;
			for(Dipendente d: dipendenti) {
				if(d.getEmail().equalsIgnoreCase(email)) {
					dipendente = d;
					break;
				}
			}
			return dipendente;
		}
	 private static DipendenteDAO instance;
	 private LinkedList<Dipendente> dipendenti;
	 


private static final String CREATE_QUERY ="INSERT INTO dipendenti VALUES(?,?,?,?,?)";
private static final String READ_ALL_QUERY = "SELECT * FROM dipendenti";
private static final String DELETE_QUERY= "DELETE FROM dipendenti WHERE email = (?,?,?)";


}

