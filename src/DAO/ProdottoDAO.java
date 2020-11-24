package DAO;



import Model.Prodotto;
import Model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProdottoDAO extends BaseDAO implements DAO<Prodotto> {
   private ProdottoDAO() {
	   prodotti = new LinkedList<Prodotto>();
   }
   public static ProdottoDAO getInstance() {
	   if(instance == null) {
		   instance = new ProdottoDAO();
	   }
	   return instance;
   }
	public void load() {
		LinkedList<Prodotto> prodotti = new LinkedList<Prodotto>();
		ProprietarioDAO proprietarioDAO = ProprietarioDAO.getInstance();
		Prodotto p= null;
		Proprietario p1 = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
			preparedStatement.execute();
			result = preparedStatement.getResultSet();
			
			while(result.next()) {
				p = new Prodotto(result.getString(1), result.getDouble(2),result.getInt(3),null);
				p1 = proprietarioDAO.getProprietario(result.getString(4));
				p.setProprietario(p1);
				prodotti.add(p);
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
		this.prodotti=prodotti;
	}
   
	public boolean add(Prodotto prodotto) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, prodotto.getId());
			preparedStatement.setDouble(2, prodotto.getPrezzo());
			preparedStatement.setInt(3, prodotto.getQuantita());
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
	
	public boolean delete(Prodotto prodotto) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(1, prodotto.getId());
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
	public boolean update(Prodotto prodotto) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = super.createConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);
			ps.setDouble(2, prodotto.getPrezzo());
			ps.setInt(3, prodotto.getQuantita());
			ps.execute();
			load();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
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
		public LinkedList<Prodotto> getProdotto(){
			return prodotti;
		}
		public Prodotto getProdotto(String id){
			Prodotto prodotto = null;
			for(Prodotto p:prodotti) {
				if(p.getId().equalsIgnoreCase(id)) {
					prodotto = p;
					break;
				}
			}
			return prodotto;
		}

	public Prodotto getPrezzo(double prezzo){
		Prodotto prodotto = null;
		for(Prodotto p:prodotti) {
			if(p.getPrezzo() == prezzo) {
				prodotto = p;
				break;
			}
		}
		return prodotto;
	}
		
		
   private static ProdottoDAO instance;
   private LinkedList<Prodotto> prodotti;
   
   private static final String CREATE_QUERY ="INSERT INTO prodotti VALUES(?,?,?,?)";
   private static final String READ_ALL_QUERY = "SELECT * FROM prodotti";
   private static final String DELETE_QUERY= "DELETE FROM prodotti WHERE id = ?";
   private static final String UPDATE_QUERY = "UPDATE prodotti SET prezzo =(?,?) , quantita =(?,?,?)";

}
