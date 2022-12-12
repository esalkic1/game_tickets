package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoSQLImpl implements TicketDao{

    private Connection connection = null;

    public TicketDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_RPRprojekat", "freedb_esalkic1", "?RHx$54HQjTFABG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ticket getById(int id) {
        String query = "SELECT * FROM tickets WHERE idtickets = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("idticket"));
                ticket.setGame(new GameDaoSQLImpl().getById(rs.getInt("game_id")));
                ticket.setCustomer(new CustomerDaoSQLImpl().getById(rs.getInt("customer_id")));
                ticket.setPrice(rs.getInt("price"));
                ticket.setStand(rs.getString("stand"));
                rs.close();
                return ticket;
            }
            else {
                return null;  // no tickets in the result set
            }
        } catch (SQLException e) {
            e.printStackTrace(); // or "throw new RuntimeException(e)"
        }
        return null;
    }

    /**
     * Method that returns next id, used for inserting into database
     * @return id for next entity
     */
    private int getMaxId(){
        int id = 1;
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT MAX(idtickets)+1 FROM tickets");
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
                rs.close();
                return id;
            }
        }catch (SQLException e){
            System.out.println("Database problem");
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public Ticket add(Ticket item) {
        int idtickets = getMaxId();
        String insert = "INSERT INTO tickets VALUES (idtickets, item.getGame(), item.getCustomer(), item.getPrice(), item.getStand())";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.executeUpdate();
            item.setId(idtickets);
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket update(Ticket item) {
        String update = "UPDATE tickets SET game_id = ?, customer_id = ?, price = ?, stand = ? WHERE idtickets = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(update);
            stmt.setInt(1, item.getGame().getId());
            stmt.setInt(2, item.getCustomer().getId());
            stmt.setInt(3, item.getPrice());
            stmt.setString(4, item.getStand());
            stmt.setInt(5, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM tickets WHERE idtickets = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(delete);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Ticket> getAll() {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("idticket"));
                ticket.setGame(new GameDaoSQLImpl().getById(rs.getInt("game_id")));
                ticket.setCustomer(new CustomerDaoSQLImpl().getById(rs.getInt("customer_id")));
                ticket.setPrice(rs.getInt("price"));
                ticket.setStand(rs.getString("stand"));
                tickets.add(ticket);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<Ticket> getByPriceRange(int min, int max) {
        List<Ticket> tickets = new ArrayList<>();
        String query = "SELECT * FROM tickets WHERE price BETWEEN min AND max";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt("idticket"));
                ticket.setGame(new GameDaoSQLImpl().getById(rs.getInt("game_id")));
                ticket.setCustomer(new CustomerDaoSQLImpl().getById(rs.getInt("customer_id")));
                ticket.setPrice(rs.getInt("price"));
                ticket.setStand(rs.getString("stand"));
                tickets.add(ticket);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
