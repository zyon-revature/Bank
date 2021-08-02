package zyon.common;

public class Customer {
	private int account_num;
	private String f_name, l_name;
	private String user_name, user_password;
	private Account _account; 

	// Constructors
	public Customer(int account_num, String f_name, String l_name, String user_name, String user_password) {
		super();
		this.account_num = account_num;
		this.f_name = f_name;
		this.l_name = l_name;
		this.user_name = user_name;
		this.user_password = user_password;
	}

	public Customer(String f_name, String l_name, String user_name, String user_password) {
		super();
		this.f_name = f_name;
		this.l_name = l_name;
		this.user_name = user_name;
		this.user_password = user_password;
	}

	// Getters and Setters
	public int getAccount_num() {
		return account_num;
	}

	public void setAccount_num(int account_num) {
		this.account_num = account_num;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Account get_account() {
		return _account;
	}

	public void set_account(Account _account) {
		this._account = _account;
	}

	@Override
	public String toString() {
		return "[Account #: " + account_num + "\tFirst Name: " + f_name + "\tLast Name: " + l_name + "\tUsername: "
				+ user_name + "]";
	}

}
