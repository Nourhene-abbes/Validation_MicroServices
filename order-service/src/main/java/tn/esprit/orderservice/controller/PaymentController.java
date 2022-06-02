package tn.esprit.orderservice.controller;

import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.orderservice.model.Response;
import tn.esprit.orderservice.service.PaymentService;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/verifyCredentials")
    ResponseEntity verifyCredentials(@RequestParam String name, @RequestParam String email, @RequestParam String numCard, @RequestParam int expMonth, @RequestParam int expYear) {
        if (paymentService.checkValidCard(name, email, numCard, expMonth, expYear)) {
            return Response.getResponseEntity("true");
        } else {
            return Response.getResponseEntity("false");
        }
    }

    @PostMapping("/verifyBalance")
    ResponseEntity verifyBalance(@RequestParam String email, @RequestParam float amount) {
        Customer c = paymentService.getCustomer(email);
        if (c == null) {
            return Response.getResponseEntity("Customer Not Found");
        } else {
            if (c.getBalance() >= amount)
                return Response.getResponseEntity("true");
            else
                return Response.getResponseEntity("false");
        }
    }

}
