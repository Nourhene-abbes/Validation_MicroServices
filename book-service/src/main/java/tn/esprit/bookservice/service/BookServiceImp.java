package tn.esprit.bookservice.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.bookservice.model.Book;
import tn.esprit.bookservice.repository.BookRepository;


@Service
public class BookServiceImp implements BookService {

    @Autowired
    BookRepository bookRepository;
    

    @Override
    public Book addBook(Book b) {
        Book res = bookRepository.save(b);
        return res;
    }

    @Override
    public void deleteBook(Book b) {
        bookRepository.delete(b);
    }

    @Override
    public void updateBook(Book b) {
        bookRepository.save(b);
    }

    @Override
    public ArrayList<Book> getBookList() {
        return (ArrayList<Book>) bookRepository.findAll();
    }

    @Override
    public Book getBook(Book b) {
        return bookRepository.findById(b.getId()).get();
    }

    @Transactional
    @Override
    public Book getBookByID(int id) {
        System.out.println("***************************"+id);
        Optional<Book> op = bookRepository.findById(id);
        if (op.isPresent())
            return op.get();
        return null;
    }

    @Override
    public int getQuantity(int id) {

        return bookRepository.findById(id).get().getQuantity();
    }

    @Override
    public void setQuantity(int id, int newQ) {
       bookRepository.findById(id).get().setQuantity(newQ);
    }
}