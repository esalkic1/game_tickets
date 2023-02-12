package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketManagerTest {
    private TicketManager ticketManager = new TicketManager();
    private CustomerManager customerManager = new CustomerManager();
    private GameManager gameManager = new GameManager();

    @Test
    void addNoParameters(){
        Ticket tckt = new Ticket();
        assertThrows(TicketException.class, ()->{
            ticketManager.add(tckt);
        });
    }

    @Test
    void addTicketForFakeGameAndCustomer(){
        Ticket tckt = new Ticket();
        tckt.setGame(1000);
        tckt.setCustomer(2000);
        tckt.setStand("Sjever");
        tckt.setPrice(8);
        assertThrows(TicketException.class, ()->{
            ticketManager.add(tckt);
        });
    }
    }
