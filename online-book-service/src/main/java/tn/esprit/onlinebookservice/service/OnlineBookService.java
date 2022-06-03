package tn.esprit.onlinebookservice.service;



import java.util.ArrayList;

import tn.esprit.onlinebookservice.model.OnlineBook;

public interface OnlineBookService {
    OnlineBook addBook(OnlineBook b);

    void deleteBook(OnlineBook b);

    void updateBook(OnlineBook b);

    ArrayList<OnlineBook> getBookList();

    OnlineBook getBook(OnlineBook b);

    OnlineBook getBookByID(int id);

    int getQuantity(int id);

    void setOnlineQuantity(int id, int newQ);
}
