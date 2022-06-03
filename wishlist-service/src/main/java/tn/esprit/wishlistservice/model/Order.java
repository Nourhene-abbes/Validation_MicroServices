package tn.esprit.wishlistservice.model;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> items;

    private float totalPrice;

    private String paymentID;

    private String orderDate;

    private String address;

    private int zipCode;

    private String numTel;

    private String status;

    public Order(int id) {
        this.id = id;
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


	public List<OrderItem> getItems() {
		return items;
	}


	public void setItems(List<OrderItem> items) {
		this.items = items;
	}


	public float getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getPaymentID() {
		return paymentID;
	}


	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getZipCode() {
		return zipCode;
	}


	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}


	public String getNumTel() {
		return numTel;
	}


	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", items:`\n" + items +
                ", totalPrice=" + totalPrice +
                ", paymentID='" + paymentID + '\'' +
                ", orderDate=" + orderDate +
                ", address=" + address +
                ", zipcode=" + zipCode +
                ", numTel=" + numTel +
                ", status='" + status + '\'' +
                '}';
    }

}
