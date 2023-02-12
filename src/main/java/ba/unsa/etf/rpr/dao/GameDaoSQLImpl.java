package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;


public class GameDaoSQLImpl extends AbstractDao<Game> implements GameDao{

    public GameDaoSQLImpl() {
        super("game");
    }

    @Override
    public Game row2object(ResultSet rs) throws TicketException {
        try {
            Game game = new Game();
            game.setId(rs.getInt("id"));
            game.setCapacity(rs.getInt("capacity"));
            game.setSold(rs.getInt("sold"));
            game.setOpponent(rs.getString("opponent"));
            game.setDate(rs.getObject("date", LocalDate.class));
            game.setCompetition(rs.getString("competition"));
            return game;
        }catch (SQLException e){
            throw new TicketException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Game object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("capacity", object.getCapacity());
        item.put("sold", object.getSold());
        item.put("opponent", object.getOpponent());
        item.put("date", object.getDate());
        item.put("competition", object.getCompetition());
        return item;
    }

    @Override
    public List<Game> searchByOpponent(String text) throws TicketException{
        return executeQuery("SELECT * FROM game WHERE opponent LIKE concat('%', ?, '%')", new Object[]{text});
    }

    public List<Game> searchByCompetition(String text) throws TicketException{
        return executeQuery("SELECT * FROM game WHERE competition LIKE concat('%', ?, '%')", new Object[]{text});
    }

    public List<Game> searchByOpponentAndCompetition(String opp, String comp) throws TicketException{
        return executeQuery("SELECT * FROM game WHERE opponent LIKE concat('%', ?, '%') AND competition LIKE concat('%', ?, '%')",
                new Object[]{opp, comp});
    }

    @Override
    public List<Game> getByDateRange(Date start, Date end) throws TicketException{
        return executeQuery("SELECT * FROM game WHERE date BETWEEN ? AND ?", new Object[]{start, end});
    }
}
