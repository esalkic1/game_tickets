package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.AppFX;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HomeController {


    public TextField tfUsername;
    public PasswordField tfPassword;
    public Button btnLogin;
    public Button btnRegister;

    public void initialize(){
        tfUsername.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(tfUsername.getText().trim().isEmpty()){
                    tfUsername.getStyleClass().removeAll("correctinput");
                    tfUsername.getStyleClass().add("wronginput");
                }
                else {
                    tfUsername.getStyleClass().removeAll("wronginput");
                    tfUsername.getStyleClass().add("correctinput");
                }
            }
        });
        tfPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(tfPassword.getText().trim().isEmpty()){
                    tfPassword.getStyleClass().removeAll("correctinput");
                    tfPassword.getStyleClass().add("wronginput");
                }
                else {
                    tfPassword.getStyleClass().removeAll("wronginput");
                    tfPassword.getStyleClass().add("correctinput");
                }
            }
        });
    }

    public void loginBtnClick(ActionEvent actionEvent) throws IOException {
        if(tfUsername.getText().isEmpty()){
            tfUsername.getStyleClass().add("wronginput");
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setTitle("Error");
            empty.setHeaderText("Niste unijeli korisnicko ime!");
            empty.setContentText("Pokušajte ponovo");
            empty.showAndWait();
            return;
        }
        /*if(tfPassword.getText().isEmpty()){
            Alert empty = new Alert(Alert.AlertType.ERROR);
            empty.setTitle("Error");
            empty.setHeaderText("Niste unijeli šifru!");
            empty.setContentText("Pokušajte ponovo");
            empty.showAndWait();
        }*/

            Customer customer = DaoFactory.customerDao().searchByUsername(tfUsername.getText());
            if(customer == null){
                tfUsername.getStyleClass().add("wronginput");
                Alert wrongUsername = new Alert(Alert.AlertType.ERROR);
                wrongUsername.setTitle("Error");
                wrongUsername.setHeaderText("Unijeli ste pogrešno korisničko ime!");
                wrongUsername.setContentText("Pokušajte ponovo");
                wrongUsername.showAndWait();
                return;
            }

            if(!customer.getPassword().equals(tfPassword.getText())){
                tfUsername.getStyleClass().add("correctinput");
                tfPassword.getStyleClass().add("wronginput");
                Alert wrongUsername = new Alert(Alert.AlertType.ERROR);
                wrongUsername.setTitle("Error");
                wrongUsername.setHeaderText("Unijeli ste pogrešnu šifru!");
                wrongUsername.setContentText("Pokušajte ponovo");
                wrongUsername.showAndWait();
                return;
            }
            Node node = (Node) actionEvent.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            Stage newStage = new Stage();
            if(customer.getIsAdmin()!=1) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UserMain.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                newStage.setTitle("Fk Željezničar ulaznice");
                newStage.setScene(scene);
                newStage.setResizable(false);
                newStage.show();
                thisStage.close();
            }
            else{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/AdminEdit.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                newStage.setTitle("Fk Željezničar ulaznice");
                newStage.setScene(scene);
                newStage.setResizable(false);
                newStage.show();
                thisStage.close();
            }

    }

    public void registerBtnClick(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        thisStage.setTitle("Fk Željezničar ulaznice");
        thisStage.setScene(scene);
        thisStage.setResizable(false);
        thisStage.show();
    }
}
