package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PurchaseConfirmController {
    public ChoiceBox cbStand;
    public Button btnConfirmBuy;
    public Button btnCancel;

    private Customer customer;
    private Game game;

    public PurchaseConfirmController(Customer cust, Game game) {
        this.customer = cust;
        this.game = game;
        System.out.println(cust.getPassword());
        System.out.println(game.getOpponent());
    }

    public void initialize(){
        cbStand.getItems().add("Jug");
        cbStand.getItems().add("Istok");
        cbStand.getItems().add("Sjever");
        cbStand.getItems().add("Zapad");
        cbStand.getItems().add("Zapad-VIP");
    }

    public void CancelBtnClick(ActionEvent actionEvent) throws IOException {
        Node node = (Node) actionEvent.getSource();
        Stage thisStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/UserMain.fxml"));
        fxmlLoader.setController(new UserMainController(customer));
        Scene scene = new Scene(fxmlLoader.load(),  USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
        thisStage.setTitle("Fk Željezničar ulaznice");
        thisStage.setScene(scene);
        thisStage.setResizable(true);
        thisStage.show();
    }

    public void ConfirmBtnClick(ActionEvent actionEvent) {
    }
}
