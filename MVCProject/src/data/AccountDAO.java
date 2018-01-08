package data;

import java.util.List;

import entities.Account;
import entities.AccountType;
import entities.TradeType;
import entities.Transaction;

public interface AccountDAO {
	//Account methods
	public List<Account>index();
	public Account show(int id); 
	public Account create(int uId, String json); 
	public Account update(int id, String json); 
	public Account delete(int id);
	//Transaction methods 
	public List<Transaction>indexTransactions(int id); 
	public Transaction showTransaction(int tId); 
	public Transaction createTransaction(int uId, int aId, String json); 
	public Transaction updateTransaction(int uId, int aId, int tId, String json); 
	public Transaction deleteTransaction(int id); 
	
	//User related method 
	public List<Account> indexUserAccounts(int userId);
	List<Transaction> indexUserAccountsTransactions(int userId, int accountId);
	Transaction showUserAccountsTransactions(int userId, int accountId, int tId);
	List<TradeType> indexTradeType();
	List<AccountType> indexAccountType(); 

}
