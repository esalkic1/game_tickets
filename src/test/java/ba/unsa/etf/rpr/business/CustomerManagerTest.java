package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.GameDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.exceptions.TicketException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerManagerTest {
    private CustomerManager customerManager = new CustomerManager();

    @Test
    void addNoPassword(){
        Customer cust = new Customer();
        cust.setName("Test");
        cust.setSurname("Test");
        cust.setUsername("Test");
        cust.setNumberOfTickets(0);
        cust.setIsAdmin(0);
        assertThrows(TicketException.class, ()->{
            customerManager.add(cust);
        });
    }

    @Test
    void getAllCustomers(){
        assertDoesNotThrow(()->{customerManager.getAll();});
    }

    @Test
    void searchCustomerByName(){
        Customer cust = new Customer();
        cust.setName("Test");
        cust.setSurname("Test");
        cust.setUsername("Test");
        cust.setPassword("Test123");
        cust.setNumberOfTickets(0);
        cust.setIsAdmin(0);
        try {
            customerManager.add(cust);
            assertTrue(!customerManager.searchByName("Test").isEmpty());
            customerManager.delete(cust.getId());
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void searchCustomerByUsername(){
        Customer cust = new Customer();
        cust.setName("Test");
        cust.setSurname("Test");
        cust.setUsername("Test");
        cust.setPassword("Test123");
        cust.setNumberOfTickets(0);
        cust.setIsAdmin(0);
        try {
            customerManager.add(cust);
            assertEquals(customerManager.searchByUsername("Test"), cust);
            customerManager.delete(cust.getId());
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }
    }


}
