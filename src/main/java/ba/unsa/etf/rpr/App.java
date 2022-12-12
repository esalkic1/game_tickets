package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.dao.CustomerDao;
import ba.unsa.etf.rpr.dao.CustomerDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Customer;

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
        cstm.setName("Ime");
        cstm.setSurname("Prezime");
        cstm.setNumberOfTickets(7);
        dao.add(cstm);
        //System.out.println(dao.getById(2));
        System.out.println(dao.getAll());
    }
}
