package tn.esprit.wishlistservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Verification_Code {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String code;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;
    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }
    public Verification_Code() {
        super();
        // TODO Auto-generated constructor stub
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public User getUser_id() {
        return user_id;
    }



}