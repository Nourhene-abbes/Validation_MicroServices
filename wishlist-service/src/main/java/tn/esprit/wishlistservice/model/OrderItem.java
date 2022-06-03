package tn.esprit.wishlistservice.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;
    @Column
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + order.getId() +
                ", quantity=" + quantity +
                ", bookId=" + book.getId() +
                '}';
    }

    public static String afficherTab(List<OrderItem> tab) {
        String res = "";
        for (int i = 0; i < tab.size(); i++)
            res += "\t" + tab.get(i).toString() + "\n";
        return res;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
