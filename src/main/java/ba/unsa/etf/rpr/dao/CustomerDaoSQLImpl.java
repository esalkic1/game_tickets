package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;

import java.sql.*;
import java.util.List;

public class CustomerDaoSQLImpl implements CustomerDao{

    private Connection connection;

    public CustomerDaoSQLImpl() {
        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://sql7.freemysqlhosting.net:3306/?user=sql7582893", "sql7582893", "wva4w4nSBZ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Customer> searchByName(String text) {
        return null;
    }

    @Override
    public List<Customer> searchBySurname(String text) {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public Customer add(Customer item) {
        return null;
    }

    @Override
    public Customer update(Customer item) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Customer> getAll() {
        return null;
    }
}
