package DAO;

import Model.Carrello;
import Model.Cliente;
import Model.Prodotti_Carrello;
import Model.Prodotto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Prodotti_CarrelloDAO extends BaseDAO implements DAO<Prodotti_Carrello> {

    private Prodotti_CarrelloDAO() {
        p_carrelli = new LinkedList<Prodotti_Carrello>();
    }

    public static Prodotti_CarrelloDAO getInstance() {
        if(instance == null) {
            instance = new Prodotti_CarrelloDAO();
        }
        return instance;
    }
    public void load() {
        LinkedList<Prodotti_Carrello> p_carrelli = new LinkedList<Prodotti_Carrello>();
        ClienteDAO clientiDao=ClienteDAO.getInstance();
        ProdottoDAO prodottoDAO = ProdottoDAO.getInstance();
        Prodotto prod = null;
        Cliente client = null;
        Prodotti_Carrello c = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {


            conn = super.createConnection();
            preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();

            while(result.next()) {
                c = new Prodotti_Carrello(result.getString(1), null,null, result.getInt(4), null,result.getDouble(6));
                client = clientiDao.getCliente(result.getString(2));
                c.setCliente(client);
                prod = prodottoDAO.getProdotto(result.getString(3));
                c.setProdotto(prod);
                prod = prodottoDAO.getPrezzo(result.getDouble(5));
                c.setPrezzo_u(prod);


                p_carrelli.add(c);
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
        this.p_carrelli=p_carrelli;
    }

    @Override
    public boolean add(Prodotti_Carrello p_carrello) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = super.createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, p_carrello.getIdC());
            preparedStatement.setString(2, p_carrello.getCliente().getEmail());
            preparedStatement.setString(3, p_carrello.getProdotto().getId());
            preparedStatement.setInt(4, p_carrello.getQuantita());
            preparedStatement.setDouble(5, p_carrello.getPrezzo_u().getPrezzo());
            preparedStatement.setDouble(6, p_carrello.getPrezzoTot());
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

    public boolean delete(Prodotti_Carrello p_carrello) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = super.createConnection();
            preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, p_carrello.getProdotto().getId());
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
    public boolean deleteCart(Prodotti_Carrello p_carrello) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = super.createConnection();
            preparedStatement = conn.prepareStatement(DELETE_QUERY_CART);
            preparedStatement.setString(1, p_carrello.getCliente().getEmail());
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
    public LinkedList<Prodotti_Carrello> getP_Carrello(){
        return p_carrelli;
    }
    public Carrello getP_Carrello(String idc){
        Prodotti_Carrello carrello = null;
        for(Prodotti_Carrello c: p_carrelli) {
            if(c.getIdC().equalsIgnoreCase(idc)) {
                carrello = c;
                break;
            }
        }
        return carrello;
    }
    public boolean update(Prodotti_Carrello carrelli) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = super.createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY);
            ps.setInt(1, carrelli.getQuantita());
            ps.setDouble(2, carrelli.getPrezzoTot());
            ps.setString(3, carrelli.getProdotto().getId());
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
    private static Prodotti_CarrelloDAO instance;
    private  LinkedList<Prodotti_Carrello> p_carrelli;



    private static final String CREATE_QUERY ="INSERT INTO Prodotti_carrello VALUES(?,?,?,?,?,?)";
    private static final String READ_ALL_QUERY = "SELECT * FROM Prodotti_carrello";
    private static final String DELETE_QUERY= "DELETE FROM Prodotti_carrello WHERE Prodotti =(?) ";
    private static final String DELETE_QUERY_CART= "DELETE FROM Prodotti_carrello WHERE Email =(?) ";
    private static final String UPDATE_QUERY = "UPDATE Prodotti_carrello SET Quantità=(?), Prezzo Totale=(?) WHERE Prodotti=(?) ";



}
