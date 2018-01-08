package controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.AccountDAO;
import data.UserDAO;
import entities.Account;
import entities.AccountType;
import entities.TradeType;
import entities.Transaction;
import entities.User;
@CrossOrigin
@RestController
public class ReceiptTrackerController {
	@Autowired
	UserDAO userDAO;
	@Autowired 
	AccountDAO accountDAO; 
	
	//User Methods
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
	//
	//Account Methods
	//
	@RequestMapping(path="/users/{id}/accounts", method=RequestMethod.GET)
	public List<Account> indexUserAccounts(@PathVariable int id) {
		List<Account> userAccounts = accountDAO.indexUserAccounts(id);
		return userAccounts; 
	}
	@RequestMapping(path="/users/{uId}/accounts", method=RequestMethod.POST)
	public Account createAccounts(@PathVariable int uId, @RequestBody String json, HttpServletResponse res) {
		System.out.println(json);
		Account a = accountDAO.create(uId, json);
		return 	a;  
	}
	@RequestMapping(path="/users/{uId}/accounts/{aId}", method=RequestMethod.PUT)
	public Account updateAccounts( @RequestBody String json, @PathVariable int uId,@PathVariable int aId, HttpServletResponse res) {
		Account a = accountDAO.update(aId, json); 
		return a; 
	}
	@RequestMapping(path="/users/{uId}/accounts/{aId}", method=RequestMethod.DELETE)
	public Account deleteAccount(@PathVariable int aId, @PathVariable int uId) {
		Account a = accountDAO.delete(aId); 
		return a; 
	}
	
	/*
	 * Transaction methods 
	 */
	
	@RequestMapping(path="/users/{uId}/accounts/{aId}/transactions", method=RequestMethod.GET)
	public List<Transaction> indexUserAccountsTransactions(@PathVariable int uId, @PathVariable int aId) {
		List<Transaction> userAccounts = accountDAO.indexUserAccountsTransactions(uId, aId);
		return userAccounts; 
	}
	@RequestMapping(path="/users/{uId}/accounts/{aId}/transactions", method=RequestMethod.POST)
	public Transaction createAccountTransaction(@PathVariable int uId, @PathVariable int aId, @RequestBody String json, HttpServletResponse res) {
		Transaction t = accountDAO.createTransaction(uId, aId, json);
		return t;  
	}
	@RequestMapping(path="users/{uId}/accounts/{aId}/transactions/{tId}", method=RequestMethod.PUT)
	public Transaction updateAccountTransaction(@PathVariable int uId, @PathVariable int aId, @PathVariable int tId, @RequestBody String json, HttpServletResponse res) {
		Transaction t = accountDAO.updateTransaction(uId, aId, tId, json);
		return t;  
	}
	@RequestMapping(path="users/{uId}/accounts/{aId}/transactions/{tId}", method=RequestMethod.DELETE)
	public Transaction deleteAccountTransactions(@PathVariable int tId) {
		Transaction t = accountDAO.deleteTransaction(tId); 
		return t;  
	}
	
	//Send types of trades
	@RequestMapping(path="/transactionType", method=RequestMethod.GET)
	public List<TradeType> TradeTypes(){
		return accountDAO.indexTradeType(); 
	}
	@RequestMapping(path="/accountType", method=RequestMethod.GET)
	public List<AccountType> accountTypes(){
		return accountDAO.indexAccountType(); 
	}
}
