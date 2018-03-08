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

}
