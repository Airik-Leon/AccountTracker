package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	@Column(name="first_name")
	private String firstName; 
	@Column(name="last_name")
	private String lastName; 
	private String email; 
	private String password; 
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Account> userAccounts;
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Transaction> userTransactions;

	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Account> getUserAccounts() {
		return userAccounts;
	}
	public void setUserAccounts(List<Account> userAccounts) {
		this.userAccounts = userAccounts;
	} 
}
