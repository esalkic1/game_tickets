package ba.unsa.etf.rpr;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class AppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
