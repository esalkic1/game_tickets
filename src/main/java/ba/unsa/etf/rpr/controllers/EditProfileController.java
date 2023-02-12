package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.TicketManager;
import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class EditProfileController {

    Customer customer;
    public TextField tfName;
    public TextField tfSurname;
    public TextField tfUsername;
    public PasswordField pfPassword;
    public Button btnEditCustomer;
    public Button btnDeleteTicket;
    public ListView lvTickets;

    private TicketManager manager = new TicketManager();
    public EditProfileController(Customer cust){
        this.customer = cust;
    }

    public void initialize(){
        try {
            lvTickets.setItems(FXCollections.observableList(manager.getAll()));
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    public void EditCustomerBtnClick(ActionEvent actionEvent){
    }

    public void DeleteTicketBtnClick(ActionEvent actionEvent){
        Ticket ticket = (Ticket) lvTickets.getSelectionModel().getSelectedItem();
        try {
            manager.delete(ticket.getId());
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
}
