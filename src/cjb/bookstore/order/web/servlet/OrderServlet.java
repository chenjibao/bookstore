package cjb.bookstore.order.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cjb.bookstore.cart.domain.Cart;
import cjb.bookstore.cart.domain.CartItem;
import cjb.bookstore.order.domain.Order;
import cjb.bookstore.order.domain.OrderItem;
import cjb.bookstore.order.service.OrderService;
import cjb.bookstore.user.domain.User;
import cn.itcast.commons.CommonUtils;
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
		/*
		 * 1、从session中得到cart
		 * 2、使用cart生成order对象
		 * 3、使用service方法完成添加订单
		 * 4保存order到request域中，转发到“/jsps/order/desc.jsp”
		 */
		//从session中获取Cart
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		//创建订单
		Order order=new Order();
		order.setOid(CommonUtils.uuid());//设置编号
		order.setOrdertime(new Date());//设置下单时间
		order.setTotal(cart.getTotal());//设置订单合计
		order.setState(1);//设置订单状态为1，表示未付款
		User user=(User)request.getSession().getAttribute("session_user");
		order.setOwner(user);//设置订单所有者
		
		//创建订单条目集合
		List<OrderItem> orderItemList=new ArrayList<OrderItem>();
		
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem oi=new OrderItem();
			oi.setIid(CommonUtils.uuid());
			oi.setOrder(order);
			oi.setCount(cartItem.getCount());
			oi.setBook(cartItem.getBook());
			oi.setSubtotal(cartItem.getSubTotal());
			
			orderItemList.add(oi);//把订单条目添加到集合中
		}
		//把所有的订单条目添加到订单中
		order.setOrderItemList(orderItemList);
		//清空购物车
		cart.clear();
		//调用service方法添加订单
		orderService.addOrder(order);
		//order保存到request域中，转发到“/jsps/order/desc.jsp”
		request.setAttribute("order", order);
		
		return "/jsps/order/desc.jsp";
	}
}
