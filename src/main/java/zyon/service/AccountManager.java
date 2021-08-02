package zyon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zyon.common.Account;
import zyon.common.util.DBUtil;

public class AccountManager {

	private static final Logger logger = LogManager.getLogger(AccountManager.class);

	private ArrayList<Account> _accounts;

	
	public ArrayList<Account> get_accounts() {
		return _accounts;
	}

	public void set_accounts(ArrayList<Account> _accounts) {
		this._accounts = _accounts;
	}

	public void addAccount(Account account) {
		this._accounts.add(account);
	}
	
	public ArrayList<Account> findAll() throws SQLException {
		ArrayList<Account> accounts = new ArrayList<Account>();
		try {
			Connection conn = DBUtil.getInstance().getConnection();

			String query = "SELECT * FROM bank.accounts a;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Account a = new Account(rs.getInt(1), rs.getFloat(2), rs.getInt(3));
				accounts.add(a);
			}
			logger.info("Pull account table.");

			rs.close();
			stmt.close();

		} catch (SQLException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}
	
	public void pullManager(int routing) {
		this._accounts = null;
		try {
			this._accounts = findAll(routing);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Account> findAll(int num) throws SQLException {
		ArrayList<Account> accounts = new ArrayList<Account>();

		try {
			Connection conn = DBUtil.getInstance().getConnection();

			String query = "SELECT * FROM bank.account a WHERE routingNum = ?;";

			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, num);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Account a = new Account(rs.getInt(1), rs.getFloat(2), rs.getInt(3));
				accounts.add(a);
				
			}
			logger.info("Pull account table.");

			rs.close();
			pstmt.close();

		} catch (SQLException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}

	
	
}
