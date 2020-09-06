package View;



/**
 * Sample Skeleton for 'GestioneDipendenti.fxml' Controller Class
 */

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.LinkedList;
import java.util.ResourceBundle;

import Control.DipendenteDAO;
import Control.RichiestaDAO;
import Control.UtenteDAO;
import Model.Dipendente;
import Model.RichiestaAcquisto;
import Model.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.control.Separator;
        import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class  GestioneDipendentiControl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="C"
    private Label C; // Value injected by FXMLLoader

    @FXML // fx:id="E"
    private Label E; // Value injected by FXMLLoader

    @FXML // fx:id="RemoveDip"
    private Button RemoveDip; // Value injected by FXMLLoader

    @FXML // fx:id="LGD"
    private Label LGD; // Value injected by FXMLLoader

    @FXML // fx:id="TFEmailR"
    private TextField TFEmailR; // Value injected by FXMLLoader

    @FXML // fx:id="E2"
    private Label E2; // Value injected by FXMLLoader

    @FXML // fx:id="N"
    private Label N; // Value injected by FXMLLoader

    @FXML // fx:id="P"
    private Label P; // Value injected by FXMLLoader

    @FXML // fx:id="TFCognome"
    private TextField TFCognome; // Value injected by FXMLLoader

    @FXML // fx:id="TFPass"
    private TextField TFPass; // Value injected by FXMLLoader

    @FXML // fx:id="TFEmail"
    private TextField TFEmail; // Value injected by FXMLLoader

    @FXML // fx:id="AddDipendente"
    private Button AddDipendente; // Value injected by FXMLLoader

    @FXML // fx:id="TFNome"
    private TextField TFNome; // Value injected by FXMLLoader

    @FXML // fx:id="Sep"
    private Separator Sep; // Value injected by FXMLLoader

    @FXML // fx:id="Screen"
    private ImageView Screen; // Value injected by FXMLLoader

    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String DBURL="jdbc:mysql://localhost:3306/persistenza?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
    public static final String USER="root";
    public static final String PASS="";

    public static Connection createConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(DBURL, USER, PASS);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return conn;
    }



    public boolean add() {
        Connection conn = null;

        PreparedStatement preparedStatement = null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY);
            preparedStatement.setString(1, TFNome.getText());
            preparedStatement.setString(2, TFCognome.getText());
            preparedStatement.setString(3, TFEmail.getText());
            preparedStatement.setString(4, TFPass.getText());
            preparedStatement.setString(5,null);
            preparedStatement.execute();

            DipendenteDAO.getInstance().load();

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

    public boolean RemoveDipendente(ActionEvent event) {

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

        dipendenti = DipendenteDAO.getInstance().getDipendenti();
        for(Dipendente dip:dipendenti){
            if(dip.getEmail().equals(TFEmailR.getText())){
                Connection conn = null;
                PreparedStatement preparedStatement = null;
                try {
                    conn = createConnection();
                    preparedStatement = conn.prepareStatement(DELETE_QUERY);


                    preparedStatement.setString(1, TFEmailR.getText());
                    preparedStatement.execute();
                    DipendenteDAO.getInstance().load();
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
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return false;
    }

    public boolean AddDipendente(ActionEvent event){

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



        utenti = UtenteDAO.getInstance().getUtente();
        for ( Utente u : utenti){
            if(u.getEmail().equals(TFEmail.getText())){
                try {
                    AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/OperazioneRiuscita.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean b= add();
                return b;

            }
        }
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errore.fxml"));
            Scene scene = new Scene(root);
            Stage stage= new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void Alphabetic(javafx.scene.input.KeyEvent keyEvent) {

        TFNome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*")) {

                TFNome.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));
            }
        });



    }

    public void AlphabeticC(javafx.scene.input.KeyEvent keyEvent)  {
        TFCognome.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\sa-zA-Z*") ) {


                TFCognome.setText(newValue.replaceAll("[^\\sa-zA-Z]" , ""));
            }
        });
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert C != null : "fx:id=\"C\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert E != null : "fx:id=\"E\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert RemoveDip != null : "fx:id=\"RemoveDip\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert LGD != null : "fx:id=\"LGD\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert TFEmailR != null : "fx:id=\"TFEmailR\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert E2 != null : "fx:id=\"E2\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert N != null : "fx:id=\"N\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert P != null : "fx:id=\"P\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert TFCognome != null : "fx:id=\"TFCognome\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert TFPass != null : "fx:id=\"TFPass\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert TFEmail != null : "fx:id=\"TFEmail\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert AddDipendente != null : "fx:id=\"AddDipendente\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert TFNome != null : "fx:id=\"TFNome\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert Sep != null : "fx:id=\"Sep\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";
        assert Screen != null : "fx:id=\"Screen\" was not injected: check your FXML file 'GestioneDipendenti.fxml'.";

    }
    private static final String CREATE_QUERY ="INSERT INTO dipendenti VALUES(?,?,?,?,?)";
    private static final String READ_ALL_QUERY = "SELECT * FROM dipendenti";
    private static final String DELETE_QUERY= "DELETE FROM dipendenti WHERE Email = (?)";
    private LinkedList<Dipendente> dipendenti;
    private LinkedList<Utente> utenti;
}
