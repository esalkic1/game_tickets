package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GameManager;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

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

    //constructor
    public UserMainController(Customer cust){
        this.customer = cust;
        System.out.println(customer.getName());
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void BuyTicketBtnClick(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PurchaseConfirm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),  USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        thisStage.setTitle("Fk Željezničar ulaznice");
        thisStage.setScene(scene);
        thisStage.setResizable(false);
        thisStage.show();
    }
}
