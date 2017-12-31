package data;

import java.util.List;

import entities.Account;
import entities.Transaction;

public interface AccountDAO {
	//Account methods
	public List<Account>index();
	public Account show(int id); 
	public Account create(String json); 
	public Account update(int id, String json); 
	public Account delete(int id);
	//Transaction methods 
	public List<Transaction>indexTransactions(int id); 
	public Transaction showTransaction(int tId); 
	public Transaction createTransaction(String json); 
	public Transaction updateTransaction(String json, int id); 
	public Transaction deleteTransaction(int id); 
	
	//User related method 
	public List<Account> indexUserAccounts(int userId);
	List<Transaction> indexUserAccountsTransactions(int userId, int accountId);
	Transaction showUserAccountsTransactions(int userId, int accountId, int tId); 

}
