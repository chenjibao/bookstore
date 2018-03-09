package cjb.bookstore.order.service;

import java.sql.SQLException;

import cjb.bookstore.order.dao.OrderDao;
import cjb.bookstore.order.domain.Order;
import cn.itcast.jdbc.JdbcUtils;

public class OrderService {
	private OrderDao orderDao=new OrderDao();
	/**
	 * 添加订单
	 * 需要处理事务
	 * @param order
	 */
	public void addOrder(Order order){
		try {
			//开启事务
			JdbcUtils.beginTransaction();
			orderDao.addOrder(order);//插入订单
			orderDao.addOrderItemList(order.getOrderItemList());//插入订单中所有条目
			
			//提交事务
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			//回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
			}
			throw new RuntimeException(e);
		}
	}
}
