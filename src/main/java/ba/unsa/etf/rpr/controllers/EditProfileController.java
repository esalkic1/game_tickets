package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.business.TicketManager;
import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.List;


public class EditProfileController {

    Customer customer;
    public TextField tfName;
    public TextField tfSurname;
    public TextField tfUsername;
    public PasswordField pfPassword;
    public Button btnEditCustomer;
    public Button btnDeleteTicket;
    public ListView lvTickets;

    public Label lbError;
    public Label lbHighlighted4;
    public Label lbHighlighted3;
    public Label lbHighlighted2;
    public Label lbHighlighted1;

    private CustomerManager customerManager = new CustomerManager();

    //model
    private CustomerModel model = new CustomerModel();

    private TicketManager ticketManager = new TicketManager();
    public EditProfileController(Customer cust){
        this.customer = cust;
    }

    public void initialize(){
        try {
            lvTickets.setItems(FXCollections.observableList(ticketManager.getAll()));
            tfName.textProperty().bindBidirectional(model.tfName);
            tfSurname.textProperty().bindBidirectional(model.tfSurname);
            tfUsername.textProperty().bindBidirectional(model.tfUsername);
            pfPassword.textProperty().bindBidirectional(model.pfPassword);
            model.fromCustomer(customerManager.getById(customer.getId()));  // initializes model
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }

        lbHighlighted1.setVisible(false);
        lbHighlighted2.setVisible(false);
        lbHighlighted3.setVisible(false);
        lbHighlighted4.setVisible(false);

        tfName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty()){
                    lbHighlighted1.setVisible(true);
                }
                else {
                    lbHighlighted1.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || pfPassword.getText().trim().isEmpty()){
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
                    lbHighlighted2.setVisible(true);
                }
                else {
                    lbHighlighted2.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || pfPassword.getText().trim().isEmpty()){
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
                    lbHighlighted3.setVisible(true);
                }
                else {
                    lbHighlighted3.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || pfPassword.getText().trim().isEmpty()){
                    lbError.setVisible(true);
                } else {
                    lbError.setVisible(false);
                }
            }
        });
        pfPassword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if(pfPassword.getText().trim().isEmpty()){
                    lbHighlighted4.setVisible(true);
                }
                else {
                    lbHighlighted4.setVisible(false);
                }
                if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || pfPassword.getText().trim().isEmpty()){
                    lbError.setVisible(true);
                } else {
                    lbError.setVisible(false);
                }
            }
        });

        if(tfName.getText().trim().isEmpty() || tfSurname.getText().trim().isEmpty() || tfUsername.getText().trim().isEmpty() || pfPassword.getText().trim().isEmpty()){
            lbError.setVisible(true);
        } else {
            lbError.setVisible(false);
        }
    }

    public void EditCustomerBtnClick(ActionEvent actionEvent){
        if(lbError.isVisible()) return;

        Customer cust = DaoFactory.customerDao().searchByUsername(tfUsername.getText());
        if(cust != null && cust.getId()!=customer.getId()){
            Alert existingUsername = new Alert(Alert.AlertType.ERROR);
            existingUsername.setTitle("Error");
            existingUsername.setHeaderText("Uneseno korisničko ime se već koristi!");
            existingUsername.setContentText("Pokušajte ponovo");
            existingUsername.showAndWait();
            return;
        }

        String password = pfPassword.getText();
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
        Customer cust1 = model.toCustomer();
        cust1.setId(customer.getId());
        try {
            customerManager.update(cust1);
            Alert bought = new Alert(Alert.AlertType.INFORMATION);
            bought.setTitle("Potvrda");
            bought.setHeaderText("Uspješno ste promijenili podatke!");
            bought.showAndWait();
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteTicketBtnClick(ActionEvent actionEvent){
        Ticket ticket = (Ticket) lvTickets.getSelectionModel().getSelectedItem();
        if(ticket == null){
            Alert noSelection = new Alert(Alert.AlertType.ERROR);
            noSelection.setTitle("Error");
            noSelection.setHeaderText("Nijedna karta nije odabrana!");
            noSelection.setContentText("Odaberite utakmicu i pokušajte ponovo");
            noSelection.showAndWait();
            return;
        }
        try {
            ticketManager.delete(ticket.getId());
            lvTickets.getItems().removeAll(lvTickets.getSelectionModel().getSelectedItem());
            customer.setNumberOfTickets(customer.getNumberOfTickets()-1);
            DaoFactory.customerDao().update(customer);
            Game game = DaoFactory.gameDao().getById(ticket.getGame());
            game.setSold(game.getSold()-1);
            DaoFactory.gameDao().update(game);
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    public class CustomerModel{
        public SimpleStringProperty tfName = new SimpleStringProperty("");
        public SimpleStringProperty tfSurname = new SimpleStringProperty("");
        public SimpleStringProperty tfUsername = new SimpleStringProperty("");
        public SimpleStringProperty pfPassword = new SimpleStringProperty("");

        public void fromCustomer(Customer cust){
            this.tfName.set(cust.getName());
            this.tfSurname.set(cust.getSurname());
            this.tfUsername.set(cust.getUsername());
            this.pfPassword.set(cust.getPassword());
        }

        public Customer toCustomer(){
            Customer cust = new Customer();
            cust.setName(this.tfName.getValue());
            cust.setSurname(this.tfSurname.getValue());
            cust.setUsername(this.tfUsername.getValue());
            cust.setPassword(this.pfPassword.getValue());
            cust.setIsAdmin(0);
            cust.setNumberOfTickets(customer.getNumberOfTickets());
            return cust;
        }
    }
}
