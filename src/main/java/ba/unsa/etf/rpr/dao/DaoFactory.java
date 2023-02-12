package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 */

public class DaoFactory {
    private static final CustomerDao customerDao = CustomerDaoSQLImpl.getInstance();
    private static final GameDao gameDao = GameDaoSQLImpl.getInstance();
    private static final TicketDao ticketDao = TicketDaoSQLImpl.getInstance();

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
