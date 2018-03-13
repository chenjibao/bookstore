package cjb.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cjb.bookstore.book.domain.Book;
import cjb.bookstore.category.domain.Category;
import cn.itcast.commons.CommonUtils;
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
			//需要在book对象中保存category的信息
			Map<String,Object> map=qr.query(sql, new MapHandler(), bid);
			Category category=CommonUtils.toBean(map, Category.class);
			//使用一个map映射两个对象，再给这两个对象建立关系
			Book book=CommonUtils.toBean(map, Book.class);
			book.setCategory(category);
			return book; 
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
