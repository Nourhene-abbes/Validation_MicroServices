package tn.esprit.wishlistservice.service;


import tn.esprit.wishlistservice.model.BasketBook;
import tn.esprit.wishlistservice.model.Book;
import tn.esprit.wishlistservice.model.WishList;

import java.util.List;

public interface IWishListService <T>{

    List<T> findAll();

    WishList findById(int id);

    WishList addWishList(WishList wishList);

    //WishList addBookToWishList(int bookId, int wishlistId);


     //void deleteBookFromWishList(int wishListId, int bookId);


    int countBestBookInWishList(Book bestBook);
}
