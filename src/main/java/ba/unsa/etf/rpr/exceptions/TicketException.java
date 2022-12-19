package ba.unsa.etf.rpr.exceptions;

public class TicketException extends Exception{
    public TicketException(String msg, Exception reason){
        super(msg, reason);
    }

    public TicketException(String msg){
        super(msg);
    }
}
