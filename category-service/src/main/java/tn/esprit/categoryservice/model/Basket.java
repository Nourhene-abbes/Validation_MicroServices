package tn.esprit.categoryservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Basket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name="user_id",updatable = false, unique = true)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy="basket")
    private List <BasketBook> basketBooks;

    public Basket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BasketBook> getBasketBooks() {
        return basketBooks;
    }

    public void setBasketBooks(List<BasketBook> basketBooks) {
        this.basketBooks = basketBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return id == basket.id && Objects.equals(user, basket.user) && Objects.equals(basketBooks, basket.basketBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, basketBooks);
    }
}
