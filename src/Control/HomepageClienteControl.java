package Control; /**
 * Sample Skeleton for 'HomepageCliente.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomepageClienteControl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    private Label LCart;

    @FXML
    private ImageView screenB;

    @FXML
    private ImageView Ilib;

    @FXML
    private ImageView Icart;

    @FXML
    private ImageView IArtc;


    @FXML
    private Label Lniente;



    @FXML
    private Label TFIDc;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="ViewCarrello"
    private Button ViewCarrello; // Value injected by FXMLLoader

    @FXML // fx:id="MostraProdotto"
    private Button MostraLibri; // Value injected by FXMLLoader

    @FXML // fx:id="Lidpro"
    private Label Lidpro; // Value injected by FXMLLoader

    @FXML // fx:id="LClient"
    private Label LClient; // Value injected by FXMLLoader

    @FXML // fx:id="ListaLibri"
    private ListView<String> ListaLibri; // Value injected by FXMLLoader

    @FXML // fx:id="AddCarello"
    private Button AddCarello; // Value injected by FXMLLoader

    @FXML // fx:id="MostraProdotto1"
    private Button MostraArticoli; // Value injected by FXMLLoader

    @FXML // fx:id="TextPordotto"
    private TextField TextPordotto; // Value injected by FXMLLoader

    @FXML // fx:id="Tquantita"
    private TextField Tquantita; // Value injected by FXMLLoader

    @FXML // fx:id="Lquantita"
    private Label Lquantita; // Value injected by FXMLLoader

    @FXML // fx:id="ListaArticoli"
    private ListView<String> ListaArticoli; // Value injected by FXMLLoader



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
    public boolean addCarrello(ActionEvent event) {

        String accesso= null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LClient.setText(accesso);

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String idcart=null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(Search_ALL_QUERY);

            preparedStatement.setString(1, LClient.getText());
            preparedStatement.execute();
            CarrelloDAO.getInstance().load();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                idcart = rs.getString("IdCarrello");
                TFIDc.setText(idcart);
            }
            rs.close();
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


        Connection conn2 = null;
        PreparedStatement preparedStatement2 = null;
        String prodotto=null;
        String product=null;
        try {
            conn2 = createConnection();
            preparedStatement2 = conn2.prepareStatement(Search_ALL_QUERY_P);

            preparedStatement2.setString(1, TFIDc.getText());
            preparedStatement2.execute();
            Prodotti_CarrelloDAO.getInstance().load();

            ResultSet rs = preparedStatement2.executeQuery();
            while(rs.next()){
                product = rs.getString("Prodotto");
                if(product.equalsIgnoreCase(TextPordotto.getText())){
                    prodotto = product;
                }
            }
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement2.close();
            } catch(Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn2.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }


        if (Tquantita.getText().equals("")) {
            Tquantita.setText("1");
        }

        String id=null;
        double prezzoTot=0;
       carrelli= Prodotti_CarrelloDAO.getInstance().getP_Carrello();
        for(Prodotti_Carrello cart : carrelli){
            if(idcart.equalsIgnoreCase(TFIDc.getText())) {

                id = cart.getIdC();

            }
            if (TFIDc.getText().equalsIgnoreCase(cart.getIdC()) && TextPordotto.getText().equalsIgnoreCase(prodotto)) {


                //TextPordotto.setText("Id gia esistente");

                Connection conn3 = null;
                PreparedStatement preparedStatement3 = null;

                int quantita=0;
                double prezzo_t=0.0;
                double prezzo_u=0.0;
                try {
                    conn3 = createConnection();
                    preparedStatement3 = conn3.prepareStatement(Search_ALL_QUERY_Price);

                    preparedStatement3.setString(1, TFIDc.getText());
                    preparedStatement3.setString(2, TextPordotto.getText());
                    preparedStatement3.execute();
                    Prodotti_CarrelloDAO.getInstance().load();

                    ResultSet rs = preparedStatement3.executeQuery();
                    while(rs.next()){
                        quantita = rs.getInt("Quantità");
                        prezzo_t = rs.getDouble("Prezzo_Totale");
                        prezzo_u = rs.getDouble("Prezzo_unitario");
                    }
                    rs.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        preparedStatement3.close();
                    } catch(Exception sse) {
                        sse.printStackTrace();
                    }
                    try {
                        conn3.close();
                    } catch(Exception cse) {
                        cse.printStackTrace();
                    }
                }

                boolean pro = updateP();
                boolean l = updateL();
                boolean ac = updateArtC();
                Connection conn4 = null;
                PreparedStatement ps = null;
                try {
                   double prezzo_tot = prezzo_t + (prezzo_u * Double.parseDouble(Tquantita.getText()));
                   double round_prezzo = (int)(Math.round(prezzo_tot * 100))/100.0;
                    conn4 = createConnection();
                    ps = conn4.prepareStatement(UPDATE_QUERY);
                    ps.setInt(1, quantita + Integer.parseInt(Tquantita.getText()));
                    ps.setDouble(2, round_prezzo);
                    ps.setString(3, TextPordotto.getText());
                    ps.setString(4, LClient.getText());
                    ps.execute();
                    Prodotti_CarrelloDAO.getInstance().load();
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
                        conn4.close();
                    } catch(Exception cse) {
                        cse.printStackTrace();
                    }
                }
                Lniente.setText("Quantità modificata");
                return false;

            }
        }


                //Fine
                boolean b = true;




                PreparedStatement ps = null;
                prodotti = ProdottoDAO.getInstance().getProdotto();
                int quant = 0;
                double prezzo_unit = 0;
                boolean prod = false;
                for (Prodotto p : prodotti) {
                    if (p.getId().equalsIgnoreCase(TextPordotto.getText()) && p.getQuantita() > 0) {
                        prod = true;
                        prezzo_unit = p.getPrezzo();
                        quant = p.getQuantita();
                    }
                }

                if (b && prod && Integer.parseInt(Tquantita.getText()) > 0 && Integer.parseInt(Tquantita.getText()) <= quant) {

                    boolean pro = updateP();
                    boolean l = updateL();
                    boolean ac = updateArtC();
                    Lniente.setText("Prodotto inserito");
                    try {
                        double prezzo_unitario= prezzo_unit * Double.parseDouble(Tquantita.getText());
                        double r_prezzo_u= (int)(Math.round(prezzo_unitario * 100))/100.0;
                        conn = createConnection();
                        ps = conn.prepareStatement(CREATE_QUERY_C);
                        ps.setString(1, TFIDc.getText());
                        ps.setString(2, LClient.getText());
                        ps.setString(3, TextPordotto.getText());
                        ps.setInt(4, Integer.parseInt(Tquantita.getText()));
                        ps.setDouble(5,prezzo_unit);
                        ps.setDouble(6, r_prezzo_u);

                        ps.execute();


                        Prodotti_CarrelloDAO.getInstance().load();
                        return true;

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            ps.close();
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

                } else {
                    Lniente.setText("ERRORE");
                    return false;
                }


    }







    @FXML
    void apriCarrello(ActionEvent event) {

        try {

            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Cart.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void VisualizzaLista(ActionEvent event) {
        List<Libro> libri = LibroDAO.getInstance().getLibro();
        Iterator<Libro> it = libri.iterator();
        Iterator<Libro> itid = libri.iterator();
        Iterator<Libro> itpr = libri.iterator();
        Iterator<Libro> au = libri.iterator();
        Iterator<Libro> isbn = libri.iterator();
        Iterator<Libro> cased = libri.iterator();

        ObservableList<String> lib = null;
        ObservableList<String> libid = null;
        ObservableList<Double> libpr = null;
        ObservableList<String> aut = null;
        ObservableList<String> isb = null;
        ObservableList<String> edit = null;

        ListaLibri.getItems().clear();
        while(it.hasNext()&& itid.hasNext() && itpr.hasNext() && au.hasNext() && isbn.hasNext() && cased.hasNext()){
            lib = FXCollections.observableArrayList(it.next().getTitolo());
            libid = FXCollections.observableArrayList(itid.next().getId());
            libpr = FXCollections.observableArrayList(itpr.next().getPrezzo());
            aut = FXCollections.observableArrayList(au.next().getAutore());
            isb = FXCollections.observableArrayList(isbn.next().getIsbn());
            edit = FXCollections.observableArrayList(cased.next().getCasaEd());

            ListaLibri.getItems().addAll("- Nome: "+lib+" Id Prodotto: "+libid+" Prezzo: "+String.valueOf(libpr));
            ListaLibri.getItems().addAll("      *Autore: "+aut+" ISBN: "+isb+" Editore: "+edit);

        }

    }

    @FXML
    void VisualizzaListaArticoli(ActionEvent event) {
        List<articoloC>  articoli = ArticoliCDAO.getInstance().getArticolo();
        Iterator<articoloC> it = articoli.iterator();
        Iterator<articoloC> itid = articoli.iterator();
        Iterator<articoloC> itpr = articoli.iterator();

        ObservableList<String> art = null;
        ObservableList<String> artid = null;
        ObservableList<Double> pr = null;

        ListaLibri.getItems().clear();
        while(it.hasNext() && itid.hasNext() && itpr.hasNext()){
            art = FXCollections.observableArrayList(it.next().getTipo());
            artid= FXCollections.observableArrayList(itid.next().getId());
            pr=FXCollections.observableArrayList(itpr.next().getPrezzo());
            ListaLibri.getItems().addAll("- Nome: "+ art+ " Id Prodotto: "+artid+" Prezzo: "+String.valueOf(pr));


        }


    }

    @FXML
    public boolean controlloIdC(ActionEvent event) {
        String cart;
        boolean b = false;
    clienti = ClienteDAO.getInstance().getCliente();
        for(Cliente c : clienti){
            cart=c.getCarrello().getIdC();
           if(cart.equalsIgnoreCase(TFIDc.getText())){
               b=true;
           }

        }
        if(!b){
            TFIDc.setText("Id non esistente");
        }
        return b;
    }

    @FXML
    void mail(MouseEvent mouaeEvent){
        String accesso= null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LClient.setText(accesso);

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String idcart=null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(Search_ALL_QUERY);

            preparedStatement.setString(1, LClient.getText());
            preparedStatement.execute();
            CarrelloDAO.getInstance().load();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                idcart = rs.getString("IdCarrello");
                TFIDc.setText(idcart);
            }
            rs.close();
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

    }
    @FXML
   void idC(MouseEvent mouseEvent){


        String accesso= null;
        singin = SingInDAO.getInstance().getSingIn();
        for (SingIn s : singin){
            accesso= s.getEmail();
        }
        LClient.setText(accesso);
        String idCart= null;

        singin = SingInDAO.getInstance().getSingIn();
        clienti = ClienteDAO.getInstance().getCliente();
        for (Cliente c : clienti){
            if(c.getEmail().equals(LClient.getText())){
                idCart= LCart.getText();

            }
            TFIDc.setText(idCart);
        }
    }

    public boolean updateP() {

        int quantita=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for(Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(TextPordotto.getText())){

                quantita=p.getQuantita();
            }
        }

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_P);
            //ps.setString(1, TFQuantitaUp);
            ps.setInt(1,quantita-Integer.parseInt(Tquantita.getText()));

            ps.setString(2, TextPordotto.getText());

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

        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(TextPordotto.getText())){
                Quantita=p.getQuantita();

            }
        }
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_AC);
            ps.setInt(1, Quantita);

            ps.setString(2, TextPordotto.getText());
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

        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(TextPordotto.getText())){
                Quantita=p.getQuantita();

            }
        }
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_L);
            ps.setInt(1, Quantita);

            ps.setString(2, TextPordotto.getText());
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

    CartControl cart = new CartControl();


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ViewCarrello != null : "fx:id=\"ViewCarrello\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert MostraLibri != null : "fx:id=\"MostraProdotto\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert Lidpro != null : "fx:id=\"Lidpro\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert LClient != null : "fx:id=\"LClient\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert ListaLibri != null : "fx:id=\"ListaLibri\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert AddCarello != null : "fx:id=\"AddCarello\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert MostraArticoli != null : "fx:id=\"MostraProdotto1\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert TextPordotto != null : "fx:id=\"TextPordotto\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert ListaArticoli != null : "fx:id=\"ListaArticoli\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert Ilib != null : "fx:id=\"Ilib\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert Icart != null : "fx:id=\"Icart\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert IArtc != null : "fx:id=\"IArtc\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert screenB != null : "fx:id=\"screenB\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert Tquantita != null : "fx:id=\"Tquantita\" was not injected: check your FXML file 'HomepageCliente.fxml'.";
        assert Lquantita != null : "fx:id=\"Lquantita\" was not injected: check your FXML file 'HomepageCliente.fxml'.";

    }
    private static final String CREATE_QUERY ="INSERT INTO carrelli VALUES(?,?,?,?)";
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/persistenza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
    public static final String USER="root";
    public static final String PASS="";
    private static final String READ_ALL_QUERY = "SELECT * FROM libri";
    private static final String READ_ALL_QUERY_A = "SELECT * FROM articoli";
    private static final String READ_ALL_QUERY_P = "SELECT * FROM prodotti";
    private static final String CREATE_QUERY_P ="INSERT INTO prodotti VALUES(?,?,?,?)";
    private static final String CREATE_QUERY_C ="INSERT INTO prodotti_carrello VALUES(?,?,?,?,?,?)";
    private static final String READ_ALL_QUERY_C = "SELECT * FROM carrelli";
    private static final String READ_ALL_QUERY_Client = "SELECT * FROM clienti";
    private static LinkedList<Prodotti_Carrello> carrelli;
    private LinkedList<Libro> libri;
    private LinkedList<articoloC> articoli;
    private LinkedList<Cliente> clienti;
    private LinkedList<Prodotto> prodotti;
    private LinkedList<SingIn> singin;
    private static final String UPDATE_QUERY_L = "UPDATE libri SET Quantità=(?) WHERE idProdotto=(?) ";
    private static final String UPDATE_QUERY_AC = "UPDATE articoli SET Quantità=(?) WHERE idProdotto=(?) ";
    private static final String UPDATE_QUERY_P = "UPDATE prodotti SET Quantità=(?) WHERE idProdotto=(?) ";
    private static final String UPDATE_QUERY = "UPDATE prodotti_carrello SET Quantità=(?), Prezzo_Totale=(?) WHERE Prodotto=(?) and Cliente=(?)";
    private static final String Search_ALL_QUERY = "SELECT IdCarrello FROM carrelli WHERE Cliente=(?) ";
    private static final String Search_ALL_QUERY_P = "SELECT Prodotto FROM prodotti_carrello WHERE IdC=(?) ";
    private static final String Search_ALL_QUERY_Price = "SELECT Quantità, Prezzo_unitario, Prezzo_Totale  FROM prodotti_carrello WHERE IdC=(?) and Prodotto=(?) ";

    public void Alphabetic(javafx.scene.input.KeyEvent keyEvent) {
        Tquantita.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") ) {

                Tquantita.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
