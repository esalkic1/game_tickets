package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class PurchaseConfirmController {
    public ChoiceBox cbStand;
    public Button btnConfirmBuy;
    public Button btnCancel;

    private Customer customer;
    private Game game;

    public Label lbPrice;

    private SimpleStringProperty labelOpponent;
    private SimpleObjectProperty<LocalDate> labelDate;

    public LocalDate getLabelDate() {
        return labelDate.get();
    }

    public SimpleObjectProperty<LocalDate> labelDateProperty() {
        return labelDate;
    }

    public void setLabelDate(LocalDate labelDate) {
        this.labelDate.set(labelDate);
    }

    public String getLabelOpponent() {
        return labelOpponent.get();
    }

    public SimpleStringProperty labelOpponentProperty() {
        return labelOpponent;
    }

    public void setLabelOpponent(String labelOpponent) {
        this.labelOpponent.set(labelOpponent);
    }

    public PurchaseConfirmController(Customer cust, Game game) {
        this.customer = cust;
        this.game = game;
        labelOpponent = new SimpleStringProperty(game.getOpponent());
        labelDate = new SimpleObjectProperty<LocalDate>(game.getDate());
        System.out.println(cust.getPassword());
        System.out.println(game.getOpponent());
    }

    public void initialize(){
        cbStand.getItems().add("Jug");
        cbStand.getItems().add("Istok");
        cbStand.getItems().add("Sjever");
        cbStand.getItems().add("Zapad");
        cbStand.getItems().add("Zapad-VIP");
        cbStand.getItems().add("Dječija");
    }

    /**
     * Method that displays a different price based on the competition and the chosen stand
     * @param actionEvent
     */
    public void StandChanged(ActionEvent actionEvent){
        String stand = (String) cbStand.getSelectionModel().getSelectedItem();
        if(game.getCompetition().equals("Premijer Liga BiH") || game.getCompetition().equals("Kup BiH")) {
            switch (stand) {
                case "Jug":
                    lbPrice.setText("5 KM"); break;
                case "Istok":
                    lbPrice.setText("10 KM"); break;
                case "Sjever":
                    lbPrice.setText("8 KM"); break;
                case "Zapad":
                    lbPrice.setText("20 KM"); break;
                case "Zapad-VIP":
                    lbPrice.setText("30 KM"); break;
                case "Dječija":
                    lbPrice.setText("2 KM"); break;
            }
        }
        else if(game.getCompetition().equals("Liga Prvaka") || game.getCompetition().equals("Europa Liga") || game.getCompetition().equals("Liga konferencija")) {
            switch (stand) {
                case "Jug":
                    lbPrice.setText("10 KM"); break;
                case "Istok":
                    lbPrice.setText("20 KM"); break;
                case "Sjever":
                    lbPrice.setText("16 KM"); break;
                case "Zapad":
                    lbPrice.setText("40 KM"); break;
                case "Zapad-VIP":
                    lbPrice.setText("60 KM"); break;
                case "Dječija":
                    lbPrice.setText("5 KM"); break;
            }
        }
        else if(game.getCompetition().equals("Prijateljska utakmica")){
            switch (stand) {
                case "Jug":
                    lbPrice.setText("3 KM"); break;
                case "Istok":
                    lbPrice.setText("8 KM"); break;
                case "Sjever":
                    lbPrice.setText("6 KM"); break;
                case "Zapad":
                    lbPrice.setText("12 KM"); break;
                case "Zapad-VIP":
                    lbPrice.setText("20 KM"); break;
                case "Dječija":
                    lbPrice.setText("1 KM"); break;
            }
        }
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
        Ticket ticket = new Ticket();
        ticket.setGame(game.getId());
        ticket.setCustomer(customer.getId());
        String price= lbPrice.getText();
        String numberOnly = price.replaceAll("[^0-9]", "");
        ticket.setPrice(Integer.parseInt(numberOnly));
        ticket.setStand((String) cbStand.getSelectionModel().getSelectedItem());
        try {
            DaoFactory.ticketDao().add(ticket);
            customer.setNumberOfTickets(customer.getNumberOfTickets()+1);
            DaoFactory.customerDao().update(customer);
            Game game = DaoFactory.gameDao().getById(ticket.getGame());
            game.setSold(game.getSold()+1);
            DaoFactory.gameDao().update(game);
            //System.out.println(customer.getNumberOfTickets()+1);
            Alert bought = new Alert(Alert.AlertType.INFORMATION);
            bought.setTitle("Potvrda");
            bought.setHeaderText("Uspješno ste kupili ulaznicu!");
            bought.setContentText("Kupite još ulaznica za istu utakmicu ili se vratite na izbor druge!");
            bought.showAndWait();
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

}
