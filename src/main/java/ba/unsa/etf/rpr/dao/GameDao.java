package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Game;

import java.util.List;

public interface GameDao extends Dao<Game>{

    /**
     * Returns the list of games against the given opponent
     * @param text name of the opponent team
     * @return list of opponents
     */
    List<Game> searchByOpponent(String text);

}
