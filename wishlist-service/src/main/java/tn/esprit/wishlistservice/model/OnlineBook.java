package tn.esprit.wishlistservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "online_book")
public class OnlineBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private Book book;
    private String url;

    public OnlineBook() {

    }

    public OnlineBook(int id, Book book, String url) {
        this.id = id;
        this.book = book;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OnlineBook that = (OnlineBook) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "OnlineBook{" +
                "id=" + id +
                ", book=" + book +
                ", url='" + url + '\'' +
                '}';
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "onlineBooks")
    private List<OnLineWishList> onLineWishLists;

}
