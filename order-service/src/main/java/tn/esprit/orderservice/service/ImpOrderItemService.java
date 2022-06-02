package tn.esprit.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.orderservice.model.Book;
import tn.esprit.orderservice.model.OrderItem;
import tn.esprit.orderservice.repository.OrderItemRepository;

import java.util.List;

@Service
public class ImpOrderItemService implements OrderItemService {
    @Autowired
    OrderItemRepository repository;
    @Autowired
    BookService bookService;

    @Transactional
    @Override
    public void add(OrderItem orderItem) {
        System.out.println(orderItem.getBook().getId());
        System.out.println(orderItem.getBook().getTitle());
        Book book = bookService.getBookByID(orderItem.getBook().getId());
        book.setQuantity(book.getQuantity() - orderItem.getQuantity());
        bookService.updateBook(book);
        repository.save(orderItem);
    }

    @Transactional
    @Override
    public void update(OrderItem orderItem) {
        OrderItem oi = repository.getOne(orderItem.getId());
        int diff = oi.getQuantity() - orderItem.getQuantity();
        Book book = bookService.getBookByID(orderItem.getBook().getId());
        book.setQuantity(book.getQuantity() + diff);
        bookService.updateBook(book);
        repository.save(orderItem);
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public List<OrderItem> getOrderItems(int orderId) {
        return repository.findByOrderId(orderId);
    }

    @Transactional
    @Override
    public OrderItem getOrderItem(int orderItemId) {
        return repository.findById(orderItemId).get();
    }

    @Transactional
    @Override
    public List<Book> getUnsaledBooks() {
        return repository.getUnsaledBooks();
    }

    @Transactional
    @Override
    public Book getBestBook() {
        // MAX(nbP) ,name ,bookid
        List<Object[]> list = repository.getBestBook();
        for (Object[] obj : list) {
            return bookService.getBookByID((int)obj[0]);
        }
        return null;
    }
}
