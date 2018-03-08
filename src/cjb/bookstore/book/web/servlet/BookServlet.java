package cjb.bookstore.book.web.servlet;

import cjb.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService bookService=new BookService();
}
