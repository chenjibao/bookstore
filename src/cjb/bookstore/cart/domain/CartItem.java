package cjb.bookstore.cart.domain;

import java.math.BigDecimal;

import cjb.bookstore.book.domain.Book;

public class CartItem {
	private Book book;
	private int count;
	/**
	 * 小计方法，但他没有对应的成员
	 * @return
	 */
	public double getSubTotal(){
		BigDecimal d1=new BigDecimal(book.getPrice()+"");
		BigDecimal d2=new BigDecimal(count+"");
		return d1.multiply(d2).doubleValue();
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public CartItem(Book book, int count) {
		super();
		this.book = book;
		this.count = count;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
	

}
