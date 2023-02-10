package ba.unsa.etf.rpr.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class AdminEditController {
    public ChoiceBox cbCompetition;
    public Button btnAddGame;
    public Button btnDeleteGame;

    public void initialize(){
        cbCompetition.getItems().add("Premijer Liga BiH");
        cbCompetition.getItems().add("Kup BiH");
        cbCompetition.getItems().add("Liga Prvaka");
        cbCompetition.getItems().add("Europa Liga");
        cbCompetition.getItems().add("Liga konferencija");
        cbCompetition.getItems().add("Prijateljska utakmica");
    }
}
