package tn.esprit.wishlistservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.wishlistservice.model.Basket;
import tn.esprit.wishlistservice.model.Book;
import tn.esprit.wishlistservice.model.User;
import tn.esprit.wishlistservice.model.WishList;
import tn.esprit.wishlistservice.repository.WishListRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WishListServiceImpl implements IWishListService<WishList> {
    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<WishList> findAll() {
        return wishListRepository.findAll();
    }

    @Override
    public WishList findById(int id) {
        return wishListRepository.findById(id).get();
    }

    @Override
    public WishList addWishList(WishList wishList) {
        User user = restTemplate.getForObject("http://user-service/users/informationUser/" + wishList.getUser().getId(), User.class);

        wishList.setUser(user);
        return wishListRepository.save(wishList);
    }


    @Override
    public WishList addBookToWishList(int bookId, int wishListId) {
        WishList wishList1 = wishListRepository.findById(wishListId).get();
        Book book = restTemplate.getForObject("http://book-service/api/book/getBook/" + bookId, Book.class);

        wishList1.getBooks().add(book);
        return wishListRepository.save(wishList1);
    }


    @Override
    public void deleteBookFromWishList(int wishListId, int bookId) {
        try{
            WishList wishList = wishListRepository.findById(wishListId).get();
            Book book = restTemplate.getForObject("http://book-service/api/book/getBook/" + bookId, Book.class);
            book = wishListRepository.getBookById(bookId);
           wishList.getBooks().remove(book);
            wishListRepository.save(wishList);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int countBestBookInWishList(Book bestBook) {
        int count = 0;
        try {
            System.out.println("The best Book is " +bestBook);

            for (WishList wishList : wishListRepository.findAll()) {
                for (Book book : wishList.getBooks()) {
                    if (book.getId() == bestBook.getId()) {
                        count++;
                    }
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

        }
        return count;
    }
}
