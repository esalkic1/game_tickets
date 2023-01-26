package ba.unsa.etf.rpr.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegisterController {
    public Button btnGoBack;
    public Button btnRegister;
    public Label lbError;
    public Label lbHighlighted3;
    public Label lbHighlighted2;
    public Label lbHighlighted1;
    public TextField tfName;
    public TextField tfSurname;
    public TextField tfUsername;
    public PasswordField tfPassword;


    public void initialize(){
        tfName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty()){
                    lbHighlighted1.setVisible(true);
                }
                else {
                    lbHighlighted1.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty()){
                    lbError.setVisible(true);
                } else {
                    lbError.setVisible(false);
                }
            }
        });
        tfSurname.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(tfSurname.getText().trim().isEmpty() || tfName.getText().trim().isEmpty()){
                    lbHighlighted1.setVisible(true);
                }
                else {
                    lbHighlighted1.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty()){
                    lbError.setVisible(true);
                } else {
                    lbError.setVisible(false);
                }
            }
        });
        tfUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(tfUsername.getText().trim().isEmpty()){
                    lbHighlighted2.setVisible(true);
                }
                else {
                    lbHighlighted2.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty()){
                    lbError.setVisible(true);
                } else {
                    lbError.setVisible(false);
                }
            }
        });
        tfPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(tfPassword.getText().trim().isEmpty()){
                    lbHighlighted3.setVisible(true);
                }
                else {
                    lbHighlighted3.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty()){
                    lbError.setVisible(true);
                } else {
                    lbError.setVisible(false);
                }
            }
        });

        if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty()){
            lbError.setVisible(true);
        } else {
            lbError.setVisible(false);
        }


    }


    public void btnGoBackClick(ActionEvent actionEvent) throws IOException {
    }

    public void btnRegisterClick(ActionEvent actionEvent) {

    }
}
