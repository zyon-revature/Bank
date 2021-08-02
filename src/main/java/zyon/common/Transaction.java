package zyon.common;

public class Transaction {
	private int _id;
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

	@Override
	public String toString() {
		return String.format("[Amount: " + _amount + "\tRouting #: " + _routingNumber + "]");
	}

}
