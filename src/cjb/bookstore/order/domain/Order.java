package cjb.bookstore.order.domain;

import java.util.Date;

import cjb.bookstore.user.domain.User;

public class Order {
	
	private String oid;//订单编号
	private Date ordertime;//下单时间
	private double total;//合计
	private int state;//订单状态有4种：1、未付款2、已付款但未发货3、已发货但未确认收货4、已经确认交易成功
	private User owner;//订单所有者
	private String address;//收货地址
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Order(String oid, Date ordertime, double total, int state, User owner, String address) {
		super();
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.state = state;
		this.owner = owner;
		this.address = address;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", owner="
				+ owner + ", address=" + address + "]";
	}
	
	

}
