package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.util.List;

/**
 * Dao interface for Ticket class
 * @author Emir Salkic
 */
public interface TicketDao extends Dao<Ticket>{

    /**
     * Finds tickets within given price range
     * @param min minimal price
     * @param max maximal price
     * @return list of tickets
     */
    List<Ticket> getByPriceRange(int min, int max) throws TicketException;

}
