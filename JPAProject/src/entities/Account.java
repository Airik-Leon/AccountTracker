package entities;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	private String name;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user; 
	@OneToOne
	@JoinColumn(name="type_id")
	private AccountType accountType;
	@JsonIgnore
	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", user=" + user + ", accountType=" + accountType
				 + "]";
	} 
	
}
