package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GameManager;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class UserMainController {

    public Button btnBuyTicket;
    public TableView tvGamesList;
    public TableColumn<Game, String> columnCompetition;
    public TableColumn<Game, String> columnOpponent;
    public TableColumn<Game, LocalDate> columnDate;
    public TableColumn<Game, Integer> columnSelling;
    private Customer customer;

    private GameManager manager = new GameManager();

    public void initialize(){
        try {
            columnCompetition.setCellValueFactory(new PropertyValueFactory<Game, String>("competition"));
            columnOpponent.setCellValueFactory(new PropertyValueFactory<Game, String>("opponent"));
            columnDate.setCellValueFactory(new PropertyValueFactory<Game, LocalDate>("date"));
            columnSelling.setCellValueFactory(new PropertyValueFactory<Game, Integer>("sold"));
            tvGamesList.setItems(FXCollections.observableList(manager.getAll()));
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
