package tn.esprit.basketservice.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class FavoriteCategorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private FavouriteCategorieFK FavouriteCategorieFK;



	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable=false, updatable=false)
	private User User;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "id", insertable=false, updatable=false)
	private Category Category;





	public FavouriteCategorieFK getFavouriteCategorieFK() {
		return FavouriteCategorieFK;
	}

	public void setFavouriteCategorieFK(FavouriteCategorieFK favouriteCategorieFK) {
		FavouriteCategorieFK = favouriteCategorieFK;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FavoriteCategorie(FavouriteCategorieFK favouriteCategorieFK,
			User user, Category category) {
		super();
		FavouriteCategorieFK = favouriteCategorieFK;
		User = user;
		Category = category;
	}

	public FavoriteCategorie() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FavoriteCategorie [FavouriteCategorieFK=" + FavouriteCategorieFK + ", User=" + User + ", Category="
				+ Category + "]";
	}


}
