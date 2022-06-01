package tn.esprit.wishlistservice.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class OnLineWishList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name="user_id",updatable = false, unique = true)
    private User user;


    @ManyToMany @JoinTable (name = "OnLinebook_wishList", joinColumns = @JoinColumn
            (name = "onLine_wishList_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn
                    (name = "onLine_book_id", referencedColumnName = " id "))
    private Set<OnlineBook> onlineBooks;


    public OnLineWishList() {
    }

    public OnLineWishList(int id, User user, Set<OnlineBook> onlineBooks) {
        this.id = id;
        this.user = user;
        this.onlineBooks = onlineBooks;
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

    public Set<OnlineBook> getOnlineBooks() {
        return onlineBooks;
    }

    public void setOnlineBooks(Set<OnlineBook> onlineBooks) {
        this.onlineBooks = onlineBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnLineWishList that = (OnLineWishList) o;
        return id == that.id && Objects.equals(user, that.user) && Objects.equals(onlineBooks, that.onlineBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, onlineBooks);
    }

    @Override
    public String toString() {
        return "OnLineWishList{" +
                "id=" + id +
                ", user=" + user +
                ", onlineBooks=" + onlineBooks +
                '}';
    }
}
