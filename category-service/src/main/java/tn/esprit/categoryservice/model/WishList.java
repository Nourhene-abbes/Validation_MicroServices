package tn.esprit.categoryservice.model;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Objects;
import java.util.Set;

@Entity
public class WishList implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne
    @JoinColumn(name="user_id",updatable = false, unique = true)
    private User user;


    @ManyToMany @JoinTable (name = "book_wishList", joinColumns = @JoinColumn
            (name = "wishList_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn
                    (name = "book_id", referencedColumnName = " id "))
    private Set<Book> books;

    public WishList(int id, User user, Set<Book> books) {
        this.id = id;
        this.user = user;
        this.books = books;
    }

    public WishList() {
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

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", user=" + user +
                ", books=" + books +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishList wishList = (WishList) o;
        return Objects.equals(id, wishList.id) && Objects.equals(user, wishList.user) && Objects.equals(books, wishList.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, books);
    }

}

