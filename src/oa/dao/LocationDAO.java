package oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import oa.bean.Location;



public interface LocationDAO {
	
	public void initLocation(List<Location> list);
	
	public List<Location> queryAll();
	
	public Location queryLocationById(String item);
	
	public void subNRO(@Param("item")String item,@Param("shipNumber")int shipNumber);
	
	public void addNRO(@Param("item")String item,@Param("recNumber")int recNumber);
	
	public int queryLocationCount();
	
	public void insertOrUpdate(List<Location> list);
	
}
