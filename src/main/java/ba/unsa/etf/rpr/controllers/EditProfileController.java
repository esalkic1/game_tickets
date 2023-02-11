package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.dao.AbstractDao;
import ba.unsa.etf.rpr.domain.Customer;
import javafx.event.ActionEvent;

public class EditProfileController {

    Customer customer;
    public EditProfileController(Customer cust){
        this.customer = cust;
    }

    public void EditCustomerBtnClick(ActionEvent actionEvent){

    }
}
