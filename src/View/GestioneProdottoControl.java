package View;


/**
 * Sample Skeleton for 'GestioneProdotto.fxml' Controller Class
 */

import java.io.IOException;
import java.io.Serializable;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import Control.*;
import Model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import static java.lang.Double.parseDouble;

public class GestioneProdottoControl {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="screen"
    private ImageView screen; // Value injected by FXMLLoader

    @FXML // fx:id="blu13"
    private Rectangle blu13; // Value injected by FXMLLoader


    @FXML // fx:id="blu6"
    private Rectangle blu6; // Value injected by FXMLLoader

    @FXML // fx:id="blu3"
    private Rectangle blu3; // Value injected by FXMLLoader

    @FXML // fx:id="blu1"
    private Rectangle blu1; // Value injected by FXMLLoader

    @FXML // fx:id="LIDL"
    private Label LIDL; // Value injected by FXMLLoader

    @FXML // fx:id="T"
    private Label T; // Value injected by FXMLLoader

    @FXML // fx:id="A"
    private Label A; // Value injected by FXMLLoader

    @FXML // fx:id="CE"
    private Label CE; // Value injected by FXMLLoader

    @FXML // fx:id="CISBN"
    private Label CISBN; // Value injected by FXMLLoader

    @FXML // fx:id="LGP"
    private Label LGP; // Value injected by FXMLLoader

    @FXML // fx:id="TIpo"
    private Label TIpo; // Value injected by FXMLLoader

    @FXML // fx:id="IDPAc"
    private Label IDPAc; // Value injected by FXMLLoader

    @FXML // fx:id="TFCISBN"
    private TextField TFCISBN; // Value injected by FXMLLoader

    @FXML // fx:id="TFIDL"
    private TextField TFIDL; // Value injected by FXMLLoader

    @FXML // fx:id="TFAutore"
    private TextField TFAutore; // Value injected by FXMLLoader

    @FXML // fx:id="TFTitolo"
    private TextField TFTitolo; // Value injected by FXMLLoader

    @FXML // fx:id="TFCasaE"
    private TextField TFCasaE; // Value injected by FXMLLoader

    @FXML // fx:id="TFIDAc"
    private TextField TFIDAc; // Value injected by FXMLLoader

    @FXML // fx:id="TFTipo"
    private TextField TFTipo; // Value injected by FXMLLoader

    @FXML // fx:id="BLibro"
    private Button BLibro; // Value injected by FXMLLoader

    @FXML // fx:id="BArtC"
    private Button BArtC; // Value injected by FXMLLoader

    @FXML // fx:id="TFPrezzoUp"
    private TextField TFPrezzoUp; // Value injected by FXMLLoader

    @FXML // fx:id="TFQuantitaUp"
    private TextField TFQuantitaUp; // Value injected by FXMLLoader

    @FXML // fx:id="TFupdateID"
    private TextField TFupdateID; // Value injected by FXMLLoader

    @FXML // fx:id="BUpdateP"
    private Button BUpdateP; // Value injected by FXMLLoader

    @FXML // fx:id="Q3"
    private Label Q3; // Value injected by FXMLLoader

    @FXML // fx:id="ID3"
    private Label ID3; // Value injected by FXMLLoader

    @FXML // fx:id="P3"
    private Label P3; // Value injected by FXMLLoader

    @FXML // fx:id="ListP"
    private ListView<String> ListP; // Value injected by FXMLLoader

    @FXML // fx:id="BaddP"
    private Button BaddP; // Value injected by FXMLLoader

    @FXML // fx:id="BLibromod"
    private Button BLibromod; // Value injected by FXMLLoader

    @FXML // fx:id="BACup"
    private Button BACup; // Value injected by FXMLLoader

    @FXML // fx:id="Lmod"
    private Label Lmod; // Value injected by FXMLLoader

    @FXML // fx:id="Blista"
    private Button Blista; // Value injected by FXMLLoader

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

    public void loadL() {
        LinkedList<Libro> libri = new LinkedList<Libro>();
        ProprietarioDAO proprietarioDAO = ProprietarioDAO.getInstance();
        Libro l= null;
        Proprietario p = null;
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(READ_ALL_QUERY_L);
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

    public boolean addL() {
        String email="mancinelli@gmail.com";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        int Quantita=0;
        double prezzo=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(TFIDL.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }


                try {
                    conn = createConnection();
                    preparedStatement = conn.prepareStatement(CREATE_QUERY_L);
                    preparedStatement.setString(1, TFIDL.getText());
                    preparedStatement.setDouble(2, prezzo);
                    preparedStatement.setInt(3, Quantita);
                    preparedStatement.setString(4,email);
                    preparedStatement.setString(5, TFTitolo.getText());
                    preparedStatement.setString(6, TFAutore.getText());
                    preparedStatement.setString(7, TFCasaE.getText());
                    preparedStatement.setString(8, TFCISBN.getText());
                    preparedStatement.execute();
                    LibroDAO.getInstance().load();
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






    public boolean addAC() {
        String email="mancinelli@gmail.com";
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        int Quantita=0;
        double prezzo=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(TFIDAc.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY_A);
            preparedStatement.setString(1, TFIDAc.getText());
            preparedStatement.setDouble(2,prezzo);
            preparedStatement.setInt(3, Quantita);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5, TFTipo.getText());
            preparedStatement.execute();
            ArticoliCDAO.getInstance().load();
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



    public boolean addP() throws ParseException {
        double prezzot=Double.parseDouble(TFPrezzoUp.getText());
        String email="mancinelli@gmail.com";
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        double number2 = (int)(Math.round(prezzot * 100))/100.0;

        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY_P);
            preparedStatement.setString(1, TFupdateID.getText());
            preparedStatement.setDouble(2, number2);
            preparedStatement.setInt(3, Integer.parseInt(TFQuantitaUp.getText()));
            preparedStatement.setString(4,email);
            preparedStatement.execute();
            ProdottoDAO.getInstance().load();
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

    public boolean updateP() {
        String email="mancinelli@gmail.com";
        Connection conn = null;
        PreparedStatement ps = null;
        double prezzot=Double.parseDouble(TFPrezzoUp.getText());
        double number2 = (int)(Math.round(prezzot * 100))/100.0;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_P);
            //ps.setString(1, TFQuantitaUp);
            ps.setInt(1, Integer.parseInt(TFQuantitaUp.getText()));
            ps.setDouble(2, number2);
            ps.setString(3, TFupdateID.getText());

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

    public boolean updateL() {
        Connection conn = null;
        PreparedStatement ps = null;
        int Quantita=0;
        double prezzo=0;
        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(TFupdateID.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }

        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_L);
            ps.setInt(1, Quantita);
            ps.setDouble(2, prezzo);
            ps.setString(3, TFupdateID.getText());
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

    @FXML
    public boolean AddLibro(ActionEvent event) {

        if (TFIDL.getText().equals("") || TFIDL.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        if (TFCasaE.getText().equals("") || TFCasaE.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        if (TFAutore.getText().equals("") || TFAutore.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        if (TFTitolo.getText().equals("") || TFTitolo.getText().length()>500)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        if (TFCISBN.getText().equals("") || TFCISBN.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti) {
            if (p.getId().equalsIgnoreCase(TFIDL.getText())) {
                libri = LibroDAO.getInstance().getLibro();
                for (Libro l : libri) {
                    if (l.getId().equalsIgnoreCase(TFIDL.getText()) || l.getIsbn().equalsIgnoreCase(TFCISBN.getText())) {
                        try {
                            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }
                try {
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/OperazioneRiuscita.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean b = addL();
                return b;
            }




        }
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    public boolean AddArticoloC(ActionEvent event) {

        if (TFIDAc.getText().equals("") || TFIDAc.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        if (TFTipo.getText().equals("") || TFTipo.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        prodotti = ProdottoDAO.getInstance().getProdotto();
        for ( Prodotto p : prodotti) {
            if (p.getId().equalsIgnoreCase(TFIDAc.getText())) {
                articoli = ArticoliCDAO.getInstance().getArticolo();
                for (articoloC a : articoli) {
                    if (a.getId().equalsIgnoreCase(TFIDAc.getText())) {
                        try {
                            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }
                }
                try {
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/OperazioneRiuscita.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean b = addAC();
                return b;
            }




        }
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
            if(p.getId().equalsIgnoreCase(TFupdateID.getText())){
                Quantita=p.getQuantita();
                prezzo=p.getPrezzo();
            }
        }
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_AC);
            ps.setInt(1, Quantita);
            ps.setDouble(2, prezzo);
            ps.setString(3, TFupdateID.getText());
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

    @FXML
    public boolean UpdateProdotti(ActionEvent event) throws ParseException {

        if (TFupdateID.getText().equals("") || TFupdateID.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        if (TFQuantitaUp.getText().equals("") || Integer.parseInt(TFQuantitaUp.getText())>500)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }


        if(TFPrezzoUp.getText().contains("..")){
            TFPrezzoUp.setText("00.00");
        }
        if(TFPrezzoUp.getText().equals(".")){
            TFPrezzoUp.setText("00.00");
        }
        int j=0;
        String s= TFPrezzoUp.getText();
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals(".")){
                j=j+1;
                i++;
            }

        }if(j>1){
            TFPrezzoUp.setText("00.00");
            j=0;
        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            char l= s.charAt(0);
            String str= String.valueOf(l);
            if(str.equals(".")){
                TFPrezzoUp.setText("00.00");
                i++;
            }

        }
        if(TFPrezzoUp.getText().equals("")){
            TFPrezzoUp.setText("00.00");
        }




        if (parseDouble(TFPrezzoUp.getText())>500 || TFPrezzoUp.getText().equals("00.00"))  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
        }


        boolean b = updateP();
        updateL();
        updateArtC();
        List<Prodotto> prodotti = ProdottoDAO.getInstance().getProdotto();
        Iterator<Prodotto> itp = prodotti.iterator();
        Iterator<Prodotto> itq = prodotti.iterator();
        Iterator<Prodotto> itpre = prodotti.iterator();
        ObservableList<String> prod = null;
        ObservableList<Integer> q = null;
        ObservableList<Double> pre = null;
        ListP.getItems().clear();

        while(itp.hasNext() && itq.hasNext() && itpre.hasNext()){
            prod = FXCollections.observableArrayList(itp.next().getId());
            q= FXCollections.observableArrayList(itq.next().getQuantita());
            pre= FXCollections.observableArrayList(itpre.next().getPrezzo());

            ListP.getItems().addAll("Id Prodotto: "+prod+ " Quantità: "+ String.valueOf(q)+" Prezzo: "+ String.valueOf(pre) );


        }
        return b;

    }

    @FXML
   public boolean AddProdotti(ActionEvent event) throws ParseException {

        if (TFupdateID.getText().equals("") || TFupdateID.getText().length()>50)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        if (TFQuantitaUp.getText().equals("") || Integer.parseInt(TFQuantitaUp.getText())>500)  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }


        if(TFPrezzoUp.getText().contains("..")){
            TFPrezzoUp.setText("00.00");
        }
        if(TFPrezzoUp.getText().equals(".")){
            TFPrezzoUp.setText("00.00");
        }
        int j=0;
        String s= TFPrezzoUp.getText();
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals(".")){
                j=j+1;
                i++;
            }

        }if(j>1){
            TFPrezzoUp.setText("00.00");
            j=0;
        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            char l= s.charAt(0);
            String str= String.valueOf(l);
            if(str.equals(".")){
                TFPrezzoUp.setText("00.00");
                i++;
            }

        }

        if(TFPrezzoUp.getText().equals("")){
            TFPrezzoUp.setText("00.00");
        }



        if (parseDouble(TFPrezzoUp.getText())>500 || TFPrezzoUp.getText().equals("00.00"))  {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        prodotti= ProdottoDAO.getInstance().getProdotto();
        for(Prodotto p : prodotti){
            if(p.getId().equalsIgnoreCase(TFupdateID.getText())){
                try {
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return false;
            }
        }
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/OperazioneRiuscita.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean b= addP();

        List<Prodotto> prodotti = ProdottoDAO.getInstance().getProdotto();
        Iterator<Prodotto> itp = prodotti.iterator();
        Iterator<Prodotto> itq = prodotti.iterator();
        Iterator<Prodotto> itpre = prodotti.iterator();
        ObservableList<String> prod = null;
        ObservableList<Integer> q = null;
        ObservableList<Double> pre = null;
        ListP.getItems().clear();

        while(itp.hasNext() && itq.hasNext() && itpre.hasNext()){
            prod = FXCollections.observableArrayList(itp.next().getId());
            q= FXCollections.observableArrayList(itq.next().getQuantita());
            pre= FXCollections.observableArrayList(itpre.next().getPrezzo());

            ListP.getItems().addAll("Id Prodotto: "+prod+ " Quantità: "+ String.valueOf(q)+" Prezzo: "+ String.valueOf(pre) );


        }
        return b;
    }



    @FXML
    void ShowList(ActionEvent event) {

        List<Prodotto> prodotti = ProdottoDAO.getInstance().getProdotto();
        Iterator<Prodotto> itp = prodotti.iterator();
        Iterator<Prodotto> itq = prodotti.iterator();
        Iterator<Prodotto> itpre = prodotti.iterator();
        ObservableList<String> prod = null;
        ObservableList<Integer> q = null;
        ObservableList<Double> pre = null;
        ListP.getItems().clear();

        while(itp.hasNext() && itq.hasNext() && itpre.hasNext()){
            prod = FXCollections.observableArrayList(itp.next().getId());
            q= FXCollections.observableArrayList(itq.next().getQuantita());
            pre= FXCollections.observableArrayList(itpre.next().getPrezzo());

            ListP.getItems().addAll("Id Prodotto: "+prod+ " Quantità: "+ String.valueOf(q)+" Prezzo: "+ String.valueOf(pre) );


        }


    }
    public void AlphabeticA(javafx.scene.input.KeyEvent keyEvent) {
        TFAutore.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*") ) {

                TFAutore.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });
    }

    public void AlphabeticP(javafx.scene.input.KeyEvent keyEvent)  {

        TFPrezzoUp.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") ) {

                TFPrezzoUp.setText(newValue.replaceAll("[^\\d | [\\.]]", ""));

            }
        });
    }

    public void AlphabeticQ(javafx.scene.input.KeyEvent keyEvent) {
        TFQuantitaUp.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") ) {

                TFQuantitaUp.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert screen != null : "fx:id=\"screen\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert blu13 != null : "fx:id=\"blu13\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";

        assert blu6 != null : "fx:id=\"blu6\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";

        assert LIDL != null : "fx:id=\"LIDL\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert T != null : "fx:id=\"T\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert A != null : "fx:id=\"A\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert CE != null : "fx:id=\"CE\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert CISBN != null : "fx:id=\"CISBN\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert LGP != null : "fx:id=\"LGP\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TIpo != null : "fx:id=\"TIpo\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert IDPAc != null : "fx:id=\"IDPAc\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFCISBN != null : "fx:id=\"TFCISBN\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFIDL != null : "fx:id=\"TFIDL\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFAutore != null : "fx:id=\"TFAutore\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFTitolo != null : "fx:id=\"TFTitolo\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFCasaE != null : "fx:id=\"TFCasaE\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFIDAc != null : "fx:id=\"TFIDAc\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFTipo != null : "fx:id=\"TFTipo\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert BLibro != null : "fx:id=\"BLibro\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert BArtC != null : "fx:id=\"BArtC\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFPrezzoUp != null : "fx:id=\"TFPrezzoUp\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFQuantitaUp != null : "fx:id=\"TFQuantitaUp\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert TFupdateID != null : "fx:id=\"TFupdateID\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert BUpdateP != null : "fx:id=\"BUpdateP\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert Q3 != null : "fx:id=\"Q3\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert ID3 != null : "fx:id=\"ID3\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert P3 != null : "fx:id=\"P3\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert ListP != null : "fx:id=\"ListP\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert BaddP != null : "fx:id=\"BaddP\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";


        assert Lmod != null : "fx:id=\"Lmod\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";
        assert Blista != null : "fx:id=\"Blista\" was not injected: check your FXML file 'GestioneProdotto.fxml'.";

    }
    private static final String CREATE_QUERY_L ="INSERT INTO libri VALUES(?,?,?,?,?,?,?,?)";
    private static final String READ_ALL_QUERY_L = "SELECT * FROM libri";
    private LinkedList<Libro> libri;
    private LinkedList<articoloC> articoli;
    private static final String CREATE_QUERY_A ="INSERT INTO articoli VALUES(?,?,?,?,?)";
    private static final String READ_ALL_QUERY_A = "SELECT * FROM articoli";
    private LinkedList<Prodotto> prodotti;
    private static final String CREATE_QUERY_P ="INSERT INTO prodotti VALUES(?,?,?,?)";
    private static final String READ_ALL_QUERY_P = "SELECT * FROM prodotti";
    private static final String UPDATE_QUERY_P = "UPDATE prodotti SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private static final String UPDATE_QUERY_L = "UPDATE libri SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";
    private static final String UPDATE_QUERY_AC = "UPDATE articoli SET Quantità=(?), Prezzo=(?) WHERE idProdotto=(?) ";





}

