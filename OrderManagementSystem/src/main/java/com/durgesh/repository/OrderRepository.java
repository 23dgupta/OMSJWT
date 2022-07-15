package com.durgesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.durgesh.model.OrderDetails;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderDetails, Integer> {

	//

	List<OrderDetails> findBybrand(String brand);

	List<OrderDetails> findOrderNameByCustomerName(String customerName);

	
	//all col data fetch OrderDetails return type

	// @Query(value ="select * from OrderDetails where isActive=:isActive")
	OrderDetails findAllByIsActive(Boolean isActive);
	
	//@Query(value="select order_name,price,brand from order_details Where customer_name=:customerName",nativeQuery = true)
	//List<Object> findByCustomerName(@Param("customerName") String customerName);
	
	@Query("select orderName,price,brand from OrderDetails Where customerName=:customerName")
	List<Object> findByCustomerName(String customerName);
	
	

}
