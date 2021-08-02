package zyon.common;

public class Transaction {
	private int _id;
	private String _date;
	private float _amount;
	private int _routingNumber;
	
	
	public Transaction(float amount, int routingNumber) {
		super();
		this._amount = amount;
		this._routingNumber = routingNumber;
	}


	public Transaction(int _id, float _amount, int _routingNumber) {
		super();
		this._id = _id;
		this._amount = _amount;
		this._routingNumber = _routingNumber;
	}
	
	public Transaction(int _id, float _amount, String _date, int _routingNumber) {
		super();
		this._id = _id;
		this._amount = _amount;
		this._date = _date;
		this._routingNumber = _routingNumber;
	}


	public int getId() {
		return _id;
	}

	public void setId(int id) {
		this._id = id;
	}

	public float getAmount() {
		return _amount;
	}

	public void setAmount(float amount) {
		this._amount = amount;
	}


	public int getRoutingNumber() {
		return _routingNumber;
	}

	public void setRoutingNumber(int routingNumber) {
		this._routingNumber = routingNumber;
	}
	
	
	public String get_date() {
		return _date;
	}


	public void set_date(String _date) {
		this._date = _date;
	}


	@Override
	public String toString() {
		return String.format("[Date" + _date+ "\tAmount: " + _amount + "\tRouting #: " + _routingNumber + "]");
	}

}
