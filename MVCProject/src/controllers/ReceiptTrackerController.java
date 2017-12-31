package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.AccountDAO;
import data.UserDAO;
import entities.Account;
import entities.Transaction;
import entities.User;

@RestController
public class ReceiptTrackerController {
	@Autowired
	UserDAO userDAO;
	@Autowired 
	AccountDAO accountDAO; 
	
	@RequestMapping(path="/users", method=RequestMethod.GET)
	public List<User> usersIndex() {
		return userDAO.index(); 
	}
	@RequestMapping(path="/users/{id}", method=RequestMethod.GET)
	public User showUsers(@PathVariable int id) {
		return userDAO.show(id); 
	}
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public User createUsers(@RequestBody String json, HttpServletResponse res) {
		User u = userDAO.create(json);
		if(u == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(202);
		}
		return 	u;  
	}
	@RequestMapping(path="/users/{id}", method=RequestMethod.PUT)
	public User updateUsers(@PathVariable int id, @RequestBody String json, HttpServletResponse res) {
		User u = userDAO.update(id, json);
		if(u == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(202);
		}
		
		return 	u;  
	}
	@RequestMapping(path="/users/{id}", method=RequestMethod.DELETE)
	public User deleteUsers(@PathVariable int id) {
		User u = userDAO.delete(id);
		return 	u;  
	}
	@RequestMapping(path="/users/{id}/accounts", method=RequestMethod.GET)
	public List<Account> indexUserAccounts(@PathVariable int id) {
		List<Account> userAccounts = accountDAO.indexUserAccounts(id);
		return userAccounts; 
	}
	@RequestMapping(path="/users/{uId}/accounts/{aId}/transactions", method=RequestMethod.GET)
	public List<Transaction> indexUserAccountsTransactions(@PathVariable int uId, @PathVariable int aId) {
		List<Transaction> userAccounts = accountDAO.indexUserAccountsTransactions(uId, aId);
		return userAccounts; 
	}
	@RequestMapping(path="/users/{uId}/accounts/{aId}/transactions/{tId}", method=RequestMethod.GET)
	public Transaction showUserAccountsTransactions(@PathVariable int uId,
			@PathVariable int aId, @PathVariable int tId) {
		Transaction t = accountDAO.showUserAccountsTransactions(uId, aId, tId);
		return t; 
	}
	
	@RequestMapping(path="/accounts", method=RequestMethod.GET)
	public List<Account> accountsIndex() {
		return accountDAO.index();  
	}
	@RequestMapping(path="/accounts/{id}", method=RequestMethod.GET)
	public Account accountsShow(@PathVariable int id) {
		return accountDAO.show(id);  
	}
	@RequestMapping(path="/accounts", method=RequestMethod.POST)
	public Account createAccounts(@RequestBody String json, HttpServletResponse res) {
		Account a = accountDAO.create(json);
		if(a == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(202);
		}
		
		return 	a;  
	}
	@RequestMapping(path="/accounts/{id}", method=RequestMethod.PUT)
	public Account updateAccounts( @RequestBody String json, @PathVariable int id, HttpServletResponse res) {
		Account a = accountDAO.update(id, json); 
		if(a == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(202);
		}
		return a; 
	}
	@RequestMapping(path="/accounts/{id}", method=RequestMethod.DELETE)
	public Account deleteAccounts( @PathVariable int id) {
		Account a = accountDAO.delete(id); 
		return a; 
	}
	@RequestMapping(path="/accounts/{id}/transactions", method=RequestMethod.GET)
	public List<Transaction> indexAccountTransactions(@PathVariable int id) {
		return accountDAO.indexTransactions(id);  
	}
	@RequestMapping(path="/accounts/{aId}/transactions/{tId}", method=RequestMethod.GET)
	public Transaction showAccountTransaction(@PathVariable int aId, @PathVariable int tId) {
		return accountDAO.showTransaction(tId); 
	}
	@RequestMapping(path="/accounts/{aId}/transactions/", method=RequestMethod.POST)
	public Transaction createAccountTransaction(@PathVariable int aId, @RequestBody String json, HttpServletResponse res) {
		Transaction t = accountDAO.createTransaction(json);
		if(t == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(202);
		}
		return t;  
	}
	@RequestMapping(path="/accounts/{aId}/transactions/{tId}", method=RequestMethod.PUT)
	public Transaction updateAccountTransaction(@PathVariable int tId, @RequestBody String json, HttpServletResponse res) {
		Transaction t = accountDAO.updateTransaction(json, tId);
		if(t == null) {
			res.setStatus(400);
		}
		else {
			res.setStatus(202);
		}
		return t;  
	}
	@RequestMapping(path="/accounts/{aId}/transactions/{tId}", method=RequestMethod.DELETE)
	public Transaction deleteAccountTransactions(@PathVariable int tId) {
		Transaction t = accountDAO.deleteTransaction(tId); 
		return t;  
	}
}
