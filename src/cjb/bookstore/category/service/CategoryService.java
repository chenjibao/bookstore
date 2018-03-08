package cjb.bookstore.category.service;

import java.util.List;

import cjb.bookstore.category.dao.CategoryDao;
import cjb.bookstore.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao=new CategoryDao();
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll(){
		return categoryDao.findAll();
	}
}
