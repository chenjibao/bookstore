
package cjb.bookstore.book.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cjb.bookstore.book.domain.Book;
import cjb.bookstore.book.service.BookService;
import cjb.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

public class AdminBookServlet extends BaseServlet {
	private BookService bookService=new BookService();
	private CategoryService categoryService=new CategoryService();
	/**
	 * 查看所有图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("bookList", bookService.findAll());
		return "f:/adminjsps/admin/book/list.jsp";
	}
	
	/**
	 * 加载图书
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid=request.getParameter("bid");
		Book book=bookService.load(bid);
		request.setAttribute("categoryList", categoryService.findAll());
		request.setAttribute("book", book);
		return "f:/adminjsps/admin/book/desc.jsp";
	}
	
}
