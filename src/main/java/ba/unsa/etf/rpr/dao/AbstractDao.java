package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.exceptions.TicketException;
import com.sun.scenario.effect.impl.prism.PrReflectionPeer;

import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * Abstract class that implements CRUD methods
 */

public abstract class AbstractDao<T extends Idable> implements  Dao<T>{
    private static Connection connection = null;
    private String tableName;

    public AbstractDao(String tableName){
        try {
            this.tableName = tableName;
            Properties p = new Properties();
            p.load(ClassLoader.getSystemResource("database.properties").openStream());
            this.connection = DriverManager.getConnection(p.getProperty("db.connection_string"), p.getProperty("db.username"), p.getProperty("db.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public abstract T row2object(ResultSet rs) throws TicketException;

    public abstract Map<String, Object> object2row(T object);

    public T getById(int id) throws TicketException{
        String query = "SELECT * FROM "+this.tableName+" WHERE id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) { // result set is iterator.
                T result = row2object(rs);
                rs.close();
                return result;
            } else {
                throw new TicketException("Object not found");
            }
        }
        catch (SQLException e) {
            throw new TicketException(e.getMessage(), e);
        }
    }

    public List<T> getAll() throws TicketException{
        String query = "SELECT * FROM "+ tableName;
        List<T> results = new ArrayList<T>();
        try{
            PreparedStatement stmt = getConnection().prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){ // result set is iterator.
                T object = row2object(rs);
                results.add(object);
            }
            rs.close();
            return results;
        }catch (SQLException e){
            throw new TicketException(e.getMessage(), e);
        }
    }

    public void delete(int id) throws TicketException {
        String query = "DELETE FROM "+tableName+" WHERE id = ?";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for(Map.Entry<String, Object> entry: row.entrySet()){
            counter++;
            if(entry.getKey().equals("id")) continue;
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter){
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<String,String>(columns.toString(), questions.toString());
    }

    private String prepareUpdateParts(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("id")) continue; //skip update of id due where clause
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter) {
                columns.append(",");
            }
        }
        return columns.toString();
    }

}
