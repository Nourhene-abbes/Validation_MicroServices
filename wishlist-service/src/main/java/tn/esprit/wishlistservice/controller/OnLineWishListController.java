package tn.esprit.wishlistservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.wishlistservice.model.OnLineWishList;
import tn.esprit.wishlistservice.model.WishList;
import tn.esprit.wishlistservice.service.IOnLineWishList;

@RestController
@RequestMapping("/onLineWishLists")
public class OnLineWishListController {


    @Autowired
    IOnLineWishList iOnLineWishList;

    @GetMapping("/findAllWishList")
    public String findAll() {
        System.out.println(iOnLineWishList.findAll().toString());
        return "allWishList";
    }


    @GetMapping("/findWishList")
    OnLineWishList findById(int id) {
        return iOnLineWishList.findById(id);
    }

    @PostMapping("/create")
    OnLineWishList addWishList(@RequestBody OnLineWishList wishList) {
        return iOnLineWishList.addWishList(wishList);
    }

    /*
    @PostMapping("/")
    public OnLineWishList addBookToWishList(int idBook, int idwishList) {
        return iOnLineWishList.addBookToWishList(idBook, idwishList);
    }


    @DeleteMapping("/")
    public void deleteBookFromBasket(int idwishList, int idBook) {
        iOnLineWishList.deleteBookFromWishList(idwishList, idBook);
    }
    */

}
