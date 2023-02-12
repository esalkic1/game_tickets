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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class UserMainController {

    public Button btnBuyTicket;
    public Button btnEditProfile;
    public Button btnFilterList;
    public Button btnRemoveFilters;
    public TextField tfCompetition;
    public TextField tfOpponent;
    public TableView tvGamesList;
    public TableColumn<Game, String> columnCompetition;
    public TableColumn<Game, String> columnOpponent;
    public TableColumn<Game, LocalDate> columnDate;
    public TableColumn<Game, Integer> columnSelling;
    public TableColumn<Game, Integer> columnCapacity;
    private Customer customer;

    private GameManager manager = new GameManager();

    public void initialize(){
        try {
            columnCompetition.setCellValueFactory(new PropertyValueFactory<Game, String>("competition"));
            columnOpponent.setCellValueFactory(new PropertyValueFactory<Game, String>("opponent"));
            columnDate.setCellValueFactory(new PropertyValueFactory<Game, LocalDate>("date"));
            columnSelling.setCellValueFactory(new PropertyValueFactory<Game, Integer>("sold"));
            columnCapacity.setCellValueFactory(new PropertyValueFactory<Game, Integer>("capacity"));
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
        if(tvGamesList.getSelectionModel().getSelectedItem() == null){
            Alert noSelection = new Alert(Alert.AlertType.ERROR);
            noSelection.setTitle("Error");
            noSelection.setHeaderText("Niste odabrali utakmicu!");
            noSelection.setContentText("Odaberite utakmicu i pokušajte ponovo");
            noSelection.showAndWait();
            return;
        }
        Game game = (Game) tvGamesList.getSelectionModel().getSelectedItem();
        if(game.getSold() == game.getCapacity()){
            Alert noSelection = new Alert(Alert.AlertType.ERROR);
            noSelection.setTitle("Error");
            noSelection.setHeaderText("Nema dostupnih karata!");
            noSelection.setContentText("Odaberite drugu utakmicu i pokušajte ponovo");
            noSelection.showAndWait();
            return;
        }
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PurchaseConfirm.fxml"));
        fxmlLoader.setController(new PurchaseConfirmController(customer, (Game)tvGamesList.getSelectionModel().getSelectedItem()));
        Scene scene = new Scene(fxmlLoader.load(),  USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        thisStage.setTitle("Fk Željezničar ulaznice");
        thisStage.setScene(scene);
        thisStage.centerOnScreen();
        thisStage.setResizable(false);
        thisStage.show();
    }

    public void EditProfileBtn(ActionEvent actionEvent) throws IOException {
        //Node node = (Node) actionEvent.getSource();
        //Stage thisStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EditProfile.fxml"));
        fxmlLoader.setController(new EditProfileController(customer));
        Scene scene = new Scene(fxmlLoader.load(),  USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        Stage newStage = new Stage();
        newStage.setTitle("Fk Željezničar ulaznice");
        newStage.setScene(scene);
        newStage.centerOnScreen();
        newStage.setResizable(false);
        newStage.show();
    }

    public void FilterListBtnClick(ActionEvent actionEvent){
        if(!tfOpponent.getText().equals("") && !tfCompetition.getText().equals("")){
            try {
                tvGamesList.setItems(FXCollections.observableList(manager.searchByOpponentAndCompetition(tfOpponent.getText(), tfCompetition.getText())));
            } catch (TicketException e) {
                throw new RuntimeException(e);
            }
        }
        else if(!tfOpponent.getText().equals("")) {
            try {
                tvGamesList.setItems(FXCollections.observableList(manager.searchByOpponent(tfOpponent.getText())));
            } catch (TicketException e) {
                throw new RuntimeException(e);
            }
        }
        else if(!tfCompetition.getText().equals("")){
            try {
                tvGamesList.setItems(FXCollections.observableList(manager.searchByCompetition(tfCompetition.getText())));
            } catch (TicketException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void RemoveFiltersBtnClick(ActionEvent actionEvent){
        try {
            tvGamesList.setItems(FXCollections.observableList(manager.getAll()));
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }
}
