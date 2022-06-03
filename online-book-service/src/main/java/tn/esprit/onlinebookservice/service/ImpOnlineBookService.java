package tn.esprit.onlinebookservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.onlinebookservice.model.OnlineBook;
import tn.esprit.onlinebookservice.repository.OnlineBookRepository;

import java.util.ArrayList;

@Service
public class ImpOnlineBookService implements OnlineBookService {
    @Autowired
    OnlineBookRepository onlineBookRepository;

    @Override
    public OnlineBook addBook(OnlineBook b) {
        return onlineBookRepository.save(b);
    }

    @Override
    public void deleteBook(OnlineBook b) {
        onlineBookRepository.delete(b);
    }

    @Override
    public void updateBook(OnlineBook b) {
        onlineBookRepository.save(b);
    }

    @Override
    public ArrayList<OnlineBook> getBookList() {
        return (ArrayList<OnlineBook>) onlineBookRepository.findAll();
    }

    @Override
    public OnlineBook getBook(OnlineBook b) {
        return onlineBookRepository.findById(b.getId()).get();
    }

    @Override
    public OnlineBook getBookByID(int id) {
        return onlineBookRepository.findById(id).get();
    }
    @Override
    public int getQuantity(int id) {

        return onlineBookRepository.findById(id).get().getBook().getQuantity();
    }

    @Override
    public void setOnlineQuantity(int id, int newQ) {
        onlineBookRepository.findById(id).get().getBook().setQuantity(newQ);
    }
}
