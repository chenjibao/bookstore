package cjb.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map=new LinkedHashMap<String, CartItem>();
	/**
	 * 计算合计：合计是所有条目的小计之和
	 * @return
	 */
	public double getTotal(){
		BigDecimal total=new BigDecimal("0");
		for (CartItem cartItem : map.values()) {
			BigDecimal subtotal=new BigDecimal(cartItem.getSubTotal()+"");
			total=total.add(subtotal);
		}
		return total.doubleValue();
	}
	/**
	 * 添加条目
	 * @param cartItem
	 */
	public void add(CartItem cartItem){
		if(map.containsKey(cartItem.getBook().getBid())){
			CartItem _cartItem=map.get(cartItem.getBook().getBid());
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
			map.put(cartItem.getBook().getBid(), _cartItem);
		}else{
			map.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	/**
	 * 清空购物车
	 */
	public void clear(){
		map.clear();
	}
	
	/**
	 * 按bid删除条目
	 * @param bid
	 */
	public void delete(String bid){
		map.remove(bid);
	}
	
	/**
	 * 返回所有条目
	 * @return
	 */
	public Collection<CartItem> getCartItems(){
		return map.values();
	}

}
