package zyon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zyon.common.Transaction;
import zyon.common.util.DBUtil;

public class TransactionManager {

	private static final Logger logger = LogManager.getLogger(TransactionManager.class);

	private ArrayList<Transaction> _transactions;

	public ArrayList<Transaction> get_Transaction() {
		return _transactions;
	}

	public void set_Transaction(ArrayList<Transaction> transaction) {
		this._transactions = transaction;
	}
	
	public void addTransaction(Transaction arrayList) {
		this._transactions.add(arrayList);
	}

	
	public ArrayList<Transaction> findAll() throws SQLException {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		try {
			Connection conn = DBUtil.getInstance().getConnection();

			String query = "SELECT * FROM bank.transactions t;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Transaction t = new Transaction(rs.getInt(1), rs.getFloat(2), rs.getInt(3));
				transactions.add(t);
			}
			logger.info("Pull transactions table.");

			rs.close();
			stmt.close();

		} catch (SQLException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactions;
	}
	
	public void pullManager(int id) {
		this._transactions = null;
		try {
			this._transactions = findAll(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Transaction> findAll(int id) throws SQLException {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();

		try {
			Connection conn = DBUtil.getInstance().getConnection();

			String query = "SELECT * FROM bank.transactions t WHERE routing_num = ?;";

			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Transaction t = new Transaction(rs.getInt(1), rs.getFloat(2), rs.getString(3), rs.getInt(4));
				transactions.add(t);
			}
			logger.info("Pull transactions table.");

			rs.close();
			pstmt.close();

		} catch (SQLException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return transactions;
	}
	

	
	
}
