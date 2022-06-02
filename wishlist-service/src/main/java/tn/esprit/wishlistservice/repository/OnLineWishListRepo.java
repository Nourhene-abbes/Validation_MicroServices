package tn.esprit.wishlistservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.wishlistservice.model.OnLineWishList;

@Repository
public interface OnLineWishListRepo extends JpaRepository<OnLineWishList, Integer> {
}
