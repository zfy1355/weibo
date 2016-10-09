package org.weibo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.weibo.entity.User;
import org.weibo.service.UserService;
import org.weibo.util.SessionManager;

public class LoginFilter implements Filter{
	UserService userService = new UserService();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String[] excludedPageArray = {"/registerUser","/login"};
		boolean isExcludedPage = false;     
		for (String page : excludedPageArray) {//判断是否在过滤url之外     
			if(((HttpServletRequest) request).getServletPath().equals(page)){     
				isExcludedPage = true;     
				break;     
			}     
		}  
		if(!isExcludedPage){
			Cookie[] cookies =( (HttpServletRequest)request).getCookies();
			String username = null;
			for(Cookie cookie : cookies){
				if("username".equals(cookie.getName()))
					username = cookie.getValue();
			}
			if(StringUtils.isEmpty(username) || userService.getUserByUsername(username)==null){
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}else{
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
