package oa.bean;

public class Sum {
	
	private String item;
	
	private	String ship;
	
	private	String rec;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getShip() {
		return ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getRec() {
		return rec;
	}

	public void setRec(String rec) {
		this.rec = rec;
	}

	public Sum(String item, String ship, String rec) {
		super();
		this.item = item;
		this.ship = ship;
		this.rec = rec;
	}

	public Sum() {
		super();
	}

	@Override
	public String toString() {
		return "Sum [item=" + item + ", ship=" + ship + ", rec=" + rec + "]";
	}
	
	
}
