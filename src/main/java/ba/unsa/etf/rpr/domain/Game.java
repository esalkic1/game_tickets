package ba.unsa.etf.rpr.domain;

import java.util.Date;
import java.util.Objects;

/**
 * Game information
 * @author Emir Salkic
 */
public class Game {
    private int id;
    private int capacity;
    private int sold;
    private String opponent;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && capacity == game.capacity && sold == game.sold && opponent.equals(game.opponent) && date.equals(game.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, sold, opponent, date);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", sold=" + sold +
                ", opponent='" + opponent + '\'' +
                ", date=" + date +
                '}';
    }
}


