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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    }

    public void EditCustomerBtnClick(ActionEvent actionEvent){
        Customer cust = model.toCustomer();
        cust.setId(customer.getId());
        try {
            customerManager.update(cust);
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteTicketBtnClick(ActionEvent actionEvent){
        Ticket ticket = (Ticket) lvTickets.getSelectionModel().getSelectedItem();
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
