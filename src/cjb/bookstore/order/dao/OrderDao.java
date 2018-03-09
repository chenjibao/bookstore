package cjb.bookstore.order.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cjb.bookstore.order.domain.Order;
import cjb.bookstore.order.domain.OrderItem;
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
}
