package DAO;


import Model.SingIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SingInDAO extends BaseDAO implements DAO<SingIn>{
    private SingInDAO() {
        sing = new LinkedList<SingIn>();
    }

    public static SingInDAO getInstance() {
        if(instance == null) {
            instance = new SingInDAO();
        }
        return instance;
    }
    public void load() {
        LinkedList<SingIn> sing = new LinkedList<SingIn>();
        SingIn u = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = super.createConnection();
            preparedStatement = conn.prepareStatement(READ_ALL_QUERY);
            preparedStatement.execute();
            result = preparedStatement.getResultSet();

            while(result.next()) {
                u = new SingIn(result.getString(1) );
                sing.add(u);
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

        this.sing=sing;
    }

    @Override
    public boolean add(SingIn sing) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = super.createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY);

            preparedStatement.setString(1, sing.getEmail());

            preparedStatement.executeUpdate();
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

    public boolean delete(SingIn sing) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = super.createConnection();
            preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, sing.getEmail());
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

    public LinkedList<SingIn> getSingIn(){
        return sing;
    }
    public SingIn getSingIn(String email){
        SingIn singin = null;
        for(SingIn s: sing) {
            if(s.getEmail().equalsIgnoreCase(email)) {
                singin = s;
                break;
            }
        }
        return singin;
    }
    private static SingInDAO instance;
    private LinkedList<SingIn> sing;



    private static final String CREATE_QUERY ="INSERT INTO accesso VALUES(?)";
    private static final String READ_ALL_QUERY = "SELECT * FROM accesso";
    private static final String DELETE_QUERY= "DELETE FROM accesso WHERE email =(?) ";
}
