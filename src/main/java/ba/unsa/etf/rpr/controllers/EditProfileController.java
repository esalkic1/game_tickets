package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Customer;

public class EditProfileController {

    Customer customer;
    public EditProfileController(Customer cust){
        this.customer = cust;
    }
}
