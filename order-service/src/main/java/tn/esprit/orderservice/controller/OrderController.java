package tn.esprit.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.orderservice.model.Order;
import tn.esprit.orderservice.model.Response;
import tn.esprit.orderservice.service.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("api/order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/add")
    ResponseEntity add(@RequestBody Order order) {
        if (orderService.add(order) != null)
            return Response.getResponseEntity("true");
        else
            return Response.getResponseEntity("false");
    }

    @PostMapping("/update")
    ResponseEntity update(@RequestBody Order order) {
        try {
            orderService.update(order);
            return Response.getResponseEntity("true");
        } catch (Exception ex) {
            return Response.getResponseEntity("false");
        }

    }

    @PostMapping("/updateStatus")
    ResponseEntity updateStatus(@RequestBody Order order) {
        if (orderService.getOrder(order.getId()) == null) {
            return Response.getResponseEntity("false");
        } else {
            orderService.updateStatus(order);
            return Response.getResponseEntity("true");
        }
    }

    @GetMapping("/get/{id}")
    ResponseEntity getOrder(@PathVariable Integer id) {
        Order order = orderService.getOrder(id);
        if (order != null)
            return Response.getResponseEntity("true", order);
        else
            return Response.getResponseEntity("false");
    }

    @GetMapping("/getAll")
    ResponseEntity all() {
        List<Order> res = orderService.getOrders();
        if (res != null) {
            return Response.getResponseEntity("true", res);
        } else
            return Response.getResponseEntity("false");
    }

    @GetMapping("/getAllByStatus/{status}")
    ResponseEntity all(@PathVariable String status) {
        List<Order> res = orderService.getOrders(status);
        if (res != null)
            return Response.getResponseEntity("true", res);
        else
            return Response.getResponseEntity("false");
    }

    @GetMapping("/getUserOrders/{user_id}")
    ResponseEntity allForUser(@PathVariable Integer user_id) {
        List<Order> res = orderService.getUserOrders(user_id);
        if (res != null)
            return Response.getResponseEntity("true", res);
        else
            return Response.getResponseEntity("false");

    }

    @GetMapping("/getUserOrders/{user_id}/{status}")
    ResponseEntity allForUser(@PathVariable Integer user_id, @PathVariable String status) {
        List<Order> res = orderService.getUserOrders(user_id).stream().filter(p -> p.getStatus().equals(status)).collect(Collectors.toList());
        if (res != null)
            return Response.getResponseEntity("true", res);
        else
            return Response.getResponseEntity("false");
    }

    @GetMapping("/getStat/{filter}")
    ResponseEntity getStat(@PathVariable String filter) {
        if (filter.equals("month")) {
            return Response.getResponseEntity("true", orderService.getStatsPerMonth());
        }
        if (filter.equals("quarter")) {
            return Response.getResponseEntity("true", orderService.getStatsPerQuarter());
        }
        if (filter.equals("gov")) {
            return Response.getResponseEntity("true", orderService.getStatsPerGov());
        }
        return Response.getResponseEntity("false");
    }

    @GetMapping("/getBestCustomer")
    ResponseEntity getBestCustomer() {
        return Response.getResponseEntity("true", orderService.getBestCustomer());
    }

    @GetMapping("/getClientMatching")
    ResponseEntity getClientMatching() {
        return Response.getResponseEntity("true", orderService.getClientsFavouriteCategories());
    }
}