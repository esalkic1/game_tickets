package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * MySQL implementation of DAO
 */

public class CustomerDaoSQLImpl extends AbstractDao<Customer> implements CustomerDao{

    private static CustomerDaoSQLImpl instance = null;
    private CustomerDaoSQLImpl() {
        super("customer");
    }

    public static CustomerDaoSQLImpl getInstance(){
        if (instance == null)
            instance = new CustomerDaoSQLImpl();
        return instance;
    }

    public static void removeInstance(){
        if (instance!=null)
            instance = null;
    }

    /**
     *
     * @param text name of the searched customer
     * @return list of customers
     * @throws TicketException
     */
    @Override
    public List<Customer> searchByName(String text) throws TicketException{
        return executeQuery("SELECT * FROM customer WHERE name LIKE concat('%', ?, '%')", new Object[]{text});
    }

    @Override
    public List<Customer> searchBySurname(String text) throws TicketException{
        return executeQuery("SELECT * FROM customer WHERE surname LIKE concat('%', ?, '%')", new Object[]{text});

    }

    @Override
    public Customer searchByUsername(String text){
        List<Customer> customers = null;
        try {
            customers = this.getAll();
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
        for(Customer cust : customers){
            if (cust.getUsername().equals(text))
                return cust;
        }
        return null;
    }



    @Override
    public Customer row2object(ResultSet rs) throws TicketException {
        try{
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setName(rs.getString("name"));
            cust.setSurname(rs.getString("surname"));
            cust.setNumberOfTickets(rs.getInt("numberOfTickets"));
            cust.setPassword(rs.getString("password"));
            cust.setUsername(rs.getString("username"));
            cust.setIsAdmin(rs.getInt("isAdmin"));
            return cust;
        } catch (SQLException e){
            throw new TicketException(e.getMessage(), e);
        }
    }

    @Override
    public Map<String, Object> object2row(Customer object) {
        Map<String, Object> row = new TreeMap<String, Object>();
        row.put("id", object.getId());
        row.put("name", object.getName());
        row.put("surname", object.getSurname());
        row.put("numberOfTickets", object.getNumberOfTickets());
        row.put("password", object.getPassword());
        row.put("username", object.getUsername());
        row.put("isAdmin", object.getIsAdmin());
        return row;
    }


}
