package tn.esprit.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.orderservice.model.Book;
import tn.esprit.orderservice.model.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {


    List<OrderItem> findByOrderId(@Param("id") Integer id);

    @Query(value = "select o.book.id as book_id , SUM(o.quantity) as somme " +
            "from OrderItem  o" +
            " group by book_id " +
            "ORDER BY somme DESC")
    List<Object[]> getBestBook ();

    @Query(value = "select b from Book b left join OrderItem oi on b.id = oi.book.id")
    List<Book> getUnsaledBooks ();
}
