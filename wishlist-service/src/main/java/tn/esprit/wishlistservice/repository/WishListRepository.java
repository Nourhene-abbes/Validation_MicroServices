package tn.esprit.wishlistservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.wishlistservice.model.Book;
import tn.esprit.wishlistservice.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {

    @Query("select b from Basket b where b.user.id=:user_id")
    WishList getWishListByUserId(@Param("user_id") long userId);

    @Query("select bk from Book bk where bk.id=:bookId")
    Book getBookById(@Param("bookId") int bookId);

}
