package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 */

public class DaoFactory {
    private static final CustomerDao customerDao = new CustomerDaoSQLImpl();
    private static final GameDao gameDao = new GameDaoSQLImpl();
    private static final TicketDao ticketDao = new TicketDaoSQLImpl();

    private DaoFactory(){
    }

    public static CustomerDao customerDao(){
        return customerDao;
    }

    public static GameDao gameDao(){
        return gameDao;
    }

    public static TicketDao ticketDao(){
        return ticketDao;
    }
}
