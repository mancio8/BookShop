package DAO;

import Model.Carrello;
import Model.Cliente;


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
		ClienteDAO clientiDao=ClienteDAO.getInstance();
		Cliente client = null;
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
				c = new Carrello(result.getString(1), null );
				client = clientiDao.getCliente(result.getString(2));
				c.setCliente(client);

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
	public boolean delete(Carrello carrello) {
		return false;
	}

	@Override
	public boolean add(Carrello carrelli) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, carrelli.getIdC());
			preparedStatement.setString(2, carrelli.getCliente().getEmail());
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

	public boolean search(Carrello carrelli) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(Search_ALL_QUERY);

			preparedStatement.setString(1, carrelli.getCliente().getEmail());
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

	private void selectCarrello(Carrello c){
		String queryCliente = "select Cliente from carrelli where IdCarrello=?";
		String queryId = "select IdCarrello from carrelli where Cliente=?";
		PreparedStatement statement;
		Connection conn=null;

		String email=null;
		String idCart=null;
		try {
			statement = conn.prepareStatement(queryCliente);
			statement.setString(1, c.getIdC());

			ResultSet rs = statement.executeQuery();
			while(rs.next()){
				email = rs.getString("Cliente");

			}
			rs.close();

			statement = conn.prepareStatement(queryId);
			statement.setString(1, c.getCliente().getEmail());

			rs = statement.executeQuery();
			while(rs.next()){

				idCart = rs.getString("IdCarrello");

			}
			rs.close();


		} catch (SQLException e) {

			e.printStackTrace();
		}

	}



	private static CarrelloDAO instance;
	private  LinkedList<Carrello> carrelli;




	private static final String CREATE_QUERY ="INSERT INTO carrelli VALUES(?,?)";
	private static final String READ_ALL_QUERY = "SELECT * FROM carrelli";
	private static final String Search_ALL_QUERY = "SELECT IdCarrello FROM carrelli WHERE Cliente=(?) ";




}
