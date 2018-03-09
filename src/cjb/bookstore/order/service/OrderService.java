package cjb.bookstore.order.service;

import cjb.bookstore.order.dao.OrderDao;
import cjb.bookstore.order.domain.Order;

public class OrderService {
	private OrderDao orderDao=new OrderDao();
	/**
	 * 添加订单
	 * 需要处理事务
	 * @param order
	 */
	public void addOrder(Order order){
		
	}
}
