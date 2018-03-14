package cjb.bookstore.category.service;

import java.util.List;

import cjb.bookstore.book.dao.BookDao;
import cjb.bookstore.category.dao.CategoryDao;
import cjb.bookstore.category.domain.Category;
import cjb.bookstore.category.web.servlet.admin.CategoryException;

public class CategoryService {
	private CategoryDao categoryDao=new CategoryDao();
	private BookDao bookDao=new BookDao();
	/**
	 * 添加分类
	 * @param category
	 */
	public void add(Category category) {
		categoryDao.add(category);
	}
	
	
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll(){
		return categoryDao.findAll();
	}
	/**
	 * 删除分类
	 * @param parameter
	 */
	public void delete(String cid) throws CategoryException {
		//获取该分类下的图书数目
		int count=bookDao.getCountByCid(cid);
		//如果该分类下还有图书，不能删除
		if(count>0)throw new CategoryException("该分类下还有图书，不能删除！");
		//删除图书
		categoryDao.delete(cid);
	}
	public Category getCategoryById(String cid) throws CategoryException{
		return categoryDao.findById(cid);
	}
	/**
	 * 修改分类
	 * @param category
	 */
	public void edit(Category category) throws CategoryException{
		Category category2=categoryDao.findById(category.getCid());
		if(category2==null)throw new CategoryException("不存在该分类，您的服务器后端代码错误！");
		categoryDao.edit(category);
	}
}
