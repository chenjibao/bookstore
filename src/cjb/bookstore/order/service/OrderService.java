package cjb.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

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
	/**
	 * 我的订单
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public List<Order> myOrders(String uid) throws SQLException {
		return orderDao.findByUid(uid);
	}
	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		return orderDao.load(oid);
	}
	/**
	 * 确认收货
	 * @param oid
	 * @throws OrderException
	 */
	public void confirm(String oid)throws OrderException{
		//校验订单状态，如果不是3，抛出异常
		int state =orderDao.getStateByOid(oid);
		if(state!=3)throw new OrderException("订单确认失败！");
		
		orderDao.updateState(4, oid);
	}
	
	/**
	 * 支付方法
	 * @param oid
	 */
	public void pay(String oid){
		/*
		 * 获取订单状态
		 * 如果订单状态为1就执行下面内容，否则什么都不做
		 */
		int state =orderDao.getStateByOid(oid);
		if(state==1){
			orderDao.updateState(2, oid);
		}
	}
}
