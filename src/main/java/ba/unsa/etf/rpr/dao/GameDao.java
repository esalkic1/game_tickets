package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Game;

import java.util.Date;
import java.util.List;

/**
 * Dao interface for Game class
 * @author Emir Salkic
 */
public interface GameDao extends Dao<Game>{

    /**
     * Returns the list of games against the given opponent
     * @param text name of the opponent team
     * @return list of games
     */
    List<Game> searchByOpponent(String text);

    /**
     * Finds games in the given period of time
     * @param start starting date
     * @param end ending date
     * @return list of games
     */
    List<Game> getByDateRange(Date start, Date end);
}
