package oa.bean;

public class Location {

	private int locationId;

	private String materielNumber;

	private String materielDetail;

	private String materielGroupCode;

	private String locationDetail;

	private String materielUnit;

	private double materielNRO;

	private double materielUO;

	private double materielPRC;

	private double materielFO;

	private double materielFC;

	private double materielFCO;

	private double materielCS;

	private double materielCN;

	private double materielOIC;
	
	private double openingInentory;
	
	private double endInentory;
	
	private String remarks;

	public int getLocationId() {
		return this.locationId;
	};

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getMaterielNumber() {
		return this.materielNumber;
	};

	public void setMaterielNumber(String materielNumber) {
		this.materielNumber = materielNumber;
	}

	public String getMaterielDetail() {
		return this.materielDetail;
	};

	public void setMaterielDetail(String materielDetail) {
		this.materielDetail = materielDetail;
	}

	public String getMaterielGroupCode() {
		return this.materielGroupCode;
	};

	public void setMaterielGroupCode(String materielGroupCode) {
		this.materielGroupCode = materielGroupCode;
	}

	public String getMaterielUnit() {
		return this.materielUnit;
	};

	public void setMaterielUnit(String materielUnit) {
		this.materielUnit = materielUnit;
	}

	public double getMaterielNRO() {
		return this.materielNRO;
	};

	public void setMaterielNRO(double materielNRO) {
		this.materielNRO = materielNRO;
	}

	public double getMaterielUO() {
		return this.materielUO;
	};

	public void setMaterielUO(double materielUO) {
		this.materielUO = materielUO;
	}

	public double getMaterielPRC() {
		return this.materielPRC;
	};

	public void setMaterielPRC(double materielPRC) {
		this.materielPRC = materielPRC;
	}

	public double getMaterielFO() {
		return this.materielFO;
	};

	public void setMaterielFO(double materielFO) {
		this.materielFO = materielFO;
	}

	public double getMaterielFC() {
		return this.materielFC;
	};

	public void setMaterielFC(double materielFC) {
		this.materielFC = materielFC;
	}

	public double getMaterielFCO() {
		return this.materielFCO;
	};

	public void setMaterielFCO(double materielFCO) {
		this.materielFCO = materielFCO;
	}

	public double getMaterielCS() {
		return this.materielCS;
	};

	public void setMaterielCS(double materielCS) {
		this.materielCS = materielCS;
	}

	public double getMaterielCN() {
		return this.materielCN;
	};

	public void setMaterielCN(double materielCN) {
		this.materielCN = materielCN;
	}

	public double getMaterielOIC() {
		return this.materielOIC;
	};

	public void setMaterielOIC(double materielOIC) {
		this.materielOIC = materielOIC;
	}

	public String getRemarks() {
		return this.remarks;
	};

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Location() {
		super();
	}

	public String getLocationDetail() {
		return locationDetail;
	}

	public void setLocationDetail(String locationDetail) {
		this.locationDetail = locationDetail;
	}

	public Location(int locationId, String materielNumber, String materielDetail, String materielGroupCode,
			String locationDetail, String materielUnit, double materielNRO, double materielUO, double materielPRC,
			double materielFO, double materielFC, double materielFCO, double materielCS, double materielCN,
			double materielOIC, String remarks) {
		super();
		this.locationId = locationId;
		this.materielNumber = materielNumber;
		this.materielDetail = materielDetail;
		this.materielGroupCode = materielGroupCode;
		this.locationDetail = locationDetail;
		this.materielUnit = materielUnit;
		this.materielNRO = materielNRO;
		this.materielUO = materielUO;
		this.materielPRC = materielPRC;
		this.materielFO = materielFO;
		this.materielFC = materielFC;
		this.materielFCO = materielFCO;
		this.materielCS = materielCS;
		this.materielCN = materielCN;
		this.materielOIC = materielOIC;
		this.remarks = remarks;
	}

	public Location(int locationId, String materielNumber, String materielDetail, String materielGroupCode,
			String locationDetail, String materielUnit, double materielNRO, double materielUO, double materielPRC,
			double materielFO, double materielFC, double materielFCO, double materielCS, double materielCN,
			double materielOIC, double openingInentory, double endInentory, String remarks) {
		super();
		this.locationId = locationId;
		this.materielNumber = materielNumber;
		this.materielDetail = materielDetail;
		this.materielGroupCode = materielGroupCode;
		this.locationDetail = locationDetail;
		this.materielUnit = materielUnit;
		this.materielNRO = materielNRO;
		this.materielUO = materielUO;
		this.materielPRC = materielPRC;
		this.materielFO = materielFO;
		this.materielFC = materielFC;
		this.materielFCO = materielFCO;
		this.materielCS = materielCS;
		this.materielCN = materielCN;
		this.materielOIC = materielOIC;
		this.openingInentory = openingInentory;
		this.endInentory = endInentory;
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", materielNumber=" + materielNumber + ", materielDetail="
				+ materielDetail + ", materielGroupCode=" + materielGroupCode + ", locationDetail=" + locationDetail
				+ ", materielUnit=" + materielUnit + ", materielNRO=" + materielNRO + ", materielUO=" + materielUO
				+ ", materielPRC=" + materielPRC + ", materielFO=" + materielFO + ", materielFC=" + materielFC
				+ ", materielFCO=" + materielFCO + ", materielCS=" + materielCS + ", materielCN=" + materielCN
				+ ", materielOIC=" + materielOIC + ", remarks=" + remarks + "]";
	}

	public double getOpeningInentory() {
		return openingInentory;
	}

	public void setOpeningInentory(double openingInentory) {
		this.openingInentory = openingInentory;
	}

	public double getEndInentory() {
		return endInentory;
	}

	public void setEndInentory(double endInentory) {
		this.endInentory = endInentory;
	}

}