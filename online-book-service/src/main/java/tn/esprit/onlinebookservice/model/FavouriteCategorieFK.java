package tn.esprit.wishlistservice.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FavouriteCategorieFK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long user_id;
    private Long category_id;

	public FavouriteCategorieFK() {
	}

	public FavouriteCategorieFK(Long user_id, Long category_id) {
		this.user_id = user_id;
		this.category_id = category_id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	@Override
	public String toString() {
		return "FavouriteCategorieFK{" +
				"user_id=" + user_id +
				", category_id=" + category_id +
				'}';
	}
}
