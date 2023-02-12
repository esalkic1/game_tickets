package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.Dao;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.util.List;

public class CustomerManager {

    public List<Customer> searchByName(String text) throws TicketException{
        return DaoFactory.customerDao().searchByName(text);
    }

    public List<Customer> searchBySurname(String text) throws TicketException{
        return DaoFactory.customerDao().searchBySurname(text);
    }

    public Customer searchByUsername(String text){
        return DaoFactory.customerDao().searchByUsername(text);
    }

    public Customer getById(int id) throws TicketException {
        return DaoFactory.customerDao().getById(id);
    }

    public Customer add(Customer cust) throws TicketException{
        return DaoFactory.customerDao().add(cust);
    }

    public void update(Customer cust) throws TicketException{
        DaoFactory.customerDao().update(cust);
    }

    public void delete(int id) throws TicketException{
        DaoFactory.customerDao().delete(id);
    }

    public List<Customer> getAll() throws TicketException{
        return DaoFactory.customerDao().getAll();
    }
}
