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
    private String password;
    private String username;
    private int isAdmin;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

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
        return id == customer.id && numberOfTickets == customer.numberOfTickets && isAdmin == customer.isAdmin && Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname) && password.equals(customer.password) && username.equals(customer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, numberOfTickets, password, username, isAdmin);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
