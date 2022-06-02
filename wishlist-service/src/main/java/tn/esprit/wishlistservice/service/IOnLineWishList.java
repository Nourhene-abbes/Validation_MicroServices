package tn.esprit.wishlistservice.service;

import tn.esprit.wishlistservice.model.OnLineWishList;
import tn.esprit.wishlistservice.model.OnlineBook;

import java.util.List;

public interface IOnLineWishList <T>{
    List<T> findAll();

    OnLineWishList findById(int id);

    OnLineWishList addWishList(OnLineWishList wishList);

    //OnLineWishList addBookToWishList(int bookId, int wishlistId);


    //void deleteBookFromWishList(int wishListId, int bookId);

    List <OnlineBook> countBooksInWishList(int idwishList);

}
