package DAO;


import Model.Cliente;
import Model.Dipendente;
import Model.RichiestaAcquisto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class RichiestaDAO extends BaseDAO implements DAO<RichiestaAcquisto> {
 private RichiestaDAO() {
	 richieste = new LinkedList<RichiestaAcquisto>();
 }
 public static RichiestaDAO getInstance() {
	 if(instance == null) {
		 instance = new RichiestaDAO();
	 }
	 return instance;
 }
 public void load() {
	 LinkedList<RichiestaAcquisto> richieste = new LinkedList<RichiestaAcquisto>();
	 RichiestaAcquisto r = null;
	 ClienteDAO clienti = ClienteDAO.getInstance();
	 DipendenteDAO dipendenti = DipendenteDAO.getInstance();
	 Dipendente d = null;
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
			 r = new RichiestaAcquisto(result.getString(1), result.getDate(2), result.getDouble(3),result.getString(4),null,null);
			 d = dipendenti.getDipendente(result.getString(5));
			 c = clienti.getCliente(result.getString(6));
			 r.setC(c);
			 r.setDipendente(d);

//			 d.setR(r);

			 //r.setDipendente(d);
			 richieste.add(r);
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
	 this.richieste = richieste;
 }
 
 public boolean add(RichiestaAcquisto richiesta) {
	 Connection conn = null;
	 PreparedStatement ps = null;
	 
	 try {
		 conn = super.createConnection();
		 ps = conn.prepareStatement(CREATE_QUERY);
		 
		 ps.setString(1, richiesta.getIdA());
		 ps.setDate(2, (java.sql.Date) richiesta.getData());
		 ps.setDouble(3, richiesta.getPrezzo());
		 ps.setString(4, richiesta.getTipoPagamento());

		 ps.setString(6, richiesta.getDipendente().getEmail());
		 
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
	public boolean delete(RichiestaAcquisto richiesta) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(1, richiesta.getIdA());
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
	/*public boolean update(RichiestaAcquisto richiesta) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = super.createConnection();
			ps = conn.prepareStatement(UPDATE_QUERY);
			if(richiesta.getPrezzo() > 50 && richiesta.getPrezzo()<100) {
			ps.setDouble(5, 5);
			ps.setDouble(3, richiesta.getPrezzo()-5);
			} else  if(richiesta.getPrezzo() > 100){
				ps.setDouble(5, 10);
				ps.setDouble(3, richiesta.getPrezzo()-10);
			}else { 
				ps.setDouble(5, richiesta.getSconto());
				ps.setDouble(3,richiesta.getPrezzo());
			}
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
	}*/
	
		public LinkedList<RichiestaAcquisto> getRichiesta (){
			return richieste;
		}
		public String getCliente(String email) {
			String cliente = null;
			for(RichiestaAcquisto r : richieste) {
				if(r.getC().equals(email)) {
					cliente = r.getC().getEmail();
					break;
				}
			}
			return cliente;
		}
		
		public RichiestaAcquisto getRichieste(String idA){
			RichiestaAcquisto richiesta = null;
			for(RichiestaAcquisto r : richieste) {
				if(r.getIdA().equalsIgnoreCase(idA)) {
					richiesta = r;
					break;
				}
			}
			return richiesta;
		}
 private LinkedList<RichiestaAcquisto> richieste; 

 private static RichiestaDAO instance;
 
 private static final String CREATE_QUERY ="INSERT INTO richieste VALUES(?,?,?,?,?,?)";
 private static final String READ_ALL_QUERY = "SELECT * FROM richieste";
 private static final String DELETE_QUERY= "DELETE FROM richieste WHERE idA = ?";
 //private static final String UPDATE_QUERY = "UPDATE carrello SET sconto =? , prezzo =?";
}
