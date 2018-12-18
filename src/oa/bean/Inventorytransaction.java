package oa.bean;

public class Inventorytransaction {

	private String orderCode;

	private String actionType;

	private String date;

	private long number;

	private String item;

	private String currentLocation;

	private String destinationLocation;

	public Inventorytransaction(String orderCode, String actionType, String date, long number, String item,
			String currentLocation, String destinationLocation) {
		super();
		this.orderCode = orderCode;
		this.actionType = actionType;
		this.date = date;
		this.number = number;
		this.item = item;
		this.currentLocation = currentLocation;
		this.destinationLocation = destinationLocation;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	@Override
	public String toString() {
		return "Inventorytransaction [orderCode=" + orderCode + ", actionType=" + actionType + ", date=" + date
				+ ", number=" + number + ", item=" + item + ", currentLocation=" + currentLocation
				+ ", destinationLocation=" + destinationLocation + "]";
	}

	public Inventorytransaction() {
		super();
	}

	

}