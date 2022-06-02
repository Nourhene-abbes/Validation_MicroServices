package tn.esprit.orderservice.service;

import com.stripe.model.Customer;

public interface PaymentService {

    Customer getCustomer(String email);

    String chargeCustomer(String customer_id, int amount);

    boolean checkValidCard(String name, String email, String numCard, int ExpMonth, int ExpYear);
}
