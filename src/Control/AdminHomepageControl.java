package Control; /**
 * Sample Skeleton for 'AdminHompage.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;

import DAO.*;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminHomepageControl {
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

    private GestoreDB persistenza;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;



    @FXML // fx:id="bluescreen"
    private ImageView bluescreen; // Value injected by FXMLLoader

    @FXML // fx:id="Iclient"
    private ImageView Iclient; // Value injected by FXMLLoader

    @FXML // fx:id="Iprod"
    private ImageView Iprod; // Value injected by FXMLLoader

    @FXML // fx:id="Idip"
    private ImageView Idip; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Lproduct"
    private Label Lproduct; // Value injected by FXMLLoader

    @FXML // fx:id="LClient"
    private Label LClient; // Value injected by FXMLLoader

    @FXML // fx:id="VisLisP"
    private Button VisLisP; // Value injected by FXMLLoader

    @FXML // fx:id="LAdmin"
    private Label LAdmin; // Value injected by FXMLLoader

    @FXML // fx:id="VisLisD"
    private Button VisLisD; // Value injected by FXMLLoader

    @FXML // fx:id="BPro"
    private Button BPro; // Value injected by FXMLLoader

    @FXML // fx:id="VisListC"
    private Button VisListC; // Value injected by FXMLLoader

    @FXML // fx:id="BDip"
    private Button BDip; // Value injected by FXMLLoader

    @FXML // fx:id="ListClient"
    private ListView<String> ListClient; // Value injected by FXMLLoader

    @FXML // fx:id="LDip"
    private Label LDip; // Value injected by FXMLLoader

    @FXML // fx:id="ListDip"
    private ListView<String> ListDip; // Value injected by FXMLLoader

    @FXML // fx:id="Listpro"
    private ListView<String> Listpro; // Value injected by FXMLLoader

    @FXML // fx:id="BClient"
    private Button BClient; // Value injected by FXMLLoader




    @FXML
    void ClientG(ActionEvent event) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestioneClienti.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DipG(ActionEvent event) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestioneDipendenti.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ProG(ActionEvent event) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/GestioneProdotto.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void VListaCliente(ActionEvent event) {
        List<Cliente> clienti = ClienteDAO.getInstance().getCliente();
        Iterator<Cliente> it = clienti.iterator();
        ObservableList<String> client = null;
        ListClient.getItems().clear();
        while(it.hasNext()){
            client = FXCollections.observableArrayList(it.next().getEmail());
            ListClient.getItems().addAll(client);
        }


    }

    @FXML
    void VListaProdotti(ActionEvent event) {
        List<Prodotto> prodotti = ProdottoDAO.getInstance().getProdotto();
        Iterator<Prodotto> itp = prodotti.iterator();
        ObservableList<String> prod = null;
        Listpro.getItems().clear();
        while(itp.hasNext()){
            prod = FXCollections.observableArrayList(itp.next().getId());
            Listpro.getItems().addAll(prod);
        }
    }

    @FXML
    void VListaDip(ActionEvent event) {

        List<Dipendente> dipendenti = DipendenteDAO.getInstance().getDipendenti();
        Iterator<Dipendente> it = dipendenti.iterator();

        ObservableList<String> dip = null;

        ListDip.getItems().clear();
        while(it.hasNext()){
            dip = FXCollections.observableArrayList(it.next().getEmail());

            ListDip.getItems().addAll(dip);
        }


    }
    @FXML
    void mail(MouseEvent mouaeEvent){
        String accesso= null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LAdmin.setText(accesso);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bluescreen != null : "fx:id=\"bluescreen\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert ListClient != null : "fx:id=\"ListClient\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert ListDip != null : "fx:id=\"ListDip\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert Listpro != null : "fx:id=\"Listpro\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert BClient != null : "fx:id=\"BClient\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert BDip != null : "fx:id=\"BDip\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert BPro != null : "fx:id=\"BPro\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert LAdmin != null : "fx:id=\"LAdmin\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert LClient != null : "fx:id=\"LClient\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert LDip != null : "fx:id=\"LDip\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert Lproduct != null : "fx:id=\"Lproduct\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert VisListC != null : "fx:id=\"VisListC\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert VisLisP != null : "fx:id=\"VisLisP\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert VisLisD != null : "fx:id=\"VisLisD\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert Iclient != null : "fx:id=\"Iclient\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert Iprod != null : "fx:id=\"Iprod\" was not injected: check your FXML file 'AdminHompage.fxml'.";
        assert Idip != null : "fx:id=\"Idip\" was not injected: check your FXML file 'AdminHompage.fxml'.";

    }
    private List<Cliente> clienti;
    private List<Dipendente> dipendenti;
    private List<Prodotto> prodotti;
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/persistenza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
    public static final String USER="root";
    public static final String PASS="";
    private static final String READ_ALL_QUERY = "SELECT * FROM clienti";
    private static final String READ_ALL_QUERY_DIP = "SELECT * FROM dipendenti";
    private static final String READ_ALL_QUERY_Prod = "SELECT * FROM prodotti";
    private LinkedList<SingIn> singin;
}
