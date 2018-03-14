package cjb.bookstore.category.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cjb.bookstore.category.domain.Category;
import cjb.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	private CategoryService categoryService=new CategoryService();
	/**
	 * 添加分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		categoryService.add(category);
		return findAll(request, response);
	}
	
	/**
	 * 查询所有分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 调用servlce方法，得到分类
		 * 保存到request域转发到/adminjsps/admin/category/list.jsp
		 */
		request.setAttribute("categoryList", categoryService.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
		
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cid=request.getParameter("cid");
			categoryService.delete(cid);
			return findAll(request, response);
		} catch (CategoryException e) {
			request.setAttribute("msg", e.getMessage());
			return "/adminjsps/msg.jsp";
		}
	}
	
	/**
	 * 修改分类之前
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String cid=request.getParameter("cid");
			Category category=categoryService.getCategoryById(cid);
			request.setAttribute("category", category);
			return "f:/adminjsps/admin/category/mod.jsp";
		} catch (CategoryException e) {
			request.setAttribute("msg", e.getMessage());
			return "/adminjsps/msg.jsp";
		}
	}
	/**
	 * 修改分类
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Category category=CommonUtils.toBean(request.getParameterMap(), Category.class);
			categoryService.edit(category);
			return findAll(request,response);
		} catch (CategoryException e) {
			request.setAttribute("msg", e.getMessage());
			return "/adminjsps/msg.jsp";
		}
	}

}
