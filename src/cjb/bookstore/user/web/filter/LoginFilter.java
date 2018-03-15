package cjb.bookstore.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cjb.bookstore.user.domain.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 从session中获取用户信息
		 * 	1、如果session中存在用户信息，方形
		 *  2、否则保存错误信息，转发到login.jsp
		 */
		HttpServletRequest httpRequest =(HttpServletRequest)request;
		User user=(User)httpRequest.getSession().getAttribute("session_user");
		if(user!=null){
			chain.doFilter(request, response);
		}else{
			request.setAttribute("msg", "您还没有登录");
			httpRequest.getRequestDispatcher("/jsps/user/login.jsp").forward(httpRequest, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
