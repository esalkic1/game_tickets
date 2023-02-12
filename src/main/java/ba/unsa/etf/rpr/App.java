package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CustomerManager;
import ba.unsa.etf.rpr.business.GameManager;
import ba.unsa.etf.rpr.business.TicketManager;
import ba.unsa.etf.rpr.dao.CustomerDao;
import ba.unsa.etf.rpr.dao.CustomerDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Customer;
import ba.unsa.etf.rpr.domain.Game;
import ba.unsa.etf.rpr.domain.Ticket;
import ba.unsa.etf.rpr.exceptions.TicketException;

import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Option addTicket = new Option("t", "add-ticket", false, "Adding new ticket to game_tickets database");
    private static final Option getGames = new Option("getG", "get-games", false, "Printing all games from game_tickets database");
    private static final Option getCustomers = new Option("getC", "get-customers", false, "Printing all games from game_tickets database");
    private static final Option getTickets = new Option("getT", "get-tickets", false, "Printing all tickets from game_tickets database");
    private static final Option gameDefinition = new Option(null, "game", false, "Defining game for next added ticket");
    private static final Option customerDefinition = new Option(null, "customer", false, "Defining customer for next added ticket");
    private static final Option priceDefinition = new Option(null, "price", false, "Defining price for next added ticket");
    private static final Option standDefinition= new Option(null, "stand", false, "Defining stand for next added game");


    public static void printFormattedOptions(Options options){
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);
        helpFormatter.printUsage(printWriter, 150, "java -jar game_tickets.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);
        printWriter.close();
    }

    public static Options addOptions(){
        Options options = new Options();
        options.addOption(addTicket);
        options.addOption(getGames);
        options.addOption(getCustomers);
        options.addOption(getTickets);
        options.addOption(gameDefinition);
        options.addOption(customerDefinition);
        options.addOption(priceDefinition);
        options.addOption(standDefinition);
        return options;
    }

    public static Game searchThroughGames(List<Game> listOfGames, String gameOpponent){
        Game game = null;
        game = listOfGames.stream().filter(gm -> gm.getOpponent().toLowerCase().equals(gameOpponent.toLowerCase())).findAny().get();
        return game;
    }

    public static Customer searchThroughCustomers(List<Customer> listOfCustomers, String customerUsername){
        Customer customer = null;
        customer = listOfCustomers.stream().filter(cust->cust.getUsername().toLowerCase().equals(customerUsername.toLowerCase())).findAny().get();
        return customer;
    }

    public static void main( String[] args ) throws Exception {
        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine cl = commandLineParser.parse(options, args);


        if((cl.hasOption(addTicket.getOpt()) || cl.hasOption(addTicket.getLongOpt())) && cl.hasOption((gameDefinition.getLongOpt())) && cl.hasOption(customerDefinition.getLongOpt()) && cl.hasOption(priceDefinition.getLongOpt()) && cl.hasOption(standDefinition.getLongOpt())){
            TicketManager ticketManager = new TicketManager();
            GameManager gameManager = new GameManager();
            CustomerManager customerManager = new CustomerManager();
            Game game = null;
            try {
                game = searchThroughGames(gameManager.getAll(), cl.getArgList().get(1));
            }catch(Exception e) {
                System.out.println("There is no game in the list! Try again.");
                System.exit(1);
            }
            Customer customer = null;
            try {
                customer = searchThroughCustomers(customerManager.getAll(), cl.getArgList().get(2));
            }catch(Exception e) {
                System.out.println("There is no customer in the list! Try again.");
                System.exit(1);
            }
            Ticket ticket = new Ticket();
            ticket.setGame(game.getId());
            ticket.setCustomer(customer.getId());
            ticket.setPrice(Integer.parseInt(cl.getArgList().get(3)));
            ticket.setStand(cl.getArgList().get(4));
            ticketManager.add(ticket);
            System.out.println("You successfully added ticket to database!");
//                break;
        } else if(cl.hasOption(getTickets.getOpt()) || cl.hasOption(getTickets.getLongOpt())){
            TicketManager ticketManager = new TicketManager();
            ticketManager.getAll().forEach(t -> System.out.println(t.getStand() + " " + t.getPrice()));
//                break;
        } else if(cl.hasOption(getGames.getOpt()) || cl.hasOption(getGames.getLongOpt())){
            GameManager gameManager = new GameManager();
            gameManager.getAll().forEach(gm->System.out.println(gm.getOpponent() + " " + gm.getCompetition()));
        }
        else{
            printFormattedOptions(options);
            System.exit(-1);
        }

        /*CustomerDao dao = new CustomerDaoSQLImpl();
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
        } catch (TicketException e) {
            throw new RuntimeException(e);
        }*/
    }
}
