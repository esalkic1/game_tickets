package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class PurchaseConfirmController {
    public ChoiceBox cbStand;
    public Button btnConfirmBuy;
    public Button btnCancel;

    public PurchaseConfirmController(Customer cust, Game game) {
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

    public void CancelBtnClick(ActionEvent actionEvent) {
    }

    public void ConfirmBtnClick(ActionEvent actionEvent) {
    }
}
