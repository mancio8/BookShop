package Control;

import Model.Carrello;
import Model.Libro;
import Model.Utente;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CarrelloDAO extends BaseDAO implements DAO<Carrello> {

	private CarrelloDAO() {
		carrelli = new LinkedList<Carrello>();
	}

	public static CarrelloDAO getInstance() {
		if(instance == null) {
			instance = new CarrelloDAO();
		}
		return instance;
	}
	public void load() {
		LinkedList<Carrello> carrelli = new LinkedList<Carrello>();
		Carrello c = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
			preparedStatement.execute();
			result = preparedStatement.getResultSet();

			while(result.next()) {
				c = new Carrello(result.getString(1), result.getString(2), result.getInt(3), result.getDouble(4), result.getString(5 ));
				carrelli.add(c);
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
		this.carrelli=carrelli;
	}

	@Override
	public boolean add(Carrello carrelli) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, carrelli.getIdC());
			preparedStatement.setString(2, carrelli.getProdotto());
			preparedStatement.setInt(3, carrelli.getQuantita());
			preparedStatement.setDouble(4, carrelli.getPrezzoTot());
			preparedStatement.setString(5, carrelli.getEmail());
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

	public boolean delete(Carrello carrelli) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(1, carrelli.getProdotto());
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
	public boolean deleteCart(Carrello carrelli) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY_CART);
			preparedStatement.setString(1, carrelli.getEmail());
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
	public LinkedList<Carrello> getCarrello(){
		return carrelli;
	}
	public Carrello getCarrello(String idc){
		Carrello carrello = null;
		for(Carrello c: carrelli) {
			if(c.getIdC().equalsIgnoreCase(idc)) {
				carrello = c;
				break;
			}
		}
		return carrello;
	}
	public boolean update(Carrello carrelli) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = super.createConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);
			ps.setInt(1, carrelli.getQuantita());
			ps.setDouble(2, carrelli.getPrezzoTot());
			ps.setString(3, carrelli.getProdotto());
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
	private static CarrelloDAO instance;
	private  LinkedList<Carrello> carrelli;



	private static final String CREATE_QUERY ="INSERT INTO carrelli VALUES(?,?,?,?,?)";
	private static final String READ_ALL_QUERY = "SELECT * FROM carrelli";
	private static final String DELETE_QUERY= "DELETE FROM carrelli WHERE Prodotti =(?) ";
	private static final String DELETE_QUERY_CART= "DELETE FROM carrelli WHERE Email =(?) ";
	private static final String UPDATE_QUERY = "UPDATE carrelli SET Quantità=(?), Prezzo=(?) WHERE Prodotti=(?) ";



}
