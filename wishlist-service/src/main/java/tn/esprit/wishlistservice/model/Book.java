package tn.esprit.wishlistservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "book")
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    private String title;

    private double price;

    private String pubHouse;

    private String summary;

    private Date releaseDate;

    private int quantity;

    private String status;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String image;

    private int nbPage;

    private String Authors;

    private boolean isDiscounted = false;

    @JsonIgnore
    @OneToMany(mappedBy="book")
    private List<BasketBook> basketBook;

    public Book(int id) {
        this.id = id;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<WishList> wishLists;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPubHouse() {
		return pubHouse;
	}

	public void setPubHouse(String pubHouse) {
		this.pubHouse = pubHouse;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public String getAuthors() {
		return Authors;
	}

	public void setAuthors(String authors) {
		Authors = authors;
	}

	public boolean isDiscounted() {
		return isDiscounted;
	}

	public void setDiscounted(boolean isDiscounted) {
		this.isDiscounted = isDiscounted;
	}

	public Book(String title, double price, String pubHouse, String summary, Date releaseDate, int quantity,
			String status, Category category, String image, int nbPage, String authors, boolean isDiscounted) {
		super();
		this.title = title;
		this.price = price;
		this.pubHouse = pubHouse;
		this.summary = summary;
		this.releaseDate = releaseDate;
		this.quantity = quantity;
		this.status = status;
		this.category = category;
		this.image = image;
		this.nbPage = nbPage;
		Authors = authors;
		this.isDiscounted = isDiscounted;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", price=" + price + ", pubHouse=" + pubHouse + ", summary="
				+ summary + ", releaseDate=" + releaseDate + ", quantity=" + quantity + ", status=" + status
				+ ", category=" + category + ", image=" + image + ", nbPage=" + nbPage + ", Authors=" + Authors
				+ ", isDiscounted=" + isDiscounted + "]";
	}


}
