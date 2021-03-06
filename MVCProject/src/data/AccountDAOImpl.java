package data;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Account;
import entities.AccountType;
import entities.TradeType;
import entities.Transaction;
import entities.User;

@Repository
@Transactional
public class AccountDAOImpl implements AccountDAO{
	@PersistenceContext
	private EntityManager em; 
	@Override
	public List<Account> index() {
		String query = "SELECT a FROM Account a"; 
		return em.createQuery(query, Account.class)
				.getResultList(); 
	}
	@Override
	public Account show(int id) {
		return em.find(Account.class, id);
	}
	@Override
	public Account create(int uId, String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		Account a = null; 
		try {
			System.out.println(a);
			a = mapper.readValue(json, Account.class);
			User u = em.find(User.class, uId); 
			a.setUser(u);
			em.persist(a);
			em.flush();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return em.find(Account.class, a.getId());
	}

	@Override
	public Account update(int id, String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		Account a = null; 
		Account jsonObject= null; 
		try {
			jsonObject = mapper.readValue(json, Account.class);
			System.out.println(jsonObject); 
			a = em.find(Account.class, id); 
			a.setName(jsonObject.getName());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return a;  
	}

	@Override
	public Account delete(int id) {
		Account a = em.find(Account.class, id);
		em.remove(a);
		return a;
	}
	@Override
	public List<Transaction> indexTransactions(int id) {
		String query = "SELECT t FROM Transaction t WHERE t.account.id = :id"; 
		return em.createQuery(query, Transaction.class)
				.setParameter("id", id)
				.getResultList(); 
	}
	@Override
	public Transaction showTransaction(int tId) {
		return em.find(Transaction.class, tId); 
	}
	@Override
	public Transaction createTransaction(int uId,int aId,  String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		Transaction t = null; 
		try {
			t = mapper.readValue(json, Transaction.class);
			User u = em.find(User.class, uId); 
			Account a = em.find(Account.class, aId); 
			t.setAccount(a);
			t.setUser(u);
			em.persist(t);
			em.flush();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return em.find(Transaction.class, t.getId());
	}
	@Override
	public Transaction updateTransaction(int uId, int aId, int tId, String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		Transaction t = null; 
		Transaction jsonObject = null; 
		try {
			jsonObject = mapper.readValue(json, Transaction.class);
			t = em.find(Transaction.class, tId);
			t.setCompanyAddress(jsonObject.getCompanyAddress());
			t.setCompanyName(jsonObject.getCompanyName());
			t.setPrice(jsonObject.getPrice());
			t.setType(jsonObject.getType());
			em.persist(t);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return t;
	}
	@Override
	public Transaction deleteTransaction(int id) {
		Transaction t = em.find(Transaction.class, id); 
		em.remove(t);
		return t;
	}
	@Override
	public List<Account> indexUserAccounts(int userId) {
		String query = "SELECT a FROM Account a WHERE a.user.id = :id"; 
		return em.createQuery(query, Account.class)
				.setParameter("id", userId)
				.getResultList(); 
	}
	@Override
	public List<Transaction> indexUserAccountsTransactions(int userId, int accountId) {
		String query = "SELECT t FROM Transaction t WHERE t.user.id = :uId AND t.account.id = :aId"; 
		return em.createQuery(query, Transaction.class)
				.setParameter("uId", userId)
				.setParameter("aId", accountId)
				.getResultList(); 
	}
	@Override
	public Transaction showUserAccountsTransactions(int userId, int accountId, int tId) {
		String query = "SELECT t FROM Transaction t WHERE (t.user.id = :uId AND t.account.id = :aId) AND t.id = :tId"; 
		return  em.createQuery(query, Transaction.class)
				.setParameter("uId", userId)
				.setParameter("aId", accountId)
				.setParameter("tId", tId)
				.getResultList().get(0); 
	}
	@Override 
	public List<TradeType> indexTradeType(){
		String query = "SELECT tt FROM TradeType tt"; 
		return em.createQuery(query, TradeType.class)
				.getResultList(); 
	}
	@Override
	public List<AccountType> indexAccountType(){
		String query = "SELECT at FROM AccountType at"; 
		return em.createQuery(query, AccountType.class)
				.getResultList(); 
	}
}
