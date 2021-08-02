package zyon.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zyon.common.Transaction;
import zyon.common.util.DBUtil;
import zyon.service.AccountManager;
import zyon.service.CustomerManager;
import zyon.service.TransactionManager;

public class ConsoleApp {
	
	private static final Logger logger = LogManager.getLogger(ConsoleApp.class);
	
	private Connection conn = null;
	
	//TODO: Add connection from properties file. Using Singleton
	public void start() {
		boolean quit = false;
		Scanner input = new Scanner(System.in);
		
		do {
			// show menu
			mainMenu();
			
			System.out.println("Enter Action: ");
			String choice = input.nextLine();
			
			switch (choice) {
				case "1":
					login(input, conn);
					break;
				case "2":
					createAccount(input, conn);
					break;
				case "3":
					quit = true;
					break;
				default:
					break;
			}
		} while(quit == false);
		
		input.close();
		
	}
	//TODO: Add Menu
	public static void mainMenu() {
		StringBuilder prompt = new StringBuilder();
		prompt.append("\nSelections\n");
		prompt.append("1. Login\n");
		prompt.append("2. Create Account\n");
		prompt.append("3. Exit\n");
		
		System.out.println(prompt);
		
	}
	
	public void login(Scanner input, Connection conn){
		
		String usernamePrompt = "Username: ";
		String passwordPrompt = "Password: ";
		String username = null, password = null; 
		boolean userExist = false;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			
			CustomerManager cus = new CustomerManager();
			String query = "SELECT EXISTS (SELECT username, userpassword "
					+ "FROM bank.customers c "
					+ "WHERE username = ? AND userpassword = ?) AS exist;";
			
			do {
				System.out.print(usernamePrompt);
				username = input.nextLine();
				System.out.print(passwordPrompt);
				password = input.nextLine();
				
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()){
					userExist = rs.getBoolean(1);
				}
			}while(userExist == false);
			

			
			
			cus.pullManager(username, password);
			int id;
			id = cus.get_customers().get(0).getAccount_num();
			AccountManager acc = new AccountManager();
			acc.pullManager(id);
			
			boolean quit = false;
			
			do {
				// show menu
				loginMenu();
				
				System.out.println("Enter Action: ");
				String choice = input.nextLine();
				
				switch (choice) {
					case "1":
						System.out.println("Balance: " + acc.get_accounts().get(0).get_balance());
						break;
					case "2":
						TransactionManager tr = new TransactionManager();
						tr.pullManager(acc.get_accounts().get(0).get_routingNum());
						for (Transaction t : tr.get_Transaction()) {
							System.out.println(t);
						}
						break;
					case "3":
						quit = true;
						break;
					default:
						break;
				}
			} while(quit == false);
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginMenu() {
		StringBuilder prompt = new StringBuilder();
		prompt.append("\nSelections\n");
		prompt.append("1. Check Balance");
		prompt.append("2. Check Transaction History\n");
		prompt.append("3. Exit\n");
		
		System.out.println(prompt);
	}
	
	public void createAccount(Scanner input, Connection conn) {
		String firstNamePrompt = "First Name: ";
		String lastNamePrompt = "Last Name: ";
		String usernamePrompt = "Username: ";
		String passwordPrompt = "Password: ";
		String first = null, last = null, username = null, password = null; 
		boolean userExist = true;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			
			CustomerManager cus = new CustomerManager();
			String query = "SELECT EXISTS (SELECT username, userpassword "
					+ "FROM bank.customers c "
					+ "WHERE username = ? AND userpassword = ?) AS exist;";
			
			
			do {
				System.out.print(firstNamePrompt);
				first = input.next();
				System.out.print(lastNamePrompt);
				last = input.next();
				System.out.print(usernamePrompt);
				username = input.next();
				System.out.print(passwordPrompt);
				password = input.next();
				
				
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				
				if (rs.next()){
					userExist = rs.getBoolean(1);
				}
				
			}while(userExist == true);
			query = "INSERT INTO bank.customers (f_name, l_name, username, userpassword) VALUES(?, ?, ?, ?);";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, first);
			pstmt.setString(2, last);
			pstmt.setString(3, username);
			pstmt.setString(4, password);
			
			int inserted1 = pstmt.executeUpdate();
			System.out.println("inserted: "+inserted1);
			
			cus.set_customers(cus.findAll(username, password));
			
			
			query = "INSERT INTO bank.accounts (balance, account_num) VALUES(?, ?);";
			PreparedStatement pstmt2 = conn.prepareStatement(query);
			System.out.println("Enter starting balance: ");
			float temp_balance;  
			String temp = input.next();
			temp_balance = Float.parseFloat(temp);
			pstmt2.setFloat(1, temp_balance);
			int id;
			id = cus.get_customers().get(0).getAccount_num();
//			System.out.println(id);
			pstmt2.setInt(2, id);
			inserted1 = pstmt2.executeUpdate();
			System.out.println("inserted: "+inserted1);
			
		
		}catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
}
	









