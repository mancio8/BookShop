package Control;

import Model.Carrello;
import Model.CartaFed;
import Model.Cliente;
import Model.RichiestaAcquisto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ClienteDAO extends BaseDAO implements DAO<Cliente> {
    
	private ClienteDAO() {
    	 clienti = new LinkedList<Cliente>();
     }
	
	public static ClienteDAO getInstance() {
		if(instance == null) {
			instance = new ClienteDAO();
		}
		return instance;
	}
	public void load() {
		LinkedList<Cliente> clienti = new LinkedList<Cliente>();
		CartaFedDAO cartaDAO = CartaFedDAO.getInstance();
		RichiestaDAO richiesta = RichiestaDAO.getInstance();
		CarrelloDAO carrello=CarrelloDAO.getInstance();
		Cliente c = null;
		CartaFed cf = null;
		RichiestaAcquisto r = null;
		Carrello ca = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet result = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
			preparedStatement.execute();
			result = preparedStatement.getResultSet();
			
			while(result.next()) {
				c = new Cliente(result.getString(1), result.getString(2), result.getString(3), result.getString(4), null ,null, result.getString(7));
				cf = cartaDAO.getCarte(result.getString(5));
				r = richiesta.getRichieste(result.getString(6));


				/* r.setC(c);
				cf.setCliente(c);
				c.setRichiesta(r);
				c.setCF(cf);
				ca.setCliente(c);
				c.setCarrello(ca);

				 */
				clienti.add(c);
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
		this.clienti=clienti;
	}
	
	@Override
	public boolean add(Cliente cliente) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setString(2, cliente.getCognome());
			preparedStatement.setString(3, cliente.getEmail());
			preparedStatement.setString(4, cliente.getPassword());
			preparedStatement.setString(5, cliente.getCF().getCodice());
			preparedStatement.setString(6, cliente.getRichiesta().getIdA());
			preparedStatement.setString(7, cliente.getCarrello());
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
	
	public boolean delete(Cliente cliente) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(3, cliente.getEmail());
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
		public LinkedList<Cliente> getCliente(){
			return clienti;
		}
		public static Cliente getCliente(String email){
			Cliente cliente = null;
			for(Cliente c: clienti) {
				if(c.getEmail().equalsIgnoreCase(email)) {
					cliente = c;
					break;
				}
			}
			return cliente;
		}
	 private static ClienteDAO instance;
	 private static LinkedList<Cliente> clienti;
	 


private static final String CREATE_QUERY ="INSERT INTO clienti VALUES(?,?,?,?,?,?,?)";
private static final String READ_ALL_QUERY = "SELECT * FROM clienti";
private static final String DELETE_QUERY= "DELETE FROM clienti WHERE email = (?,?,?)";


}
