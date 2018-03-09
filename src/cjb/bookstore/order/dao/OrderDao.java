package cjb.bookstore.order.dao;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

public class OrderDao {
	private QueryRunner qr=new TxQueryRunner();
}
