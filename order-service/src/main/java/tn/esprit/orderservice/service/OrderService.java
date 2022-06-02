package tn.esprit.orderservice.service;

import tn.esprit.orderservice.model.Category;
import tn.esprit.orderservice.model.Order;
import tn.esprit.orderservice.model.User;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Order add(Order order);

    Order update(Order order);

    Order updateStatus(Order order);

    Order getOrder(int orderId);

    List<Order> getOrders();

    List<Order> getOrders(String status);

    List<Order> getUserOrders(int userId);

    Map<String, Float> getStatsPerGov();

    Map<Integer, Double> getStatsPerQuarter();

    Map<Integer, Double> getStatsPerMonth();

    Map<User, Double> getBestCustomer();

    Map<String, List<User>> getClientsFavouriteCategories();

}
