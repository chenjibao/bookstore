package cjb.bookstore.cart.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cjb.bookstore.book.domain.Book;
import cjb.bookstore.book.service.BookService;
import cjb.bookstore.cart.domain.Cart;
import cjb.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	/**
	 * 添加购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1、得到车
		 * 2、得到条目:book(先得到bid,再由bid得到book),count
		 * 3、把条目添加到车中
		 */
		
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		
		String bid =request.getParameter("bid");
		Book book=new BookService().load(bid);
		int count =Integer.parseInt(request.getParameter("count"));
		
		CartItem cartItem=new CartItem();
		cartItem.setBook(book);
		cartItem.setCount(count);
		
		cart.add(cartItem);
		
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 删除购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		String bid=request.getParameter("bid");
		cart.delete(bid);
		return "f:/jsps/cart/list.jsp";
	}
	
	/**
	 * 清空购物条目
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		cart.clear();
		return "f:/jsps/cart/list.jsp";
	}
}
