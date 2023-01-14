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


    public CustomerDaoSQLImpl() {
        super("customer");
    }

    /**
     *
     * @param text name of the searched customer
     * @return list of customers
     * @throws TicketException
     */
    @Override
    public List<Customer> searchByName(String text) throws TicketException{
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE name LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,text);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                customers.add(row2object(rs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> searchBySurname(String text) throws TicketException{
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE surname LIKE concat('%', ?, '%')";
        try {
            PreparedStatement stmt = getConnection().prepareStatement(query);
            stmt.setString(1,text);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                customers.add(row2object(rs));
            }
            rs.close();
        } catch (SQLException e) {
            throw new TicketException(e.getMessage(), e);
        }
        return customers;
    }

    @Override
    public Customer row2object(ResultSet rs) throws TicketException {
        try{
            Customer cust = new Customer();
            cust.setId(rs.getInt("id"));
            cust.setName(rs.getString("name"));
            cust.setSurname(rs.getString("surname"));
            cust.setNumberOfTickets(rs.getInt("numberOfTickets"));

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
        return row;
    }


}
