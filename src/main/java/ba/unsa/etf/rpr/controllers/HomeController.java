package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HomeController {


    public TextField tfUsername;
    public PasswordField tfPassword;
    public Button btnLogin;
    public Button btnRegister;

    public void loginBtnClick(ActionEvent actionEvent) {
        if(tfUsername.getText().isEmpty()){
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setTitle("Error");
            empty.setHeaderText("Niste unijeli korisnicko ime!");
            empty.setContentText("Pokušajte ponovo");

            empty.showAndWait();
        }
    }

    public void registerBtnClick(ActionEvent actionEvent) {
    }
}
