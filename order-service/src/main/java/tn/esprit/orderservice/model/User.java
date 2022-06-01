package tn.esprit.orderservice.model;

import java.util.Set;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
private String full_name;
private String email;
private int telephone;
private String login;
private String password;
private int role;
private String address;
private int zipCode;
@OneToMany(mappedBy = "User")
private Set<FavoriteCategorie> Fcu;


    public User(String full_name, String email, int telephone, String login, String password, int role, String address,
		int zipCode) {
	super();
	this.full_name = full_name;
	this.email = email;
	this.telephone = telephone;
	this.login = login;
	this.password = password;
	this.role = role;
	this.address = address;
	this.zipCode = zipCode;
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

	public User() {  
 
    }  

    public User( String full_name, String email, int telephone, String login, String password, int role) {

        this.full_name = full_name;
        this.email = email;
        this.telephone = telephone;
        this.login = login;
        this.password = password;
        this.role = role; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User userTst = (User) o;
        return id == userTst.id &&
                telephone == userTst.telephone &&
                role == userTst.role &&
                Objects.equals(full_name, userTst.full_name) &&
                Objects.equals(email, userTst.email) &&
                Objects.equals(login, userTst.login) &&
                Objects.equals(password, userTst.password);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", full_name=" + full_name + ", email=" + email + ", telephone=" + telephone
				+ ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
    
}
