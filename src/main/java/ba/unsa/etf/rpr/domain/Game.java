package ba.unsa.etf.rpr.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * Game information
 * @author Emir Salkic
 */
public class Game implements Idable{
    private int id;
    private int capacity;
    private int sold;
    private String opponent;
    private LocalDate date;
    private String competition;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCompetition(){return competition; }

    public void setCompetition(String competition) { this.competition = competition; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id == game.id && capacity == game.capacity && sold == game.sold && opponent.equals(game.opponent) && date.equals(game.date) && competition.equals(game.competition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, capacity, sold, opponent, date, competition);
    }

    @Override
    public String toString() {
        return "{"+
                "protivnik='" + opponent + '\'' +
                ", datum=" + date +
                ", takmičenje='" + competition + '\'' +
                '}';
    }
}


