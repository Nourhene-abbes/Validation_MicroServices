package tn.esprit.orderservice.service;

import tn.esprit.orderservice.model.Book;
import tn.esprit.orderservice.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem orderItem);

    void update(OrderItem orderItem);

    List<OrderItem> getOrderItems();

    List<OrderItem> getOrderItems(int orderId);

    OrderItem getOrderItem(int orderItemId);

    Book getBestBook();

    List<Book> getUnsaledBooks();
}
