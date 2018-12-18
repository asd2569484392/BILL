package oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import oa.bean.Inventorytransaction;
import oa.bean.Sum;

public interface InventoryTransactionDAO {
		
	public void addInventoryTransaction(Inventorytransaction inventorytransaction);
		
	public List<Inventorytransaction> queryAllInventoryTransaction();

	public List<Inventorytransaction> queryInventoryTransactionByItem(String item);
	
	public List<String> queryAllDate();
	
	public List<Inventorytransaction> queryInventoryTransactionByDate(String data);
	
	public List<Inventorytransaction> queryInventoryTransactionByDateOnBetween(@Param("date_1")String date_1,@Param("date_2")String date_2);
	
	public Sum queryInventoryTransactionByDateAndSun(@Param("date_1")String date_1,@Param("date_2")String date_2,@Param("item")String item);
	
	public List<String> queryInventoryTransactionGroupByItem();

}
