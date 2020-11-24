package DAO;



import Model.Libro;
import Model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LibroDAO extends BaseDAO implements DAO<Libro> {
   private LibroDAO() {
	   libri = new LinkedList<Libro>();
   }
   public static LibroDAO getInstance() {
	   if(instance == null) {
		   instance = new LibroDAO();
	   }
	   return instance;
   }
	public void load() {
		LinkedList<Libro> libri = new LinkedList<Libro>();
		ProprietarioDAO proprietarioDAO = ProprietarioDAO.getInstance();
		Libro l= null;
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
				l = new Libro(result.getString(1), result.getDouble(2),result.getInt(3), null, result.getString(5),result.getString(6), result.getString(7),result.getString(8));
				p = proprietarioDAO.getProprietario(result.getString(4));
				l.setProprietario(p);
				libri.add(l);
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
		this.libri= libri;
	}
   
	public boolean add(Libro libro) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(CREATE_QUERY);
			preparedStatement.setString(1, libro.getId());
			preparedStatement.setDouble(2, libro.getPrezzo());
			preparedStatement.setInt(3, libro.getQuantita());
			preparedStatement.setString(4, libro.getTitolo());
			preparedStatement.setString(5, libro.getAutore());
			preparedStatement.setString(6, libro.getCasaEd());
			preparedStatement.setString(7, libro.getIsbn());
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
	
	public boolean delete(Libro libro) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = super.createConnection();
			preparedStatement = conn.prepareStatement(DELETE_QUERY);
			preparedStatement.setString(1, libro.getId());
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
		public LinkedList<Libro> getLibro(){
			return libri;
		}
		public Libro getLibro(String id){
			Libro libro = null;
			for(Libro l:libri) {
				if(l.getId().equalsIgnoreCase(id)) {
					libro = l;
					break;
				}
			}
			return libro;
		}
	public boolean updateL(Libro libri) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = super.createConnection();
			ps = conn.prepareStatement(UPDATE_QUERY_L);
			ps.setInt(1, libri.getQuantita());
			ps.setDouble(2, libri.getPrezzo());
			ps.setString(3, libri.getId());
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
		
   private static LibroDAO instance;
   private LinkedList<Libro> libri;
   
   private static final String CREATE_QUERY ="INSERT INTO libri VALUES(?,?,?,?,?,?,?)";
   private static final String READ_ALL_QUERY = "SELECT * FROM libri";
   private static final String DELETE_QUERY= "DELETE FROM libri WHERE id = ?";
	private static final String UPDATE_QUERY_L = "UPDATE libri SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";

}
