package View;
/**
 * Sample Skeleton for 'Cart.fxml' Controller Class
 */

import java.net.URL;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import Control.CarrelloDAO;
import Control.SingInDAO;
import Control.LibroDAO;

import Control.ClienteDAO;
import Control.DipendenteDAO;
import Control.ProdottoDAO;
import Model.*;
import Control.RichiestaDAO;
import Control.ArticoliCDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javax.print.attribute.standard.DateTimeAtCreation;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.parseDouble;

public class CartControl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="TotalL"
    private Label TotalL; // Value injected by FXMLLoader

    @FXML // fx:id="CartList"
    private ListView<String> CartList; // Value injected by FXMLLoader

    @FXML // fx:id="DeletButton"
    private Button DeletButton; // Value injected by FXMLLoader

    @FXML // fx:id="LabeMail"
    private Label LabeMail; // Value injected by FXMLLoader

    @FXML // fx:id="IdRichiesta"
    private TextField IdRichiesta; // Value injected by FXMLLoader

    @FXML // fx:id="Payement"
    private SplitMenuButton Payement; // Value injected by FXMLLoader



    @FXML // fx:id="DeleteId"
    private TextField DeleteId; // Value injected by FXMLLoader

    @FXML
    private Label LPay;

    @FXML // fx:id="PriceT"
    private Label PriceT; // Value injected by FXMLLoader

    @FXML // fx:id="CartadiCredito"
    private MenuItem CartadiCredito; // Value injected by FXMLLoader

    @FXML // fx:id="CarrelloB"
    private Button CarrelloB; // Value injected by FXMLLoader

    @FXML // fx:id="Contanti"
    private MenuItem Contanti; // Value injected by FXMLLoader

    @FXML // fx:id="LCartidP"
    private Label LCartidP; // Value injected by FXMLLoader

    @FXML // fx:id="lrichiesta"
    private Label lrichiesta; // Value injected by FXMLLoader

    @FXML // fx:id="CreateRequestB"
    private Button CreateRequestB; // Value injected by FXMLLoader

    @FXML
    private Label LidEsistente;

    @FXML
    private ImageView screenblue;

    @FXML
    private ImageView Icart;

    @FXML
    private ImageView Ieuro;

    @FXML
    private ImageView Ivisa;

    @FXML // fx:id="Tquantita"
    private TextField Tquantita; // Value injected by FXMLLoader

    @FXML // fx:id="Lquantita"
    private Label Lquantita; // Value injected by FXMLLoader






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


    @FXML
    public boolean DeleteP(ActionEvent event) {
        carrelli= CarrelloDAO.getInstance().getCarrello();
        int quantita= 0;
        double prezzot=0;
        String prodotto=null;
        for(Carrello cart : carrelli){
            if(cart.getProdotto().equalsIgnoreCase(DeleteId.getText())){
                quantita=cart.getQuantita();
               prezzot=cart.getPrezzoTot();
               prodotto=cart.getProdotto();
            }
        }
        prodotti=ProdottoDAO.getInstance().getProdotto();
        double prezzo=0;
        for(Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(DeleteId.getText())){
                prezzo=p.getPrezzo();
            }
        }
        if(Tquantita.getText().equals("")){
            Tquantita.setText("1");
        }
        if (Integer.parseInt(Tquantita.getText())>quantita){
            Tquantita.setText(String.valueOf(quantita));
        }
        if(quantita==1 || quantita==Integer.parseInt(Tquantita.getText())){

                if(DeleteId.getText().equalsIgnoreCase(prodotto)){
                    boolean p= updateP();
                    boolean ac= updateArtC();
                    boolean l= updateL();
                    Connection conn = null;
                    PreparedStatement preparedStatement = null;
                    try {
                        conn = createConnection();
                        preparedStatement = conn.prepareStatement(DELETE_QUERY);
                        preparedStatement.setString(1, DeleteId.getText());
                        preparedStatement.execute();
                        CarrelloDAO.getInstance().load();
                        String accesso=null;
                        singin = SingInDAO.getInstance().getSingIn();
                        for (SingIn s : singin){
                            accesso= s.getEmail();
                        }
                        LabeMail.setText(accesso);
                        double prezzotot=0;
                        carrelli = CarrelloDAO.getInstance().getCarrello();
                        ObservableList<String> pro = null;
                        ObservableList<Integer> quan = null;
                        ObservableList<Double> pre = null;
                        CartList.getItems().clear();

                        for ( Carrello cart : carrelli){
                            if(cart.getEmail().equals(LabeMail.getText())){
                                prezzotot=prezzotot+cart.getPrezzoTot();
                                pro = FXCollections.observableArrayList(cart.getProdotto());
                                quan=  FXCollections.observableArrayList(cart.getQuantita());
                                pre=  FXCollections.observableArrayList(cart.getPrezzoTot());
                                CartList.getItems().addAll("Id: "+pro+" Quantità: "+String.valueOf(quan)+" Prezzo: "+String.valueOf(pre));
                            }



                        }


                        PriceT.setText(String.valueOf(prezzotot));
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
                }else{

                    DeleteId.setText("Id non trovato");
                }
            }

        else {
            boolean p= updateP();
            boolean ac= updateArtC();
            boolean l= updateL();
            Connection conn = null;
            PreparedStatement ps = null;
            double prezzoto=prezzot-(prezzo*Integer.parseInt(Tquantita.getText()));
            double number2 = (int)(Math.round(prezzoto * 100))/100.0;
            try {
                conn = createConnection();
                ps = conn.prepareStatement(UPDATE_QUERY);
                ps.setInt(1, quantita-Integer.parseInt(Tquantita.getText()));
                ps.setDouble(2,number2 );
                ps.setString(3, DeleteId.getText());
                ps.execute();
                CarrelloDAO.getInstance().load();
                String accesso=null;
                singin = SingInDAO.getInstance().getSingIn();
                for (SingIn s : singin){
                    accesso= s.getEmail();
                }
                LabeMail.setText(accesso);
                double prezzotot=0;
                carrelli = CarrelloDAO.getInstance().getCarrello();
                ObservableList<String> pro = null;
                ObservableList<Integer> quan = null;
                ObservableList<Double> pre = null;
                CartList.getItems().clear();

                for ( Carrello cart : carrelli){
                    if(cart.getEmail().equals(LabeMail.getText())){
                        prezzotot=prezzotot+cart.getPrezzoTot();
                        pro = FXCollections.observableArrayList(cart.getProdotto());
                        quan=  FXCollections.observableArrayList(cart.getQuantita());
                        pre=  FXCollections.observableArrayList(cart.getPrezzoTot());
                        CartList.getItems().addAll("Id: "+pro+" Quantità: "+String.valueOf(quan)+" Prezzo: "+String.valueOf(pre));
                    }



                }


                PriceT.setText(String.valueOf(prezzotot));
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


       return false;
    }

    public boolean addR() {
        Connection conn = null;
        PreparedStatement ps = null;
        carrelli = CarrelloDAO.getInstance().getCarrello();
        Date data1=new Date (System.currentTimeMillis());
        double prezzotot=0;

        for ( Carrello c : carrelli) {
            if (c.getEmail().equals(LabeMail.getText())) {
                prezzotot = prezzotot + c.getPrezzoTot();
            }
        }

        if(prezzotot > 0){
            try {
                double number2 = (int)(Math.round(prezzotot * 100))/100.0;

                conn = createConnection();
                ps = conn.prepareStatement(CREATE_QUERY_R);

                ps.setString(1, IdRichiesta.getText());
                ps.setDate(2, data1);
                ps.setDouble(3, number2);
                ps.setString(4, LPay.getText());

                ps.setString(5, null);
                ps.setString(6, LabeMail.getText());

                ps.execute();
                RichiestaDAO.getInstance().load();
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
        LidEsistente.setText("Carrello vuoto");
      return false;
    }

    @FXML
    public boolean NewRequest(ActionEvent event) {

        if(LPay.getText().equals("")){
            LPay.setText("Contanti");
        }
        String accesso=null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LabeMail.setText(accesso);
        clienti = ClienteDAO.getInstance().getCliente();
        richieste = RichiestaDAO.getInstance().getRichiesta();
        for(RichiestaAcquisto r : richieste){
            if(r.getIdA().equalsIgnoreCase(IdRichiesta.getText())){
                LidEsistente.setText("Id già esistente");
                return false;
            }

        }
        for (Cliente c : clienti){
           if(IdRichiesta.getText().equals("")){
                LidEsistente.setText("Dati Errati");

                return false;
            }
            else{
               LidEsistente.setText("Richiesta inviata");

               boolean b = addR();
               boolean d= deleteCart();

                return b;



            }
        }


        return false;



    }




    @FXML
    public void ShowList(ActionEvent event) {


        String accesso=null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LabeMail.setText(accesso);
        double prezzotot=0;
        carrelli = CarrelloDAO.getInstance().getCarrello();
        ObservableList<String> pro = null;
        ObservableList<Integer> quan = null;
        ObservableList<Double> pre = null;
        CartList.getItems().clear();

        for ( Carrello c : carrelli){
            if(c.getEmail().equals(LabeMail.getText())){
                prezzotot=prezzotot+c.getPrezzoTot();
                pro = FXCollections.observableArrayList(c.getProdotto());
                quan=  FXCollections.observableArrayList(c.getQuantita());
                pre=  FXCollections.observableArrayList(c.getPrezzoTot());
                CartList.getItems().addAll("Id: "+pro+" Quantità: "+String.valueOf(quan)+" Prezzo: "+String.valueOf(pre));
            }



        }


     PriceT.setText(String.valueOf(prezzotot));


    }

    @FXML
    void Addmail(MouseEvent mouseEvent){
        String accesso=null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
           accesso= s.getEmail();
        }
        LabeMail.setText(accesso);
    }
    @FXML
    void PayContanti(ActionEvent event) {
        LPay.setText("Contanti");
    }

    @FXML
    void Paycreditcard(ActionEvent event) {
        LPay.setText("CartadiCredito");
    }

    public boolean updateP() {
        Double prezzo=0.00;
        int quantita=0;
        int quant=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for(Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(DeleteId.getText())){
                prezzo=p.getPrezzo();
                quantita=p.getQuantita();
            }
        }
        double number2 = (int)(Math.round(prezzo * 100))/100.0;
        carrelli =CarrelloDAO.getInstance().getCarrello();
        for (Carrello car : carrelli){
            if(car.getProdotto().equalsIgnoreCase(DeleteId.getText())){
                quant= car.getQuantita();
            }
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_P);
            //ps.setString(1, TFQuantitaUp);
            ps.setInt(1,quantita+Integer.parseInt(Tquantita.getText()));
            ps.setDouble(2, number2);
            ps.setString(3, DeleteId.getText());

            ps.execute();
            ProdottoDAO.getInstance().load();
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

    public boolean updateArtC() {


        Connection conn = null;
        PreparedStatement ps = null;
        int Quantita=0;
        double prezzo=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(DeleteId.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }
        double number2 = (int)(Math.round(prezzo * 100))/100.0;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_AC);
            ps.setInt(1, Quantita);
            ps.setDouble(2, number2);
            ps.setString(3, DeleteId.getText());
            ps.execute();
            ArticoliCDAO.getInstance().load();
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

    public boolean updateL() {
        Connection conn = null;
        PreparedStatement ps = null;
        int Quantita=0;
        double prezzo=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(DeleteId.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }
        double number2 = (int)(Math.round(prezzo * 100))/100.0;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_L);
            ps.setInt(1, Quantita);
            ps.setDouble(2, number2);
            ps.setString(3, DeleteId.getText());
            ps.execute();
            LibroDAO.getInstance().load();
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
    public boolean deleteCart() {
        clienti = ClienteDAO.getInstance().getCliente();
        for(Cliente c : clienti){
            if(c.getEmail().equals(LabeMail.getText())){
                Connection conn = null;
                PreparedStatement preparedStatement = null;
                try {
                    conn = createConnection();
                    preparedStatement = conn.prepareStatement(DELETE_QUERY_CART);
                    preparedStatement.setString(1, LabeMail.getText());
                    preparedStatement.execute();
                    CarrelloDAO.getInstance().load();
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
        }
        return false;

    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert screenblue != null : "fx:id=\"screenblue\" was not injected: check your FXML file 'Cart.fxml'.";
        assert TotalL != null : "fx:id=\"TotalL\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CartList != null : "fx:id=\"CartList\" was not injected: check your FXML file 'Cart.fxml'.";
        assert DeletButton != null : "fx:id=\"DeletButton\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LabeMail != null : "fx:id=\"LabeMail\" was not injected: check your FXML file 'Cart.fxml'.";
        assert IdRichiesta != null : "fx:id=\"IdRichiesta\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Payement != null : "fx:id=\"Payement\" was not injected: check your FXML file 'Cart.fxml'.";

        assert DeleteId != null : "fx:id=\"DeleteId\" was not injected: check your FXML file 'Cart.fxml'.";
        assert PriceT != null : "fx:id=\"PriceT\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CartadiCredito != null : "fx:id=\"CartadiCredito\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CarrelloB != null : "fx:id=\"CarrelloB\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Contanti != null : "fx:id=\"Contanti\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LCartidP != null : "fx:id=\"LCartidP\" was not injected: check your FXML file 'Cart.fxml'.";
        assert lrichiesta != null : "fx:id=\"lrichiesta\" was not injected: check your FXML file 'Cart.fxml'.";
        assert CreateRequestB != null : "fx:id=\"CreateRequestB\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LidEsistente != null : "fx:id=\"LidEsistente\" was not injected: check your FXML file 'Cart.fxml'.";
        assert LPay != null : "fx:id=\"LPay\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Icart != null : "fx:id=\"Icart\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Ieuro != null : "fx:id=\"Ieuro\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Ivisa != null : "fx:id=\"Ivisa\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Tquantita != null : "fx:id=\"Tquantita\" was not injected: check your FXML file 'Cart.fxml'.";
        assert Lquantita != null : "fx:id=\"Lquantita\" was not injected: check your FXML file 'Cart.fxml'.";



    }
    private  LinkedList<Carrello> carrelli;
    private static final String READ_ALL_QUERY = "SELECT * FROM carrelli";
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/persistenza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
    public static final String USER="root";
    public static final String PASS="";
    private static final String DELETE_QUERY= "DELETE FROM carrelli WHERE Prodotti =(?) ";
    private static final String READ_ALL_QUERY_R = "SELECT * FROM richieste";
    private static final String CREATE_QUERY_R ="INSERT INTO richieste VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_QUERY_P = "UPDATE prodotti SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private LinkedList<Prodotto> prodotti;
    private LinkedList<RichiestaAcquisto> richieste;
    private LinkedList<Cliente> clienti;
    private LinkedList<SingIn> singin;
    private static final String UPDATE_QUERY_L = "UPDATE libri SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private static final String UPDATE_QUERY_AC = "UPDATE articoli SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private static final String DELETE_QUERY_CART= "DELETE FROM carrelli WHERE Email =(?) ";
    private static final String UPDATE_QUERY = "UPDATE carrelli SET Quantità=(?), Prezzo=(?) WHERE Prodotti=(?) ";

    public void Alphabetic(javafx.scene.input.KeyEvent keyEvent) {
        Tquantita.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") ) {

                Tquantita.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

}

