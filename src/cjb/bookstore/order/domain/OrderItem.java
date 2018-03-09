package cjb.bookstore.order.domain;

import java.util.List;

import cjb.bookstore.book.domain.Book;

public class OrderItem {
	private String iid;
	private int count;//数量
	private double subtotal;//小计
	private Order order;//所属订单
	private Book book;//要购买的图书
	
	private List<OrderItem> orderItemList ;//当前订单下所有条目
	
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public OrderItem(String iid, int count, double subtotal, Order order, Book book) {
		super();
		this.iid = iid;
		this.count = count;
		this.subtotal = subtotal;
		this.order = order;
		this.book = book;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderItem [iid=" + iid + ", count=" + count + ", subtotal=" + subtotal + ", order=" + order + ", book="
				+ book + "]";
	}
	
	

}
