package tn.esprit.onlinebookservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.onlinebookservice.model.OnlineBook;

@Repository
public interface OnlineBookRepository extends JpaRepository<OnlineBook, Integer> {

    @Query(value = "select b.quantity from Book b where b.id = :id")
    int getQuantity(@Param("id") int id);




}
