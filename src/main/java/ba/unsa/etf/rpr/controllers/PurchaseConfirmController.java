package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class PurchaseConfirmController {
    public ChoiceBox cbStand;

    public void initialize(){
        cbStand.getItems().add("Jug");
        cbStand.getItems().add("Istok");
        cbStand.getItems().add("Sjever");
        cbStand.getItems().add("Zapad");
        cbStand.getItems().add("Zapad-VIP");
    }
}
