package ba.unsa.etf.rpr.business;


import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;

import java.util.List;

public class TicketManager {

    public List<Ticket> getByPriceRange(int min, int max) throws TicketException {
        return DaoFactory.ticketDao().getByPriceRange(min, max);
    }

    public Ticket getById(int id) throws TicketException {
        return DaoFactory.ticketDao().getById(id);
    }

    public Ticket add(Ticket ticket) throws TicketException{
        return DaoFactory.ticketDao().add(ticket);
    }

    public void update(Ticket ticket) throws TicketException{
        DaoFactory.ticketDao().update(ticket);
    }

    public void delete(int id) throws TicketException{
        DaoFactory.ticketDao().delete(id);
    }

    public List<Ticket> getAll() throws TicketException{
        return DaoFactory.ticketDao().getAll();
    }
}
