package tn.esprit.BookStore.service;

import tn.esprit.BookStore.model.Book;
import tn.esprit.BookStore.model.OrderItem;

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
