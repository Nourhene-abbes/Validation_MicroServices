package tn.esprit.basketservice.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class BasketBook  implements Serializable {

    @EmbeddedId
    private BasketBookPk basketBookPk;


    @ManyToOne
    @JoinColumn(name = "idBasket", referencedColumnName = "id", insertable = false, updatable = false)
    private Basket basket;


    @ManyToOne
    @JoinColumn(name = "idBook", referencedColumnName = "id", insertable = false, updatable = false)
    private Book book;



    public BasketBookPk getBasketBookPk() {
        return basketBookPk;
    }

    public void setBasketBookPk(BasketBookPk basketBookPk) {
        this.basketBookPk = basketBookPk;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketBook that = (BasketBook) o;
        return Objects.equals(basketBookPk, that.basketBookPk) && Objects.equals(basket, that.basket) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketBookPk, basket, book);
    }
}
