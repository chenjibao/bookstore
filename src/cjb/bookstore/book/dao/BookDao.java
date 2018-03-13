package cjb.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cjb.bookstore.book.domain.Book;
import cn.itcast.jdbc.TxQueryRunner;

public class BookDao {
	private QueryRunner qr=new TxQueryRunner();
	/**
	 * 查询所有图书
	 * @return
	 */
	public List<Book> findAll(){
		String sql ="select * from  book";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 按分类查询
	 * @param cid
	 * @return
	 */
	public List<Book> findByCategory(String cid) {
		String sql ="select * from  book where cid=?";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class),cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按bid查询
	 * @param bid
	 * @return Book
	 */
	public Book findByBid(String bid) {
		String sql ="select * from  book where bid=?";
		try {
			return qr.query(sql, new BeanHandler<Book>(Book.class),bid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//查询指定分类下的图书本数
	public int getCountByCid(String cid) {
		try {
			String sql="SELECT COUNT(*) FROM book WHERE cid=?";
			Number cnt=(Number)qr.query(sql, new ScalarHandler(),cid);
			return cnt.intValue();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
