package com.durgesh.service;

import java.util.List;

import com.durgesh.model.OrderDetails;

public interface IOrderService {
	
	Integer saveOrder(OrderDetails order);
	
	List<OrderDetails> getAllOrderDetails();
	List<OrderDetails> getAllOrderDetailsInExcelAndPdf();
	
	OrderDetails getOneOrderDetails(Integer id);
	void deleteOrderDetails(Integer id);
	
	 List<OrderDetails> findPaginated(int pageNo, int pageSize);
	
	//void updateOrderDetails(Integer id,OrderDetails orderDetails);
	void updateOrderDetails(OrderDetails orderDetails);
	
	boolean isOrderDetailsExist(Integer id);
	
	List<OrderDetails> getBybrand(String brand);
	
	List<OrderDetails> getOrderNameByCustomerName(String customerName);
	
	Integer disAbleProduct(Integer id);
	
	
	Integer enableAbleProduct(Integer id);
	 
	 //custom column
	 List<Object> getByCustomerName(String customerName);
	 
	
	

}
