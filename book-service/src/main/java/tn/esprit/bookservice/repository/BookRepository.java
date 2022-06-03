package tn.esprit.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.bookservice.model.Book;



@Repository("BookRepository")
public interface BookRepository extends JpaRepository<Book, Integer> {

}


