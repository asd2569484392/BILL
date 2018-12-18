package oa.bean;

public class SystemInfo {
	
	private	int id;
	
	private	String excel_name;
	
	private	String excel_date;

	@Override
	public String toString() {
		return "SystemInfo [id=" + id + ", excel_name=" + excel_name + ", excel_date=" + excel_date + "]";
	}

	public SystemInfo(int id, String excel_name, String excel_date) {
		super();
		this.id = id;
		this.excel_name = excel_name;
		this.excel_date = excel_date;
	}

	public SystemInfo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExcel_name() {
		return excel_name;
	}

	public void setExcel_name(String excel_name) {
		this.excel_name = excel_name;
	}

	public String getExcel_date() {
		return excel_date;
	}

	public void setExcel_date(String excel_date) {
		this.excel_date = excel_date;
	}
	
	
	
	

}
