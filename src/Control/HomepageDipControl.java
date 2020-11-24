package Control; /**
 * Sample Skeleton for 'HomePageDip.fxml' Controller Class
 */

/**
 * Sample Skeleton for 'HomePageDip.fxml' Controller Class
 */

import java.net.URL;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.RichiestaDAO;
import Model.RichiestaAcquisto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class HomepageDipControl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // fx:id="screen"
    private ImageView screen; // Value injected by FXMLLoader

    @FXML // fx:id="ImageR"
    private ImageView ImageR; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="IdRic"
    private Label IdRic; // Value injected by FXMLLoader

    @FXML // fx:id="IdRic"
    private Label richiestaeff; // Value injected by FXMLLoader

    @FXML // fx:id="TextRic"
    private TextField TextRic; // Value injected by FXMLLoader

    @FXML // fx:id="LDip"
    private Label LDip; // Value injected by FXMLLoader

    @FXML // fx:id="EliminaRic"
    private Button EliminaRic; // Value injected by FXMLLoader

    @FXML // fx:id="VRic"
    private Button VRic; // Value injected by FXMLLoader

    @FXML // fx:id="ListaRichieste"
    private ListView<String> ListaRichieste; // Value injected by FXMLLoader

    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/persistenza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
    public static final String USER="root";
    public static final String PASS="";

    public static Connection createConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(DBURL, USER, PASS);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }

    public boolean delete(RichiestaAcquisto richiesta) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn =createConnection();
            preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setString(1, richiesta.getIdA());
            preparedStatement.execute();
            RichiestaDAO.getInstance().load();
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

    @FXML
    void VisRichiesta(ActionEvent event) {
        List<RichiestaAcquisto> richieste = RichiestaDAO.getInstance().getRichiesta();
        Iterator<RichiestaAcquisto> it = richieste.iterator();

        ObservableList<String> ric = null;

        ListaRichieste.getItems().clear();
        while(it.hasNext()){
            ric = FXCollections.observableArrayList(it.next().getIdA());

            ListaRichieste.getItems().addAll(ric);
        }

    }

    @FXML
    public boolean DeleteRichiesta(ActionEvent event) {

        richieste = RichiestaDAO.getInstance().getRichiesta();
        for(RichiestaAcquisto r : richieste){
            if(r.getIdA().equals(TextRic.getText())){
                richiestaeff.setText("Richiesta eliminata");
                Connection conn = null;
                PreparedStatement preparedStatement = null;
                try {
                    conn = createConnection();
                    preparedStatement = conn.prepareStatement(DELETE_QUERY);
                    preparedStatement.setString(1, TextRic.getText());
                    preparedStatement.execute();
                    RichiestaDAO.getInstance().load();
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        preparedStatement.close();
                    } catch (Exception sse) {
                        sse.printStackTrace();
                    }
                    try {
                        conn.close();
                    } catch (Exception cse) {
                        cse.printStackTrace();
                    }
                }

                return false;

            }else{
                richiestaeff.setText("Dati errati");
            }
        }

           return false;


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert IdRic != null : "fx:id=\"IdRic\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert richiestaeff != null : "fx:id=\"IdRic\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert TextRic != null : "fx:id=\"TextRic\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert LDip != null : "fx:id=\"LDip\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert EliminaRic != null : "fx:id=\"EliminaRic\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert VRic != null : "fx:id=\"VRic\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert ListaRichieste != null : "fx:id=\"ListaRichieste\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert screen != null : "fx:id=\"screen\" was not injected: check your FXML file 'HomePageDip.fxml'.";
        assert ImageR != null : "fx:id=\"ImageR\" was not injected: check your FXML file 'HomePageDip.fxml'.";

    }
    private LinkedList<RichiestaAcquisto> richieste;
    private static final String READ_ALL_QUERY = "SELECT * FROM richieste";
    private static final String DELETE_QUERY= "DELETE FROM richieste WHERE idAcquisto = ?";
}
