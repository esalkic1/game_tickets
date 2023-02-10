package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GameManager;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class AdminEditController {
    public ChoiceBox cbCompetition;
    public Button btnAddGame;
    public Button btnDeleteGame;
    public ListView lvGames;
    public TextField tfCapacity;
    public DatePicker dpDate;
    public TextField tfOpponent;

    private GameManager manager = new GameManager();

    public void initialize(){
        cbCompetition.getItems().add("Premijer Liga BiH");
        cbCompetition.getItems().add("Kup BiH");
        cbCompetition.getItems().add("Liga Prvaka");
        cbCompetition.getItems().add("Europa Liga");
        cbCompetition.getItems().add("Liga konferencija");
        cbCompetition.getItems().add("Prijateljska utakmica");

        try {
            lvGames.setItems(FXCollections.observableList(manager.getAll()));
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }


    public void AddGameBtnClick(ActionEvent actionEvent) {
        Game game = new Game();
        game.setCapacity(Integer.parseInt(tfCapacity.getText()));
        game.setSold(0);
        game.setOpponent(tfOpponent.getText());
        game.setDate(dpDate.getValue());
        game.setCompetition((String) cbCompetition.getValue());
        try {
            game = manager.add(game);
            lvGames.getItems().add(game);
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    public void DeleteGameBtnClick(ActionEvent actionEvent) {
        Game game = (Game) lvGames.getSelectionModel().getSelectedItem();
        try {
            manager.delete(game.getId());
            lvGames.getItems().removeAll(lvGames.getSelectionModel().getSelectedItem());
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    }
