package tn.esprit.bookservice.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BasketBookPk  implements Serializable {


    private int idBook;
    private int idBasket;
    private int quantity;

    public BasketBookPk() {
    }

    public BasketBookPk(int idBook, int idBasket, int quantity) {
        this.idBook = idBook;
        this.idBasket = idBasket;
        this.quantity = quantity;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public int getIdBasket() {
        return idBasket;
    }

    public void setIdBasket(int idBasket) {
        this.idBasket = idBasket;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketBookPk that = (BasketBookPk) o;
        return idBook == that.idBook && idBasket == that.idBasket && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, idBasket, quantity);
    }
}
