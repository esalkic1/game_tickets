package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.util.Date;
import java.util.List;

public class GameManager {

    public List<Game> searchByOpponent(String text) throws TicketException{
        return DaoFactory.gameDao().searchByOpponent(text);
    }

    public List<Game> searchByCompetition(String text) throws TicketException{
        return DaoFactory.gameDao().searchByCompetition(text);
    }

    public List<Game> searchByOpponentAndCompetition(String opp, String comp) throws TicketException{
        return DaoFactory.gameDao().searchByOpponentAndCompetition(opp, comp);
    }

    public List<Game> getByDateRange(Date start, Date end) throws TicketException{
        return DaoFactory.gameDao().getByDateRange(start, end);
    }

    public Game getById(int id) throws TicketException{
        return DaoFactory.gameDao().getById(id);
    }

    public Game add(Game game) throws TicketException{
        return DaoFactory.gameDao().add(game);
    }

    public void update(Game game) throws TicketException{
         DaoFactory.gameDao().update(game);
    }

    public void delete(int id) throws TicketException{
        DaoFactory.gameDao().delete(id);
    }

    public List<Game> getAll() throws TicketException{
        return DaoFactory.gameDao().getAll();
    }
}
