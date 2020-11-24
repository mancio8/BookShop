/**
 * Sample Skeleton for 'Welcome.fxml' Controller Class
 */
package Control;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Control.GestoreUtenti;
import DAO.SingInDAO;
import Model.Utente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class WelcomeControl {


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="bluescreen"
    private ImageView bluescreen; // Value injected by FXMLLoader

    @FXML // fx:id="accedi"
    private Button accedi; // Value injected by FXMLLoader

    @FXML // fx:id="welcome"
    private Label welcome; // Value injected by FXMLLoader

    @FXML // fx:id="labelmail"
    private TextField labelmail; // Value injected by FXMLLoader

    @FXML // fx:id="mail"
    private Label mail; // Value injected by FXMLLoader

    @FXML // fx:id="pass"
    private Label pass; // Value injected by FXMLLoader

    @FXML // fx:id="PassField"
    private PasswordField PassField; // Value injected by FXMLLoader

    @FXML // fx:id="Unisannio"
    private ImageView Unisannio; // Value injected by FXMLLoader

    @FXML // fx:id="Iinstagram"
    private ImageView Iinstagram; // Value injected by FXMLLoader

    @FXML // fx:id="Iwhatsapp"
    private ImageView Iwhatsapp; // Value injected by FXMLLoader

    @FXML // fx:id="numb"
    private Label numb; // Value injected by FXMLLoader

    @FXML // fx:id="intagram"
    private Label intagram; // Value injected by FXMLLoader

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

    public boolean add() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = createConnection();
            preparedStatement = conn.prepareStatement(CREATE_QUERY);

            preparedStatement.setString(1, labelmail.getText());

            preparedStatement.executeUpdate();
            SingInDAO.getInstance().load();
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
    void login(ActionEvent event ) {
        Utente u = new Utente(null, null, labelmail.getText(), PassField.getText());
        //if (GestoreUtenti.getInstance().login(u)) {
        if (GestoreUtenti.getInstance().login(u) && GestoreUtenti.getInstance().isProprietario(u) && u.getEmail().length()<100 && u.getPassword().length()<50) {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/AdminHomepage.fxml"));
                Scene scene = new Scene(root);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (GestoreUtenti.getInstance().login(u) && GestoreUtenti.getInstance().isDipendente(u) && u.getEmail().length()<100 && u.getPassword().length()<50) {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/HomepageDip.fxml"));
                Scene scene = new Scene(root);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (GestoreUtenti.getInstance().login(u) && GestoreUtenti.getInstance().isCliente(u)&& u.getEmail().length()<100 && u.getPassword().length()<50) {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/HomepageCliente.fxml"));
                Scene scene = new Scene(root);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // }
        else {
            try {
                AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Errorelogin.fxml"));
                Scene scene = new Scene(root);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }




        }
    add();
    }











    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert bluescreen != null : "fx:id=\"bluescreen\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert accedi != null : "fx:id=\"accedi\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert welcome != null : "fx:id=\"welcome\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert labelmail != null : "fx:id=\"labelmail\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert mail != null : "fx:id=\"mail\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert PassField != null : "fx:id=\"PassField\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert Unisannio != null : "fx:id=\"Unisannio\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert Iinstagram != null : "fx:id=\"Iinstagram\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert Iwhatsapp != null : "fx:id=\"Iwhatsapp\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert numb != null : "fx:id=\"numb\" was not injected: check your FXML file 'Welcome.fxml'.";
        assert intagram != null : "fx:id=\"intagram\" was not injected: check your FXML file 'Welcome.fxml'.";

    }
    private static final String CREATE_QUERY ="INSERT INTO accesso VALUES(?)";
}
