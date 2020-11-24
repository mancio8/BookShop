package DAO;


import Model.Proprietario;
import Model.articoloC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ArticoliCDAO extends BaseDAO implements DAO<articoloC> {
   private ArticoliCDAO() {
	   articoli = new LinkedList<articoloC>();
   }
   public static ArticoliCDAO getInstance() {
	   if(instance == null) {
		   instance = new ArticoliCDAO();
	   }
	   return instance;
   }
	public void load() {
		LinkedList<articoloC> articoli = new LinkedList<articoloC>();
		ProprietarioDAO pdao = ProprietarioDAO.getInstance();
		articoloC a= null;
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
				a = new articoloC(result.getString(1), result.getDouble(2),result.getInt(3),null, result.getString(5));
				p = pdao.getProprietario(result.getString(4));
				a.setProprietario(p);
				articoli.add(a);
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
		this.articoli = articoli;
	}
   
	public boolean add(articoloC articolo) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, articolo.getId());
			preparedStatement.setDouble(2, articolo.getPrezzo());
			preparedStatement.setInt(3, articolo.getQuantita());
			preparedStatement.setString(4, articolo.getTipo());
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
	
	public boolean delete(articoloC articolo) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(1, articolo.getId());
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
		public LinkedList<articoloC> getArticolo(){
			return articoli;
		}
		public articoloC getArticolo(String id){
			articoloC articolo = null;
			for(articoloC a:articoli) {
				if(a.getId().equalsIgnoreCase(id)) {
					articolo = a;
					break;
				}
			}
			return articolo;
		}
		
		
   private static ArticoliCDAO instance;
   private LinkedList<articoloC> articoli;
   
   private static final String CREATE_QUERY ="INSERT INTO articoli VALUES(?,?,?,?)";
   private static final String READ_ALL_QUERY = "SELECT * FROM articoli";
   private static final String DELETE_QUERY= "DELETE FROM articoli WHERE id = ?";

}
