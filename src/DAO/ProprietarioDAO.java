package DAO;


import Model.Prodotto;
import Model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class ProprietarioDAO extends BaseDAO implements DAO<Proprietario> {
    
	private ProprietarioDAO() {
    	 proprietario = new LinkedList<Proprietario>();
     }
	
	public static ProprietarioDAO getInstance() {
		if(instance == null) {
			instance = new ProprietarioDAO();
		}
		return instance;
	}
	public void load() {
		LinkedList<Proprietario> proprietario = new LinkedList<Proprietario>();
		Proprietario p = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
			preparedStatement.execute();
			result = preparedStatement.getResultSet();
			
			while(result.next()) {
				p = new Proprietario(result.getString(1), result.getString(2), result.getString(3), result.getString(4),null );
				proprietario.add(p);
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
		this.proprietario = proprietario;
	}
	
	@Override
	public boolean add(Proprietario proprietari) {
		Connection conn = null;
		ProdottoDAO pdao = ProdottoDAO.getInstance();
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, proprietari.getNome());
			preparedStatement.setString(2, proprietari.getCognome());
			preparedStatement.setString(3, proprietari.getEmail());
			preparedStatement.setString(4, proprietari.getPassword());
			preparedStatement.execute();
			LinkedList<Prodotto> prodotti = pdao.getProdotto();
			
			for(Prodotto p : prodotti) {
				Connection conn2 = super.createConnection();
				PreparedStatement ps2 = conn2.prepareStatement("INSERT INTO visibilit� VALUES(?,?,?)");
				ps2.setString(1, p.getId());
				ps2.setDouble(2,p.getPrezzo());
				ps2.setInt(3, p.getQuantita());
				ps2.execute();
			}
			pdao.load();
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
	
	public boolean delete(Proprietario proprietari) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(3, proprietari.getEmail());
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
		public LinkedList<Proprietario> getProprietario(){
			return proprietario;
		}
		public Proprietario getProprietario(String email){
			Proprietario proprietari = null;
			for(Proprietario p: proprietario) {
				if(p.getEmail().equalsIgnoreCase(email)) {
					proprietari = p;
					break;
				}
			}
			return proprietari;
		}
	 private static ProprietarioDAO instance;
	 private LinkedList<Proprietario> proprietario;
	 


private static final String CREATE_QUERY ="INSERT INTO proprietario VALUES(?,?,?,?,?)";
private static final String READ_ALL_QUERY = "SELECT * FROM proprietario";
private static final String DELETE_QUERY= "DELETE FROM proprietario WHERE email = (?,?,?)";


}
