package zyon.common;

public class Account {
	private int _routingNum;
	private float _balance;
	private int _accountNum;
	
	public Account() {
		super();
	}
	
	public Account(int _routingNum, float _balance, int _accountNum) {
		super();
		this._routingNum = _routingNum;
		this._balance = _balance;
		this._accountNum = _accountNum;
	}

	public Account(int _accountNum, float _balance) {
		super();
		this.set_accountNum(_accountNum);
		this._balance = _balance;
	}

	public Account(int _accountNum) {
		super();
		this.set_accountNum(_accountNum);
		this._balance = 0;
	}

	public int get_routingNum() {
		return _routingNum;
	}

	public void set_routingNum(int _routingNum) {
		this._routingNum = _routingNum;
	}

	public float get_balance() {
		return _balance;
	}

	public void set_balance(float _balance) {
		this._balance = _balance;
	}

	public int get_accountNum() {
		return _accountNum;
	}

	public void set_accountNum(int _accountNum) {
		this._accountNum = _accountNum;
	}

	@Override
	public String toString() {
		return "[Routing #: " + _routingNum + "\tBalance: " + _balance + "\tAccount #: " + _accountNum + "]";
	}

	
	
	
	
}
