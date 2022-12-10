package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.Ticket;

import java.util.List;

public interface TicketDao extends Dao<Ticket>{

    /**
     * Finds tickets within given price range
     * @param min minimal price
     * @param max maximal price
     * @return list of tickets
     */
    List<Ticket> getByPriceRange(int min, int max);

}
