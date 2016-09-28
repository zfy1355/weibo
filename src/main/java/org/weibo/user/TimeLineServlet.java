package org.weibo.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.weibo.util.RedisUtils;

@WebServlet("/timeline")
public class TimeLineServlet extends BaseServlet{

	private static final long serialVersionUID = 8089875410008505689L;
	
	public void doPost()
			throws ServletException, IOException {
			List users  = RedisUtils.getNew10UserList();
			request.setAttribute("users", users);
			request.getRequestDispatcher("/timeline.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
