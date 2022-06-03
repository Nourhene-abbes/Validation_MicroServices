package tn.esprit.basketservice.service;

import tn.esprit.basketservice.model.Basket;
import tn.esprit.basketservice.model.BasketBook;

import java.util.List;

public interface IBasketService<T>{

    List<T> findAll();

    void deleteBasket (int idBasket);
    void deleteBasketFromBook (int idBook, int idBasket);
    BasketBook addBookToBasket(BasketBook basketBook);
    void updateQuantityBook (BasketBook basketBook);
    Basket ajouterBasket(Basket basket);
    void returnBasketByUserId (long userId);






}
