package tn.esprit.BookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.BookStore.model.OrderItem;
import tn.esprit.BookStore.service.OrderItemService;

import java.util.List;

@RequestMapping("api/orderItem")
@RestController
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;


    @GetMapping("/getAll")
    List<OrderItem> all() {
        return orderItemService.getOrderItems();
    }

    @GetMapping("/getAll/{id}")
    List<OrderItem> all(@PathVariable Integer id) {
        return orderItemService.getOrderItems(id);
    }

    @GetMapping("/getOne/{id}")
    OrderItem getOne(@PathVariable Integer id) {
        return orderItemService.getOrderItem(id);
    }
}