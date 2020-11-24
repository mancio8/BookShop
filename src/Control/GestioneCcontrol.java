package Control; /**
 * Sample Skeleton for 'GestioneClienti.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import DAO.CarrelloDAO;
import DAO.CartaFedDAO;
import DAO.ClienteDAO;
import DAO.UtenteDAO;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;

public class GestioneCcontrol {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TextField TFcodcf;

    @FXML
    private TextField TFidC;

    @FXML
    private Label lccf;


    @FXML
    private Label Lidc;

    @FXML // fx:id="C"
    private Label C; // Value injected by FXMLLoader

    @FXML // fx:id="E"
    private Label E; // Value injected by FXMLLoader

    @FXML // fx:id="Addcarrello"
    private Button Addcarrello; // Value injected by FXMLLoader

    @FXML // fx:id="E2"
    private Label E2; // Value injected by FXMLLoader

    @FXML // fx:id="N"
    private Label N; // Value injected by FXMLLoader

    @FXML // fx:id="E3"
    private Label E3; // Value injected by FXMLLoader

    @FXML // fx:id="TFEmailCf"
    private TextField TFEmailCf; // Value injected by FXMLLoader

    @FXML // fx:id="P"
    private Label P; // Value injected by FXMLLoader

    @FXML // fx:id="TFCognome"
    private TextField TFCognome ; // Value injected by FXMLLoader

    @FXML // fx:id="TFEmailCliente"
    private TextField TFEmailCliente; // Value injected by FXMLLoader



    @FXML // fx:id="AddCarta"
    private Button AddCarta; // Value injected by FXMLLoader

    @FXML // fx:id="EC"
    private Label EC; // Value injected by FXMLLoader

    @FXML // fx:id="UpdateCF"
    private Button UpdateCF; // Value injected by FXMLLoader

    @FXML // fx:id="TFEmail1"
    private TextField TFEmail1; // Value injected by FXMLLoader

    @FXML // fx:id="Pu"
    private Label Pu; // Value injected by FXMLLoader

    @FXML // fx:id="LGC"
    private Label LGC; // Value injected by FXMLLoader

    @FXML // fx:id="TFid"
    private TextField TFid; // Value injected by FXMLLoader

    @FXML // fx:id="TFData"
    private TextField TFData; // Value injected by FXMLLoader

    @FXML // fx:id="Ds"
    private Label Ds; // Value injected by FXMLLoader

    @FXML // fx:id="TFPunti"
    private TextField TFPunti; // Value injected by FXMLLoader

    @FXML // fx:id="TFPass"
    private TextField TFPass; // Value injected by FXMLLoader

    @FXML // fx:id="TFEmail"
    private TextField TFEmail; // Value injected by FXMLLoader

    @FXML // fx:id="LabelCarrello"
    private Label LabelCarrello; // Value injected by FXMLLoader

    @FXML // fx:id="AddUtente"
    private Button AddUtente; // Value injected by FXMLLoader

    @FXML // fx:id="Id"
    private Label Id; // Value injected by FXMLLoader

    @FXML // fx:id="TFIdCarrello"
    private TextField TFIdCarrello; // Value injected by FXMLLoader

    @FXML // fx:id="TFNome"
    private TextField TFNome; // Value injected by FXMLLoader

    @FXML // fx:id="AddC"
    private Button AddC; // Value injected by FXMLLoader

    @FXML // fx:id="Sep"
    private Separator Sep; // Value injected by FXMLLoader

    @FXML
    private ListView<String> List;

    @FXML // fx:id="screenblue"
    private ImageView screenblue; // Value injected by FXMLLoader

    @FXML // fx:id="Bmenu"
    private MenuButton Bmenu; // Value injected by FXMLLoader

    @FXML // fx:id="ListU"
    private MenuItem ListU; // Value injected by FXMLLoader

    @FXML // fx:id="ListCf"
    private MenuItem ListCf; // Value injected by FXMLLoader

    @FXML // fx:id="ListClient"
    private MenuItem ListClient; // Value injected by FXMLLoader
    private Label Ds1;


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


    public boolean addCartaF() {

        Connection conn = null;


        PreparedStatement ps = null;

        try {
            conn = createConnection();
            ps = conn.prepareStatement(CREATE_QUERY_CF);

            ps.setString(1, TFid.getText());
            ps.setDate(2, Date.valueOf(TFData.getText()));
            ps.setInt(3, Integer.parseInt(TFPunti.getText()));
            ps.setString(4, TFEmailCf.getText());

            ps.executeUpdate();


            CartaFedDAO.getInstance().load();
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




    public boolean addU() {

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {

            conn = createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY_U);
            preparedStatement.setString(1, TFNome.getText());
            preparedStatement.setString(2, TFCognome.getText());
            preparedStatement.setString(3, TFEmail.getText());
            preparedStatement.setString(4, TFPass.getText());
            preparedStatement.executeUpdate();

            UtenteDAO.getInstance().load();
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


    }

    public boolean AddClient(){
        Connection conn2 = null;
        PreparedStatement preparedStatement = null;
        try {
            conn2 = createConnection();
            preparedStatement = conn2.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, TFNome.getText());
            preparedStatement.setString(2, TFCognome.getText());
            preparedStatement.setString(3, TFEmail.getText());
            preparedStatement.setString(4, TFPass.getText());
            preparedStatement.setString(5, null);
            preparedStatement.setString(6, null);
            preparedStatement.setString(7, null);
            preparedStatement.execute();
            ClienteDAO.getInstance().load();
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
                conn2.close();
            } catch(Exception cse) {
                cse.printStackTrace();
            }
        }
        return false;
    }



    @FXML
    public boolean AddUtente(ActionEvent event) {


        utenti = UtenteDAO.getInstance().getUtente();

        if (TFEmail.getText().equals("") || TFEmail.getText().length()>100)  {
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

        if (TFNome.getText().equals("") || TFNome.getText().length()>50)  {
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

        if (TFCognome.getText().equals("") || TFCognome.getText().length()>50)  {
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

        if (TFPass.getText().equals("") || TFPass.getText().length()>50)  {
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

        for (Utente u : utenti) {
            if (u.getEmail().equals(TFEmail.getText()) ) {
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
        boolean b = addU();
        boolean client = AddClient();

        utenti = UtenteDAO.getInstance().getUtente();
        Iterator<Utente> it = utenti.iterator();
        ObservableList<String> user = null;

        List.getItems().clear();

        while (it.hasNext()) {

            user = FXCollections.observableArrayList(it.next().getEmail());
            List.getItems().addAll(user);

        }
        return b;



    }


    @FXML
    public boolean AddCF(ActionEvent event) throws ParseException {
        boolean d = Ceckdata();

        int mail=0;
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String email=null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(Search_ALL_QUERY_CF);

            preparedStatement.setString(1, TFEmailCf.getText());
            preparedStatement.execute();
            CartaFedDAO.getInstance().load();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                email = rs.getString("Email");
                if(email.equals(TFEmailCf.getText())){
                    mail=1;
                }
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


        if (TFEmailCf.getText().equals("") || TFEmailCf.getText().length() > 100) {
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

        if (TFPunti.getText().equals("") || Integer.parseInt(TFPunti.getText()) > 500) {
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
       if(TFData.getText().equals("")){
            TFData.setText("2000-01-01");
        }
        if(TFData.getText().equalsIgnoreCase("yyyy-MM-dd")){
            TFData.setText("2000-01-01");
        }

        if(TFData.getText().length()<10){
            TFData.setText("2000-01-01");
        }
        if(TFData.getText().equals("----------")){
            TFData.setText("2000-01-01");
        }
        if(TFData.getText().length()>10){
            TFData.setText("2000-01-01");
        }

        int j=0;
        String s= TFData.getText();
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("-")){
                j=j+1;
                i++;
            }

        }if(j>2){
            TFData.setText("2000-01-01");
            j=0;
        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("[^\\sa-zA-Z]")){
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("_")){
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("/")){
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(4);
            String str= String.valueOf(lettera);
            if(str.equals("-")){

                i++;
            }else{
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(7);
            String str= String.valueOf(lettera);
            if(str.equals("-")){

                i++;
            }else{
                TFData.setText("2000-01-01");
                i++;
            }

        }


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TFData.setTextFormatter(new TextFormatter<>(new DateStringConverter(format), format.parse(TFData.getText())));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        java.util.Date today = calendar.getTime();

        Date data = Date.valueOf(TFData.getText());





            if (TFData.getText().equals("") || data.before(today)|| !d ) {
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




        if (TFid.getText().equals("") || TFid.getText().length()>50)  {
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



        utenti = UtenteDAO.getInstance().getUtente();
        for ( Utente u : utenti) {
            if (u.getEmail().equals(TFEmailCf.getText())) {
                carte = CartaFedDAO.getInstance().getCarta();
                for (CartaFed c : carte) {
                    if (c.getCodice().equals(TFid.getText()) || mail==1  ) {
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
                boolean b = addCartaF();
                try {
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/OperazioneRiuscita.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                carte = CartaFedDAO.getInstance().getCarta();


                Iterator<CartaFed> itc = carte.iterator();
                ObservableList<String> idcf = null;

                List.getItems().clear();


                while (itc.hasNext()) {
                    idcf = FXCollections.observableArrayList(itc.next().getCodice());

                    List.getItems().addAll("Codice Fedeltà");

                    List.getItems().addAll(idcf);



                }





                PreparedStatement ps = null;
                try {
                    conn = createConnection();
                    ps = conn.prepareStatement(UPDATE_QUERY_CaFe);
                    ps.setString(1,  TFid.getText());
                    ps.setString(2, TFEmailCf.getText());

                    ps.execute();
                    ClienteDAO.getInstance().load();
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


            }


        return false;
    }


    public boolean addCart() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY_CART);
            preparedStatement.setString(1, TFidC.getText());
            preparedStatement.setString(2, TFEmailCliente.getText());
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


    @FXML
    public boolean UpdateCF(ActionEvent event) throws ParseException {
        boolean b= Ceckdata();

        if (TFPunti.getText().equals("") || Integer.parseInt(TFPunti.getText())>500)  {
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

        if (TFid.getText().equals("") || TFid.getText().length()>50)  {
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
        if(TFData.getText().equals("")){
            TFData.setText("2000-01-01");
        }








        if(TFData.getText().length()<10){
            TFData.setText("2000-01-01");
        }
        if(TFData.getText().equals("----------")){
            TFData.setText("2000-01-01");
        }
        if(TFData.getText().length()>10){
            TFData.setText("2000-01-01");
        }
        int j=0;
        String s= TFData.getText();
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("-")){
                j=j+1;
                i++;
            }

        }if(j>2){
            TFData.setText("2000-01-01");
            j=0;
        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("[^\\sa-zA-Z]")){
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("_")){
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(i);
            String str= String.valueOf(lettera);
            if(str.equals("/")){
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(4);
            String str= String.valueOf(lettera);
            if(str.equals("-")){

                i++;
            }else{
                TFData.setText("2000-01-01");
                i++;
            }

        }
        for(int i=0; i<s.length();i++){

            char lettera=s.charAt(7);
            String str= String.valueOf(lettera);
            if(str.equals("-")){

                i++;
            }else{
                TFData.setText("2000-01-01");
                i++;
            }

        }



        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        TFData.setTextFormatter(new TextFormatter<>(new DateStringConverter(format), format.parse(TFData.getText())));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"), Locale.ITALY);
        java.util.Date today = calendar.getTime();

        Date data = Date.valueOf(TFData.getText());


        if (TFData.getText().equals("") || data.before(today) || !b )  {
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

        Connection conn = null;
        PreparedStatement ps = null;
        carte = CartaFedDAO.getInstance().getCarta();
        for(CartaFed c : carte){
            if(c.getCodice().equalsIgnoreCase(TFid.getText())){
                try {
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/OperazioneRiuscita.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    conn = createConnection();
                    ps = conn.prepareStatement(UPDATE_QUERY_CF);
                    ps.setDate(1, Date.valueOf(TFData.getText()));
                    ps.setInt(2, Integer.parseInt(TFPunti.getText()));
                    ps.setString(3,TFid.getText());
                    ps.execute();
                    CartaFedDAO.getInstance().load();
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
        carte = CartaFedDAO.getInstance().getCarta();


        Iterator<CartaFed> itc = carte.iterator();
        ObservableList<String> idcf = null;

        List.getItems().clear();


        while (itc.hasNext()) {
            idcf = FXCollections.observableArrayList(itc.next().getCodice());

            List.getItems().addAll("Codice Fedeltà");

            List.getItems().addAll(idcf);



        }
        return false;

    }


    public boolean Ceckdata () throws ParseException {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            formatter.parse(TFData.getText());
            return true;
        }catch(Exception e){
            return false;
        }

    }

    public boolean updateCart(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = createConnection();
            ps = conn.prepareStatement(UPDATE_QUERY_CaartC);
            ps.setString(1,  TFidC.getText());
            ps.setString(2, TFEmailCf.getText());

            ps.execute();
            ClienteDAO.getInstance().load();
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
    public boolean AddCliente(ActionEvent event) {

        int mail=0;
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        String email=null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(Search_ALL_QUERY);

            preparedStatement.setString(1, TFEmailCliente.getText());
            preparedStatement.execute();
            CarrelloDAO.getInstance().load();

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                email = rs.getString("Cliente");
                if(email.equals(TFEmailCliente.getText())){
                    mail=1;
                }
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


        if (TFidC.getText().equals("") || TFidC.getText().length() > 50) {
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


        utenti=UtenteDAO.getInstance().getUtente();
        for(Utente u : utenti) {
            if(u.getEmail().equals(TFEmailCliente.getText())) {
                carrelli = CarrelloDAO.getInstance().getCarrello();

                for (Carrello c : carrelli) {

                    if (c.getIdC().equalsIgnoreCase(TFidC.getText()) || mail==1) {
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

                boolean ca = addCart();
                boolean ucart = updateCart();

                try {
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/OperazioneRiuscita.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                clienti = ClienteDAO.getInstance().getCliente();
                Iterator<Cliente> it = clienti.iterator();
                ObservableList<String> client = null;
                List.getItems().clear();

                while (it.hasNext()) {
                    client = FXCollections.observableArrayList(it.next().getEmail());
                    List.getItems().addAll(client);
                }

                return false;
            }
            }

           return  false;
        }



    @FXML


    public void Alphabetic(javafx.scene.input.KeyEvent keyEvent) {

        TFNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z+")) {

                TFNome.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });



    }

    @FXML
    void ShowListC(ActionEvent event) {
        clienti = ClienteDAO.getInstance().getCliente();
        Iterator<Cliente> it = clienti.iterator();
        ObservableList<String> client = null;
        List.getItems().clear();

        while(it.hasNext()){
            client = FXCollections.observableArrayList(it.next().getEmail());
            List.getItems().addAll(client);
        }
    }

    @FXML
    void showListU(ActionEvent event) {
        utenti = UtenteDAO.getInstance().getUtente();
        Iterator<Utente> it = utenti.iterator();
        ObservableList<String> user = null;

        List.getItems().clear();

        while (it.hasNext()) {

            user = FXCollections.observableArrayList(it.next().getEmail());
            List.getItems().addAll(user);

        }
    }

    @FXML
    void showListcf(ActionEvent event) {
        carte = CartaFedDAO.getInstance().getCarta();


        Iterator<CartaFed> itc = carte.iterator();
        ObservableList<String> idcf = null;

        List.getItems().clear();


        while (itc.hasNext()) {
            idcf = FXCollections.observableArrayList(itc.next().getCodice());

            List.getItems().addAll("Codice Fedeltà");

            List.getItems().addAll(idcf);



        }
    }



    public void AlphabeticC(javafx.scene.input.KeyEvent keyEvent) {
        TFCognome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z+") ) {

                TFCognome.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });

    }

    public void AlphabeticP(javafx.scene.input.KeyEvent keyEvent) {
        TFPunti.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") ) {

                TFPunti.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert C != null : "fx:id=\"C\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert E != null : "fx:id=\"E\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert Addcarrello != null : "fx:id=\"Addcarrello\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert E2 != null : "fx:id=\"E2\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert N != null : "fx:id=\"N\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert E3 != null : "fx:id=\"E3\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFEmailCf != null : "fx:id=\"TFEmailCf\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert P != null : "fx:id=\"P\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFCognome != null : "fx:id=\"TFCognome\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFEmailCliente != null : "fx:id=\"TFEmailCliente\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert AddCarta != null : "fx:id=\"AddCarta\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert EC != null : "fx:id=\"EC\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert UpdateCF != null : "fx:id=\"UpdateCF\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFEmail1 != null : "fx:id=\"TFEmail1\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert Pu != null : "fx:id=\"Pu\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert LGC != null : "fx:id=\"LGC\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFid != null : "fx:id=\"TFid\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFData != null : "fx:id=\"TFData\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert Ds != null : "fx:id=\"Ds\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFPunti != null : "fx:id=\"TFPunti\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFPass != null : "fx:id=\"TFPass\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFEmail != null : "fx:id=\"TFEmail\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert LabelCarrello != null : "fx:id=\"LabelCarrello\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert AddUtente != null : "fx:id=\"AddUtente\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert Id != null : "fx:id=\"Id\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFIdCarrello != null : "fx:id=\"TFIdCarrello\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert TFNome != null : "fx:id=\"TFNome\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert AddC != null : "fx:id=\"AddC\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert Sep != null : "fx:id=\"Sep\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert Bmenu != null : "fx:id=\"Bmenu\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert ListU != null : "fx:id=\"ListU\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert ListCf != null : "fx:id=\"ListCf\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert ListClient != null : "fx:id=\"ListClient\" was not injected: check your FXML file 'GestioneClienti.fxml'.";
        assert Ds1 != null : "fx:id=\"Ds\" was not injected: check your FXML file 'GestioneClienti.fxml'.";

    }
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/persistenza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
    public static final String USER="root";
    public static final String PASS="";
    private static final String READ_ALL_QUERY = "SELECT * FROM clienti";
    private static final String CREATE_QUERY ="INSERT INTO clienti VALUES(?,?,?,?,?,?,?)";
    private static final String CREATE_QUERY_U ="INSERT INTO utenti VALUES(?,?,?,?)";
    private static final String READ_ALL_QUERY_U = "SELECT * FROM utenti";
    private static final String READ_ALL_QUERY_CF = "SELECT * FROM carte";
    private static final String UPDATE_QUERY_CF = "UPDATE carte SET datas =(?) , punti =(?) WHERE IDc=(?)";
    private static final String CREATE_QUERY_CF ="INSERT INTO carte VALUES(?,?,?,?)";
    private static final String CREATE_QUERY_CART ="INSERT INTO carrelli VALUES(?,?)";
    private static final String UPDATE_QUERY_CaFe = "UPDATE clienti SET CartaFed =(?) WHERE email=(?)";
    private static final String UPDATE_QUERY_CaartC = "UPDATE clienti SET Id_Carrello =(?) WHERE email=(?)";
    private static final String Search_ALL_QUERY = "SELECT Cliente FROM carrelli WHERE Cliente=(?) ";
    private static final String Search_ALL_QUERY_CF = "SELECT Email FROM carte WHERE Email=(?) ";

    private LinkedList<Cliente> clienti;
    private LinkedList<Carrello> carrelli;
    private LinkedList<Utente> utenti;
    private static LinkedList<CartaFed> carte;
    Connection conn = null;






  /* public void newData(javafx.scene.input.KeyEvent keyEvent) {
        if(TFData.getText().equals("yyyy-MM-dd")) {
            TFData.setText("");
        }
        TFData.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d") ) {

                TFData.setText(newValue.replaceAll("[^\\d+-]", ""));
            }
        });
    }
*/

}
