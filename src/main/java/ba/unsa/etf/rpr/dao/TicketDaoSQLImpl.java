package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of DAO
 */

public class TicketDaoSQLImpl extends AbstractDao<Ticket> implements TicketDao{


    public TicketDaoSQLImpl() {
       super("tickets");
    }



    @Override
    public List<Ticket> getByPriceRange(int min, int max) throws TicketException{
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE price BETWEEN ? AND ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setObject(1, min);
            stmt.setObject(2, max);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                tickets.add(row2object(rs));
            }
            rs.close();
        } catch (SQLException e) {
            throw new TicketException(e.getMessage(),e);
        }
        return tickets;
    }

    @Override
    public Ticket row2object(ResultSet rs) throws TicketException {
        try{
            Ticket ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setGame(DaoFactory.gameDao().getById(rs.getInt("game_id")));
            ticket.setCustomer(DaoFactory.customerDao().getById(rs.getInt("customer_id")));
            ticket.setPrice(rs.getInt("price"));
            ticket.setStand(rs.getString("stand"));
            return ticket;
        }catch (SQLException e){
            throw new TicketException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Ticket object) {
        Map<String, Object> item = new TreeMap<String, Object>();
        item.put("id", object.getId());
        item.put("game_id", object.getGame());
        item.put("customer_id", object.getCustomer());
        item.put("price", object.getPrice());
        item.put("stand", object.getStand());
        return item;
    }
}
