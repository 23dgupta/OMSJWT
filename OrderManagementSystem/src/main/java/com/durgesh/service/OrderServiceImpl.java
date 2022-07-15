package com.durgesh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.durgesh.exception.OrderNotFoundException;
import com.durgesh.model.OrderDetails;
import com.durgesh.repository.OrderRepository;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	
	@Override
    public List<OrderDetails> findPaginated(int pageNo, int pageSize) {

        PageRequest paging =  PageRequest.of(pageNo, pageSize);
        Page<OrderDetails> pagedResult = orderRepository.findAll(paging);
        return pagedResult.toList();
    }

	@Override
	public Integer saveOrder(OrderDetails order) {
		// TODO Auto-generated method stub
		return orderRepository.save(order).getOrderId();
	}

	@Override
	public List<OrderDetails> getAllOrderDetails() {
		// TODO Auto-generated method stub
		List<OrderDetails> allProduct = (List<OrderDetails>) orderRepository.findAll();
		List<OrderDetails> aP = new ArrayList<>();
		for (OrderDetails productList : allProduct) {
			
			if (productList.getIsActive() == true) {
				aP.add(productList);
			}

		}
		return aP;
	}

	@Override
	public OrderDetails getOneOrderDetails(Integer id) {
		// TODO Auto-generated method stub
		/*
		 * Optional<OrderDetails> opt = orderRepository.findById(id);
		 * if(opt.isPresent()) { OrderDetails od = opt.get(); return od; }else { throw
		 * new OrderNotFoundException("Order Details "+id+"  Not Exist"); }
		 * 
		 */
		return orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order Details " + id + "  Not Exist"));
	}

	@Override
	public void deleteOrderDetails(Integer id) {
		orderRepository.delete(orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Order Details " + id + "  Not Exist")));

	}

	@Override
	public void updateOrderDetails(OrderDetails orderDetails) {
		orderRepository.save(orderDetails);

	}

	@Override
	public boolean isOrderDetailsExist(Integer id) {
		// TODO Auto-generated method stub
		return orderRepository.existsById(id);
	}

	@Override
	public List<OrderDetails> getBybrand(String brand) {
		// TODO Auto-generated method stub
		return orderRepository.findBybrand(brand);
	}

	@Override
	public List<OrderDetails> getOrderNameByCustomerName(String customerName) {
		// TODO Auto-generated method stub
		return orderRepository.findOrderNameByCustomerName(customerName);
	}

	@Override
	public Integer disAbleProduct(Integer id) {
		// TODO Auto-generated method stub
		OrderDetails existId = orderRepository.findById(id).get();
		existId.setIsActive(false);

		return orderRepository.save(existId).getOrderId();

	}

	@Override
	public Integer enableAbleProduct(Integer id) {
		// TODO Auto-generated method stub
		OrderDetails existId = orderRepository.findById(id).get();
		existId.setIsActive(true);

		return orderRepository.save(existId).getOrderId();

	}
	
	public List<Object> getByCustomerName(String customerName){
		
		return  orderRepository.findByCustomerName(customerName);
		
		
	}

	@Override
	public List<OrderDetails> getAllOrderDetailsInExcelAndPdf() {
		// TODO Auto-generated method stub
		return (List<OrderDetails>) orderRepository.findAll();
	}
	
		

}
