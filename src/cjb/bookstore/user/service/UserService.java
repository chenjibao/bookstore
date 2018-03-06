package cjb.bookstore.user.service;

import cjb.bookstore.user.dao.UserDao;
import cjb.bookstore.user.domain.User;

public class UserService {
	private UserDao userDao=new UserDao();
	
	/**
	 * 注册功能
	 * @param form
	 * @throws UserException
	 */
	public void regist(User form )throws UserException{
		//校验用户名
		User user=userDao.findByUsername(form.getUsername());
		if(user!=null) throw new UserException("用户名已被注册！");
		
		//校验邮箱
		user=userDao.findByEmail(form.getEmail());
		if(user!=null) throw new UserException("邮箱已被注册！");
	
		//插入用户到数据库
		userDao.add(form);
	}
	
	public void active(String code)throws UserException{
		User user =userDao.findByCode(code);
		if(user==null)throw new UserException("用户不存在，激活码无效");
		if(user.isState())throw new UserException("您已经激活过了，不用再激活了");
		//修改用户状态
		userDao.updateState(user.getUid(), true);
	}
}
