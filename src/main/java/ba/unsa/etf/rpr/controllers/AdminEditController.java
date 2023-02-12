package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.GameManager;
import ba.unsa.etf.rpr.business.TicketManager;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.util.List;

public class AdminEditController {
    public ChoiceBox cbCompetition;
    public Button btnAddGame;
    public Button btnDeleteGame;
    public ListView lvGames;
    public TextField tfCapacity;
    public DatePicker dpDate;
    public TextField tfOpponent;

    private GameManager manager = new GameManager();
    private TicketManager ticketManager = new TicketManager();

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
        if(tfOpponent.getText().trim().equals("") || dpDate.getValue() == null || cbCompetition.getValue() == null || tfCapacity.getText().trim().equals("")){
            Alert noSelection = new Alert(Alert.AlertType.ERROR);
            noSelection.setTitle("Error");
            noSelection.setHeaderText("Sva polja moraju biti unesena!");
            noSelection.setContentText("Unesite sve i pokušajte ponovo");
            noSelection.showAndWait();
            return;
        }
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


        if(game == null){
            Alert noSelection = new Alert(Alert.AlertType.ERROR);
            noSelection.setTitle("Error");
            noSelection.setHeaderText("Nijedna utakmica nije odabrana!");
            noSelection.setContentText("Odaberite utakmicu i pokušajte ponovo");
            noSelection.showAndWait();
            return;
        }

        try {
            List<Ticket> tickets = ticketManager.getAll();
            for(Ticket tckt: tickets){
                if (tckt.getGame() == game.getId());
                Alert childExists = new Alert(Alert.AlertType.ERROR);
                childExists.setTitle("Error");
                childExists.setHeaderText("Ne možete obrisati utakmicu za koju postoje kupljene karte!");
                childExists.setContentText("Odaberite drugu utakmicu i pokušajte ponovo");
                childExists.showAndWait();
                return;
            }
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }

        try {
            manager.delete(game.getId());
            lvGames.getItems().removeAll(lvGames.getSelectionModel().getSelectedItem());
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    }
