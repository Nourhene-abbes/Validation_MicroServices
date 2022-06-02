package tn.esprit.wishlistservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.wishlistservice.model.OnLineWishList;
import tn.esprit.wishlistservice.model.OnlineBook;
import tn.esprit.wishlistservice.repository.OnLineWishListRepo;


import java.util.List;


@Service
public class OnLineWishListImpl implements IOnLineWishList {

    @Autowired
    OnLineWishListRepo onLineWishListRepo;

    //@Autowired
    //OnlineBookRepository bookRepository;

    @Override
    public List findAll() {
        return onLineWishListRepo.findAll();
    }

    @Override
    public OnLineWishList findById(int id) {
        return onLineWishListRepo.findById(id).get();
    }

    @Override
    public OnLineWishList addWishList(OnLineWishList wishList) {
        return onLineWishListRepo.save(wishList);
    }

    /*
    @Override
    public OnLineWishList addBookToWishList(int bookId, int wishlistId) {
        OnLineWishList wishList1 = onLineWishListRepo.findById(wishlistId).get();
        OnlineBook book = bookRepository.findById(bookId).get();

        wishList1.getOnlineBooks().add(book);
        return onLineWishListRepo.save(wishList1);
    }

    @Override
    public void deleteBookFromWishList(int wishListId, int bookId) {
        try{
            OnLineWishList wishList = onLineWishListRepo.findById(wishListId).get();
            OnlineBook book = bookRepository.findById(bookId).get();
            wishList.getOnlineBooks().remove(book);
            onLineWishListRepo.save(wishList);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    */
    @Override
    public List<OnlineBook> countBooksInWishList(int idwishList) {
        return null;
    }
}
