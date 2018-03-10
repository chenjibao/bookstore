package cjb.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cjb.bookstore.book.domain.Book;
import cjb.bookstore.order.domain.Order;
import cjb.bookstore.order.domain.OrderItem;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr=new TxQueryRunner();
	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Order order){
			try {
				String sql="insert into orders values(?,?,?,?,?,?)";
				//处理util的Date转换为sql的Timestamp
				Timestamp timestamp=new Timestamp(order.getOrdertime().getTime());
				Object [] params={order.getOid(),timestamp,order.getTotal(),order.getState(),order.getOwner().getUid(),order.getAddress()};
				qr.update(sql, params);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}
	
	/**
	 * 插入订单条目
	 * @param orderItemList
	 */
	public void addOrderItemList(List<OrderItem> orderItemList){
		try {
			String sql="insert into orderitem values(?,?,?,?,?)";
			Object [][] params=new Object[orderItemList.size()][];
			for (int i = 0; i < orderItemList.size(); i++) {
				OrderItem item=orderItemList.get(i);
				params[i]=new Object[]{
						item.getIid(),
						item.getCount(),
						item.getSubtotal(),
						item.getOrder().getOid(),
						item.getBook().getBid()};
			}
			qr.batch(sql, params);//执行批处理
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 按uid查询orders表
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	public List<Order> findByUid(String uid) throws SQLException {
		/*
		 * 1、通过uid查询出当前用户的所欲List<Order>
		 * 2、循环遍历每个order,为其加载他所有的OrderItem
		 */
		
			//1得到当前用户的所有订单
			String sql="select * from orders where uid=?";
			List<Order> orderList=qr.query(sql, new BeanListHandler<Order>(Order.class),uid);
			//2遍历每个订单order，为他加载自己所有的订单条目orderItem
			for (Order order : orderList) {
				loadOrderItems(order);//为order对象添加他的所有订单条目
			}
			return orderList;
	}
	/**
	 * 加载指定订单所有订单条目
	 * @param order
	 */
	private void loadOrderItems(Order order) {
		/*
		 * 需要查询两张表orderitem、book，因为orderitem表中没有book，所以需要通过bid关联
		 * 因为一行记录对应的不再是一个JavaBean，所以不能在使用BeanListHandler，而是MapListHandler
		 */
		try {
			String sql="select * from orderitem i,book b where i.bid=b.bid and oid=?";
			//一个map就对应一行记录
			List<Map<String,Object>> mapList=qr.query(sql, new MapListHandler(),order.getOid());
			//需要使用一个map生成两个对象OrderItem、Book，把Book设置给OrderItem，最终结果只有一个OrderItem
			//把mapList转换成List<OrderItem>
			List<OrderItem> orderItemList=toOrderItemList(mapList);
			order.setOrderItemList(orderItemList);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 把mapList中的每个map转换成两个对象并简练关系
	 * @param mapList
	 * @return
	 */
	private List<OrderItem> toOrderItemList(List<Map<String, Object>> mapList) {
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		for (Map<String,Object> map : mapList) {
			OrderItem orderItem=toOrderItem(map);
			orderItemList.add(orderItem);
		}
		return orderItemList;
	}
	/**
	 * 把一个map转化成一个OrderItem对象
	 * @param map
	 * @return
	 */
	private OrderItem toOrderItem(Map<String, Object> map) {
		OrderItem orderItem=CommonUtils.toBean(map, OrderItem.class);
		Book book=CommonUtils.toBean(map, Book.class);
		orderItem.setBook(book);
		return orderItem ;
	}
	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) {
		try {
			String sql="select * from orders where oid=?";
			//得到订单（按oid）
			Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
			//加载该订单的所有条目
			loadOrderItems(order);
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 通过oid查询订单状态
	 * @param oid
	 * @return
	 */
	public int getStateByOid(String oid){
		try {
			String sql="select state from orders where oid=?";
			return (Integer)qr.query(sql, new ScalarHandler(), oid);
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	
	/**
	 *修改订单状态
	 * @param oid
	 * @return
	 */
	public void updateState(int state,String oid){
		try {
			String sql="update orders set state=? where oid=?";
			qr.update(sql, state,oid); 
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
}
