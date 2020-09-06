package Control;

import Model.CartaFed;
import Model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CartaFedDAO extends BaseDAO implements DAO<CartaFed> {
 private CartaFedDAO() {
	 carte = new LinkedList<CartaFed>();
 }
 public static CartaFedDAO getInstance() {
	 if(instance == null) {
		 instance = new CartaFedDAO();
	 }
	 return instance;
 }
 public void load() {
	 LinkedList<CartaFed> carte = new LinkedList<CartaFed>();
	 ClienteDAO clientDAO = ClienteDAO.getInstance();
	 CartaFed cf = null;
	 Cliente c = null;
	 Connection conn = null;
	 PreparedStatement ps = null;
	 ResultSet result = null;
	 try {
		 conn = super.createConnection();
		 ps = conn.prepareStatement(READ_ALL_QUERY);
		 ps.execute();
		 result = ps.getResultSet();
		 
		 while(result.next()) {
			 cf = new CartaFed(result.getString(1), result.getDate(2), result.getInt(3), result.getString(4));
			 //c = clientDAO.getCliente(result.getString(4));
			 //c.setCF(cf);
			// cf.setCliente(c);
			 carte.add(cf);
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
				 ps.close();
			 } catch(Exception sse) {
				 sse.printStackTrace();
			 } try {
				 conn.close();
			 } catch(Exception cse) {
				 cse.printStackTrace();
			 }
		 }
	 this.carte = carte;
 }
 
 public boolean add(CartaFed carta) {
	 Connection conn = null;
	 PreparedStatement ps = null;
	 
	 try {
		 conn = super.createConnection();
		 ps = conn.prepareStatement(CREATE_QUERY);
		 
		 ps.setString(1, carta.getCodice());
		 ps.setDate(2, (java.sql.Date) carta.getData());
		 ps.setInt(3, carta.getPunti());
		 ps.setString(4, carta.getCliente());
		 
		 ps.executeUpdate();
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
	public boolean delete(CartaFed carta) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(1, carta.getCodice());
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
	
	public boolean update(CartaFed carta) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = super.createConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);
			ps.setDate(2, (java.sql.Date) carta.getData());
			ps.setInt(3, carta.getPunti());
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
		public LinkedList<CartaFed> getCarta(){
			return carte;
		}
		public static CartaFed getCarte(String codice){
			CartaFed carta = null;
			for(CartaFed c: carte) {
				if(c.getCodice().equalsIgnoreCase(codice)) {
					carta = c;
					break;
				}
			}
			return carta;
		}
 private static LinkedList<CartaFed> carte; 
 private static CartaFedDAO instance;
 
 private static final String CREATE_QUERY ="INSERT INTO carte VALUES(?,?,?,?)";
 private static final String READ_ALL_QUERY = "SELECT * FROM carte";
 private static final String DELETE_QUERY= "DELETE FROM carte WHERE codice = ?";
 private static final String UPDATE_QUERY = "UPDATE carte SET datas =(?,?) , punti =(?,?,?)";
}
