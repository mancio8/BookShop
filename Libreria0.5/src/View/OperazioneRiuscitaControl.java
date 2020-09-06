package View;

/**
 * Sample Skeleton for 'OperazioneRiuscita.fxml' Controller Class
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class OperazioneRiuscitaControl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="screen"
    private ImageView screen; // Value injected by FXMLLoader

    @FXML // fx:id="labelok"
    private Label labelok; // Value injected by FXMLLoader

    @FXML // fx:id="OK"
    private ImageView OK; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert screen != null : "fx:id=\"screen\" was not injected: check your FXML file 'OperazioneRiuscita.fxml'.";
        assert labelok != null : "fx:id=\"labelok\" was not injected: check your FXML file 'OperazioneRiuscita.fxml'.";
        assert OK != null : "fx:id=\"OK\" was not injected: check your FXML file 'OperazioneRiuscita.fxml'.";

    }
}
