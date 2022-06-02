package tn.esprit.BookStore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.BookStore.model.Order;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "SELECT o from Order o  where o.user.id = :user_id")
    List<Order> findAllByUser(@Param("user_id") Integer user_id);

    @Query(value = "SELECT o FROM Order o where o.status = :status")
    List<Order> findAllByStatus(@Param("status") String status);

    @Modifying
    @Query(value = "UPDATE Order o set o.status = :status where o.id = :id")
    void updateStatus(@Param("status") String status, @Param("id") Integer id);

    //Stats

//    @Query(value = "select CONCAT(z.governorate,':',SUM(o.totalPrice)) as sum " +
//            "from Order o, ZipCodesTN z " +
//            "where z.code like  FUNCTION('CONCAT',function('SUBSTR',o.zipCode,1,2),'__' ) " +
//            "GROUP BY z.governorate")
//    ArrayList<String> getInputsByGovernorate();

    @Query(value = "select FUNCTION('QUARTER',o.orderDate),SUM(o.totalPrice)" +
            "as sum from Order o where FUNCTION('YEAR',current_date) = FUNCTION('YEAR',o.orderDate)" +
            "group by FUNCTION('QUARTER',o.orderDate)")
    List<Object[]> getInputsByQuarter();


    @Query(value = "select FUNCTION('MONTH',o.orderDate) ,SUM(o.totalPrice)" +
            "as sum from Order o where FUNCTION('YEAR',current_date) = FUNCTION('YEAR',o.orderDate)" +
            "group by FUNCTION('MONTH',o.orderDate)")
    List<Object[]> getInputsByMonth();



    @Query(value = "select o.user.id as user_id ,SUM(o.totalPrice) as somme " +
            "from Order o " +
            "GROUP BY user_id " +
            "ORDER BY somme DESC")
    List<Object[]> getBestCustomer();

    @Query(value = "select c.name , o.user.id, SUM(oi.quantity) as nb " +
            "from Order o join OrderItem oi on o.id = oi.order.id " +
            "JOIN Book b on b.id = oi.book.id " +
            "JOIN Category c on c.id = b.category.id " +
            "GROUP BY c.name, o.user.id")
    List<Object[]> getClientsFavouriteCategories();


    @Query(value = "select o.id,u.full_name from Order o JOIN User u on o.user.id = u.id")
    List<Object[]> getTest();
}
