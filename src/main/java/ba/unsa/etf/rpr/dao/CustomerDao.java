package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.util.List;

/**
 * Dao interface for Customer class
 * @author Emir Salkic
 */

public interface CustomerDao extends Dao<Customer>{

    /**
     * Method that finds all customers with a given name
     *
     * @param text name of the searched customer
     * @return list of customers with the given name
     */
    List<Customer> searchByName(String text) throws TicketException;

    /**
     * Method that finds all customers with a given surname
     * @param text surname of the searched customer
     * @return list of customers with the given surname
     */
    List<Customer> searchBySurname(String text) throws TicketException;
}
