package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoSQLImpl implements CustomerDao{

    private Connection connection;

    public CustomerDaoSQLImpl() {
       /* try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }*/
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/?user=freedb_esalkic1", "freedb_esalkic1", "?RHx$54HQjTFABG");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Customer> searchByName(String text) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE name = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,text);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Customer cstm = new Customer();
                cstm.setId(rs.getInt("idcustomer"));
                cstm.setName(rs.getString("name"));
                cstm.setSurname(rs.getString("surname"));
                cstm.setNumberOfTickets(rs.getInt("numberOfTickets"));
                customers.add(cstm);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public List<Customer> searchBySurname(String text) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE surname = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setString(1,text);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Customer cstm = new Customer();
                cstm.setId(rs.getInt("idcustomer"));
                cstm.setName(rs.getString("name"));
                cstm.setSurname(rs.getString("surname"));
                cstm.setNumberOfTickets(rs.getInt("numberOfTickets"));
                customers.add(cstm);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getById(int id) {
        String query = "SELECT * FROM customer WHERE idcustomer = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("idcustomer"));
                customer.setName(rs.getString("name"));
                customer.setSurname(rs.getString("surname"));
                customer.setNumberOfTickets(rs.getInt("numberOfTickets"));
                rs.close();
                return customer;
            }
            else {
                return null;  // no customers in the result set
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
            PreparedStatement stmt = this.connection.prepareStatement("SELECT MAX(idcustomer)+1 FROM customer");
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
    public Customer add(Customer item) {
        int idcustomer = getMaxId();
        String insert = "INSERT INTO customer VALUES (idcustomer, item.getName(), item.getSurname(), item.getNumberOfTickets())";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(insert);
            stmt.executeUpdate();
            item.setId(idcustomer);
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Customer update(Customer item) {
        String update = "UPDATE customer SET name = ?, surname = ?, numberOfTickets = ? WHERE idcustomer = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(update);
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getSurname());
            stmt.setInt(3, item.getNumberOfTickets());
            stmt.setInt(4, item.getId());
            stmt.executeUpdate();
            return item;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String delete = "DELETE FROM customer WHERE idcustomer = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(delete);
            stmt.setInt(1,id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Customer cstm = new Customer();
                cstm.setId(rs.getInt("idcustomer"));
                cstm.setName(rs.getString("name"));
                cstm.setSurname(rs.getString("surname"));
                cstm.setNumberOfTickets(rs.getInt("numberOfTickets"));
                customers.add(cstm);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
