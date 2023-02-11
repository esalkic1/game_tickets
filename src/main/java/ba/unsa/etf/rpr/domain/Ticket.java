package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class for ticket information
 * @author Emir Salkic
 */
public class Ticket implements Idable{
    private int id;
    private int game;
    private int customer;
    private int price;
    private String stand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && price == ticket.price && game == ticket.game && customer == ticket.customer && stand.equals(ticket.stand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, game, customer, price, stand);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", game=" + game +
                ", customer=" + customer +
                ", price=" + price +
                ", stand='" + stand + '\'' +
                '}';
    }
}
