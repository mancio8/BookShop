package DAO;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class PagamentoDAO extends BaseDAO implements DAO<Pagamento>{
    private PagamentoDAO() {
        pagamenti = new LinkedList<Pagamento>();
    }
    public static PagamentoDAO getInstance() {
        if(instance == null) {
            instance = new PagamentoDAO();
        }
        return instance;
    }

    public void load() {
        LinkedList<Pagamento> pagamenti = new LinkedList<Pagamento>();
        Pagamento pag = null;
        ClienteDAO clienti = ClienteDAO.getInstance();


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
                pag = new Pagamento(null, result.getDate(2), result.getDouble(3),result.getDouble(4));

                c = clienti.getCliente(result.getString(1));
                pag.setEmail(c);



                pagamenti.add(pag);
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
        this.pagamenti = pagamenti;
    }

    @Override
    public boolean delete(Pagamento pagamento) {
        return false;
    }

    public boolean add(Pagamento pagamento) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = super.createConnection();
            ps = conn.prepareStatement(CREATE_QUERY);

            ps.setString(1, pagamento.getEmail().getEmail());
            ps.setDate(2, (java.sql.Date) pagamento.getData());
            ps.setDouble(3, pagamento.getSconto());
            ps.setDouble(4, pagamento.getPrezzo_T());



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




    private static PagamentoDAO instance;
    private LinkedList<Pagamento> pagamenti;

    private static final String CREATE_QUERY ="INSERT INTO pagamenti VALUES(?,?,?,?)";
    private static final String READ_ALL_QUERY = "SELECT * FROM pagamenti";


}
