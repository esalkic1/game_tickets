package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;

import java.sql.*;
import java.util.List;

public class TicketDaoSQLImpl implements TicketDao{

    private Connection connection;

    public TicketDaoSQLImpl(){
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/root", "root", "root");
        } catch (SQLException e) {
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
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public List<Ticket> getByPriceRange(int min, int max) {
        return null;
    }
}
