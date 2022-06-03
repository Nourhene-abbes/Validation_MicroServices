package tn.esprit.basketservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.basketservice.model.Basket;
import tn.esprit.basketservice.model.BasketBook;
import tn.esprit.basketservice.repository.BasketBookRepo;
import tn.esprit.basketservice.repository.BasketRepository;

import java.util.List;

@Service
public class BasketServiceImpl implements IBasketService<Basket>{
    @Autowired
    BasketRepository basketRepository;

    @Autowired
    private BasketBookRepo basketBookRepo;


    @Override
    public void deleteBasketFromBook(int idBook, int idBasket) {
        basketBookRepo.removeBookFromBasket(idBook,idBasket);

    }


    @Override
    public BasketBook addBookToBasket(BasketBook basketBook) {
        return basketBookRepo.save(basketBook);
    }


    @Override
    public void updateQuantityBook(BasketBook basketBook) {
        System.out.println(basketBook);
        basketBookRepo.updateQuantityBook(basketBook.getBasketBookPk().getIdBook(),basketBook.getBasketBookPk().getIdBasket(),
                basketBook.getBasketBookPk().getQuantity());
    }



    @Override
    public List<Basket> findAll() {
        return basketRepository.findAll();
    }


    @Override
    public void deleteBasket(int idBasket) {
        basketRepository.deleteById(idBasket);
    }

    @Override
    public Basket ajouterBasket(Basket basket) {
        return basketRepository.save(basket);
    }


    @Override
    public void returnBasketByUserId(long userId) {

    }


}
