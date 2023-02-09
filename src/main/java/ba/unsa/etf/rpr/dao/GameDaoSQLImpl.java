package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.sql.*;
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
            game.setDate(rs.getDate("date"));
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
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM game WHERE opponent LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,text);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                games.add(row2object(rs));
            }
            rs.close();
        } catch (SQLException e) {
            throw new TicketException(e.getMessage(), e);
        }
        return games;
    }

    @Override
    public List<Game> getByDateRange(Date start, Date end) throws TicketException{
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM game WHERE date BETWEEN ? AND ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setObject(1, start);
            stmt.setObject(2, end);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                games.add(row2object(rs));
            }
            rs.close();
            return games;
        } catch (SQLException e) {
            throw new TicketException(e.getMessage(), e);
        }
    }
}
