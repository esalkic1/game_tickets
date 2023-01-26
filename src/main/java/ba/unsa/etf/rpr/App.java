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
        cstm.setName("Emir");
        cstm.setSurname("Salkić");
        cstm.setNumberOfTickets(0);
        cstm.setPassword("Rprprojekat23");
        cstm.setUsername("Emir1921");
        cstm.setIsAdmin(1);
        try {
            dao.add(cstm);
            Customer customer = DaoFactory.customerDao().searchByUsername("Emir1921");
            System.out.println(customer.getName());
        //System.out.println(dao.getById(2));
            //dao.delete(7);
            //dao.delete(8);
            //dao.delete(9);
            //System.out.println(dao.getAll());
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }
}
