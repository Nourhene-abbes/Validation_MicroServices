package tn.esprit.basketservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.basketservice.model.BasketBook;

import javax.transaction.Transactional;

@Repository
public interface BasketBookRepo  extends JpaRepository<BasketBook, Integer> {

    @Transactional
    @Modifying
    @Query("delete from BasketBook b where b.book.id= :idBook and b.basket.id= :idBasket")
     void removeBookFromBasket(int idBook, int idBasket);

    @Transactional
    @Modifying
    @Query("update BasketBook bb set bb.basketBookPk.quantity = :quantity  " +
            "where bb.basketBookPk.idBook= :idBook and " +
            "bb.basketBookPk.idBasket= :idBasket")
     void updateQuantityBook(int idBook, int idBasket, int quantity );






}
