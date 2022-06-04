package tn.esprit.wishlistservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.wishlistservice.model.*;
import tn.esprit.wishlistservice.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wishLists")
public class WishListController {

    @Autowired
    private WishListServiceImpl wishListService;

    @GetMapping("/findAllWishList")
    @ResponseBody
    public List <WishList> findAll() {
      return  wishListService.findAll();
    }

    @GetMapping("/findWishList/{id}")
    @ResponseBody
    WishList findById(@PathVariable("id") int id) {
        return wishListService.findById(id);
    }

    @PostMapping("/create")
    @ResponseBody
    WishList addWishList(@RequestBody WishList wishList) {
        return wishListService.addWishList(wishList);
    }


    @PostMapping("/")
    @ResponseBody
    public WishList addBookToWishList(int idBook, int idwishList) {
        return wishListService.addBookToWishList(idBook, idwishList);
    }

    @DeleteMapping("/")
    @ResponseBody
    public void deleteBookFromBasket(int idwishList, int idBook) {
        wishListService.deleteBookFromWishList(idwishList, idBook);

    }

    @GetMapping("/allwishlist")
    @ResponseBody
    public Map<String, List<Object>> getWishList(int user_id) {
        Map<String, List<Object>> map = new HashMap<>();

        List<Object> wishListArrayList = new ArrayList<>();

        List<WishList> wishLists = wishListService.findAll();

        for (WishList wishList : wishLists) {

            if (wishList.getUser().getId() == user_id)
                wishListArrayList.add(wishList);
        }
        map.put("list", wishListArrayList);
        return map;
    }

}