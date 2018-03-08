package cjb.bookstore.book.service;

import java.util.List;

import cjb.bookstore.book.dao.BookDao;
import cjb.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao=new BookDao();
	/**
	 * 查询所有图书
	 * @return
	 */
	public List<Book> findAll(){
		return bookDao.findAll();
	}
	/**
	 * 按分类查询
	 * @param cid
	 * @return
	 */
	public List<Book> findByCategory(String cid) {
		return bookDao.findByCategory(cid);
	}
	public Book load(String bid) {
		return bookDao.findByBid(bid);
	}

}
