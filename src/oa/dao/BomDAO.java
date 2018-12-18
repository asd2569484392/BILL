package oa.dao;

import java.util.List;

import oa.bean.Materiel;

public interface BomDAO {
	
	
	public void initBOM(List<Materiel> materiels);
	
	public List<Materiel> queryAllBomData();
	
	public int queryCurrentMaterielLength(); 
	
	public void insertOrUpdate(List<Materiel> materiels);
	
	public Materiel queryMaterielByItem(String item);

}
