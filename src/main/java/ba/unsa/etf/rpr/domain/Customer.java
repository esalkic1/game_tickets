package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Ticket customer information
 *
 * @author Emir Salkic
 */
public class Customer implements Idable{
    private int id;
    private String name;
    private String surname;
    private int numberOfTickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && numberOfTickets == customer.numberOfTickets && name.equals(customer.name) && surname.equals(customer.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, numberOfTickets);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }
}
