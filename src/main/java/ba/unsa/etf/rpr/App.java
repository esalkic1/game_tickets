package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.CustomerDao;
import ba.unsa.etf.rpr.dao.CustomerDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CustomerDao dao = new CustomerDaoSQLImpl();
        Customer cstm = new Customer();
        cstm.setName("Student");
        cstm.setSurname("Studentic");
        cstm.setNumberOfTickets(4);
        cstm.setPassword("safepassword");
        cstm.setUsername("Emir1921");
        cstm.setIsAdmin(0);
        try {
            dao.add(cstm);
        //System.out.println(dao.getById(2));
            //dao.delete(5);
            System.out.println(dao.getAll());
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }
}
