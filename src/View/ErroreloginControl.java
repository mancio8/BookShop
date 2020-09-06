package View;

/**
 * Sample Skeleton for 'Errorelogin.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ErroreloginControl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;



    @FXML // fx:id="Screen"
    private ImageView Screen; // Value injected by FXMLLoader

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="Errore"
    private Label Errore; // Value injected by FXMLLoader

    @FXML // fx:id="Err"
    private ImageView Err; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert Screen != null : "fx:id=\"Screen\" was not injected: check your FXML file 'Errorelogin.fxml'.";
        assert Errore != null : "fx:id=\"Errore\" was not injected: check your FXML file 'Errorelogin.fxml'.";
        assert Err != null : "fx:id=\"Err\" was not injected: check your FXML file 'Errorelogin.fxml'.";

    }
}
