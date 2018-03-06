package cjb.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cjb.bookstore.user.domain.User;
import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr=new TxQueryRunner();
	
	/**
	 * 按用户名查询
	 * @param username
	 * @return
	 */
	public User findByUsername(String username){
		try {
			String sql="select * from tb_user where username=?";
			return qr.query(sql, new BeanHandler<User>(User.class), username);
		} catch (SQLException e) {
	
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 按邮箱查询
	 * @param email
	 * @return
	 */
	public User findByEmail(String email){
		try {
			String sql="select * from tb_user where email=?";
			return qr.query(sql, new BeanHandler<User>(User.class), email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 插入user
	 * @param user
	 */
	public void add(User user){
		try {
			String sql="insert into tb_user values(?,?,?,?,?,?)";
			Object [] params={
					user.getUid(),
					user.getUsername(),
					user.getPassword(),
					user.getEmail(),
					user.getCode(),
					user.isState()
			};
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
