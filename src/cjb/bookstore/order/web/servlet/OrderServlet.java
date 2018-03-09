package cjb.bookstore.order.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cjb.bookstore.order.service.OrderService;
import cn.itcast.servlet.BaseServlet;

public class OrderServlet extends BaseServlet {
	private OrderService orderService =new OrderService();
	
	/**
	 * 添加订单：把session中的车用来生成订单
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String addOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return null;
	}
}
