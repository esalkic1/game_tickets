package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.exceptions.TicketException;

import java.util.List;

/**
 * interface for dao classes
 * @author Emir Salkic
 */
public interface Dao<T> {

    /**
     * Method that gets an entity by received id
     * @param id primary key
     * @return entity from database
     */
    T getById(int id) throws TicketException;

    /**
     * Method that adds the item as entity in the database
     * @param item to be added in the database
     * @return added item
     */
    T add(T item) throws TicketException;

    /**
     * Method that updates existing entity in database
     * @param item to be updated
     * @return updated version of entity
     */
    T update(T item) throws TicketException;

    /**
     * Method that deletes an entity from database based on id
     * @param id primary key of the to be deleted entity
     */
    void delete(int id) throws TicketException;

    /**
     * Method that returns all the items stored in the database
     * @return returns all items as a list
     */
    List<T> getAll() throws TicketException;
}
