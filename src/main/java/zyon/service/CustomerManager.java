package zyon.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zyon.common.Customer;
import zyon.common.util.DBUtil;

public class CustomerManager {

	private static final Logger logger = LogManager.getLogger(CustomerManager.class);

	private ArrayList<Customer> _customers;

	public ArrayList<Customer> get_customers() {
		return _customers;
	}

	public void set_customers(ArrayList<Customer> _customers) {
		this._customers = _customers;
	}
	
	public void addCustomer(Customer arrayList) {
		this._customers.add(arrayList);
	}

	private boolean isStringPresent(String str) {
		return str != null && !str.isEmpty();
	}

	public ArrayList<Customer> findAll() throws SQLException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try {
			Connection conn = DBUtil.getInstance().getConnection();

			String query = "SELECT * FROM bank.customers c;";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				customers.add(c);
			}
			logger.info("Pull customers table.");

			rs.close();
			stmt.close();

		} catch (SQLException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customers;
	}
	
	public void pullManager(String username, String password) {
		this._customers = null;
		try {
			this._customers = findAll(username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Customer> findAll(String username, String password) throws SQLException {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		if (isStringPresent(username) == false | isStringPresent(password) == false) {
			return null;
		}

		try {
			Connection conn = DBUtil.getInstance().getConnection();

			String query = "SELECT * FROM bank.customers c WHERE username = ? and userpassword = ?;";

			PreparedStatement pstmt = conn.prepareStatement(query);

			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				customers.add(c);
				
			}
			logger.info("Pull customers table.");

			rs.close();
			pstmt.close();

		} catch (SQLException ex) {
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customers;
	}
	
//	public ArrayList<Customer> findAll(int id) throws SQLException {
//		ArrayList<Customer> customers = new ArrayList<Customer>();
//
//		try {
//			Connection conn = DBUtil.getInstance().getConnection();
//
//			String query = "SELECT * FROM bank.customers c WHERE account_num = ?;";
//
//			PreparedStatement pstmt = conn.prepareStatement(query);
//
//			pstmt.setString(1, username);
//			pstmt.setString(2, password);
//
//			ResultSet rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
//						rs.getString(5));
//				customers.add(c);
//				
//			}
//			logger.info("Pull customers table.");
//
//			rs.close();
//			pstmt.close();
//
//		} catch (SQLException ex) {
//			throw ex;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return customers;
//	}
	
	
}
