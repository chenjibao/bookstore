package cjb.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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

}
