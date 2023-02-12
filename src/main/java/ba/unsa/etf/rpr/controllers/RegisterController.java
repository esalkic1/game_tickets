package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        thisStage.setTitle("Fk Željezničar ulaznice");
        thisStage.setScene(scene);
        thisStage.setResizable(false);
        thisStage.centerOnScreen();
        thisStage.show();
    }

    public void btnRegisterClick(ActionEvent actionEvent) {
        if(lbError.isVisible()) return;

        Customer customer = DaoFactory.customerDao().searchByUsername(tfUsername.getText());
        if(customer != null){
            Alert existingUsername = new Alert(Alert.AlertType.ERROR);
            existingUsername.setTitle("Error");
            existingUsername.setHeaderText("Uneseno korisničko ime se već koristi!");
            existingUsername.setContentText("Pokušajte ponovo");
            existingUsername.showAndWait();
            return;
        }

        String password = tfPassword.getText();
        boolean containsNumber = false;
        for(int i = 0; i < password.length(); i++){
            if(Character.isDigit(password.charAt(i))) containsNumber = true;
        }
        if(!containsNumber){
            Alert noNumber = new Alert(Alert.AlertType.ERROR);
            noNumber.setTitle("Error");
            noNumber.setHeaderText("Šifra mora sadržavati broj!");
            noNumber.setContentText("Pokušajte ponovo");
            noNumber.showAndWait();
            return;
        }
        boolean containsUpperCase = false;
        for(int i = 0; i < password.length(); i++){
            if(Character.isUpperCase(password.charAt(i))) containsUpperCase = true;
        }
        if(!containsUpperCase){
            Alert noUpperCase = new Alert(Alert.AlertType.ERROR);
            noUpperCase.setTitle("Error");
            noUpperCase.setHeaderText("Šifra mora sadržavati veliko slovo!");
            noUpperCase.setContentText("Pokušajte ponovo");
            noUpperCase.showAndWait();
            return;
        }

        Customer newcustomer = new Customer();
        newcustomer.setName(tfName.getText());
        newcustomer.setSurname(tfSurname.getText());
        newcustomer.setNumberOfTickets(0);
        newcustomer.setPassword(tfPassword.getText());
        newcustomer.setUsername(tfUsername.getText());
        newcustomer.setIsAdmin(0);

        try {
            DaoFactory.customerDao().add(newcustomer);
            Alert registered = new Alert(Alert.AlertType.INFORMATION);
            registered.setTitle("Dobrodošli");
            registered.setHeaderText("Uspješno ste registrovani!");
            registered.setContentText("Mi smo Željini, Željo je naš");
            registered.showAndWait();
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }
}
