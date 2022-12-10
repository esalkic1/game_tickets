package ba.unsa.etf.rpr.dao;

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
    T getById(int id);

    /**
     * Method that adds the item as entity in the database
     * @param item to be added in the database
     * @return added item
     */
    T add(T item);

    /**
     * Method that updates existing entity in database
     * @param item to be updated
     * @return updated version of entity
     */
    T update(T item);

    void delete(int id);

    List<T> getAll();
}
