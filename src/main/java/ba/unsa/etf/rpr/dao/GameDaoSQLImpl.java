package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Game;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class GameDaoSQLImpl implements GameDao{

    private Connection connection;

    public GameDaoSQLImpl() {
       /* try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/?user=sql7582893", "sql7582893", "wva4w4nSBZ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Game getById(int id) {
        String query = "SELECT * FROM game WHERE idgame = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Game game = new Game();
                game.setId(rs.getInt("idgame"));
                game.setCapacity(rs.getInt("capacity"));
                game.setSold(rs.getInt("sold"));
                game.setOpponent(rs.getString("opponent"));
                game.setDate(rs.getDate("date"));
                rs.close();
                return game;
            }
            else {
                return null;  // no games in the result set
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
            PreparedStatement stmt = this.connection.prepareStatement("SELECT MAX(idgame)+1 FROM game");
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
    public Game add(Game item) {
        int idgame = getMaxId();
        String insert = "INSERT INTO game VALUES (idgame, item.getCapacity(), item.getSold(), item.getOpponent(), item.getDate())";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.executeUpdate();
            item.setId(idgame);
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Game update(Game item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public List<Game> searchByOpponent(String text) {
        return null;
    }

    @Override
    public List<Game> getByDateRange(Date start, Date end) {
        return null;
    }
}
