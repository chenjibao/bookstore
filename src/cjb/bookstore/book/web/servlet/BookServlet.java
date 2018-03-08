package cjb.bookstore.book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cjb.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService bookService=new BookService();
	/**
	 * 查询所有图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}
}
