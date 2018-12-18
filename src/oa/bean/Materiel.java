package oa.bean;


//BOM
public class Materiel {
	
	private	String materiel_id;
	
	private	String materiel_detail;
	
	private	float materiel_package;
	
	private	String materiel_supplier;
	
	private	String materiel_type;

	public String getMateriel_id() {
		return materiel_id;
	}

	public void setMateriel_id(String materiel_id) {
		this.materiel_id = materiel_id;
	}

	public String getMateriel_detail() {
		return materiel_detail;
	}

	public void setMateriel_detail(String materiel_detail) {
		this.materiel_detail = materiel_detail;
	}

	

	public String getMateriel_supplier() {
		return materiel_supplier;
	}

	public void setMateriel_supplier(String materiel_supplier) {
		this.materiel_supplier = materiel_supplier;
	}

	public String getMateriel_type() {
		return materiel_type;
	}

	public void setMateriel_type(String materiel_type) {
		this.materiel_type = materiel_type;
	}

	public Materiel(String materiel_id, String materiel_detail, float materiel_package, String materiel_supplier,
			String materiel_type) {
		super();
		this.materiel_id = materiel_id;
		this.materiel_detail = materiel_detail;
		this.setMateriel_package(materiel_package);
		this.materiel_supplier = materiel_supplier;
		this.materiel_type = materiel_type;
	}

	public Materiel() {
		super();
	}

	public float getMateriel_package() {
		return materiel_package;
	}

	public void setMateriel_package(float materiel_package) {
		this.materiel_package = materiel_package;
	}

	@Override
	public String toString() {
		return "Materiel [materiel_id=" + materiel_id + ", materiel_detail=" + materiel_detail + ", materiel_package="
				+ materiel_package + ", materiel_supplier=" + materiel_supplier + ", materiel_type=" + materiel_type
				+ "]";
	}
	
	
	
}
