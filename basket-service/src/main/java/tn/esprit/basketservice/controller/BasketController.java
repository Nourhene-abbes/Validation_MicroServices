package tn.esprit.basketservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.basketservice.model.Basket;
import tn.esprit.basketservice.model.BasketBook;
import tn.esprit.basketservice.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    @Autowired
    private IBasketService basketService;

    //@Autowired
   // private ImpUserService impUserService;

    //@Autowired
   // private BookServiceImp bookServiceImp;





    @DeleteMapping("/")
    public String deleteBookFromBasket(int idBook, int idBasket) {
        basketService.deleteBasketFromBook(idBook,idBasket);
        return "Book deleted from basket";

    }

    @PostMapping("/")
    public BasketBook addBookToBasket(@RequestBody BasketBook basketBook) {
        return basketService.addBookToBasket(basketBook);
    }

    @PutMapping("/")
    public void updateQuantityBook(@RequestBody BasketBook basketBook) {
        basketService.updateQuantityBook(basketBook);
    }

    @GetMapping ("/findAllBaskets")
    public List<Basket> findAll(){
        return basketService.findAll();
    }


    /*@PostMapping("/create")
    Map<String ,String> createBasket (int id_user) {

        Map<String, String> map = new HashMap<>();
        try {
            Basket basket = new Basket();
            basket.setUser(impUserService.GetUser(id_user));
            basketService.ajouterBasket(basket);
            map.put("message", "create user");
        } catch (Exception e) {
            System.out.println("Utilisateur existe déjà!");
            map.put("message", "Utilisateur existe déjà!");

        }

        return map;
    }*/

    @DeleteMapping("/basket")
    public String deleteBasket(int idBasket) {
        basketService.deleteBasket(idBasket);
        return "Basket deleted";
    }



    @GetMapping("/get")
    HashMap <String, String> returnBasketByUserId (long userId){
        HashMap map = new HashMap();

        try{
            basketService.returnBasketByUserId(userId);
            map.put("state", "Succées");
        } catch (Exception e) {
            map.put("state", "Erreur");

        }
        return map;
    }





}

