package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		//HttpSession session = request.getSession(true); // true : 없으면 만들어 달라 , false : 없으면 널 리턴 (최초에는 true 넣자)
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authUser = new UserDao().get(email, password);
		
		if(authUser == null) {
			request.setAttribute("result", "fail");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		} 
		
		// 로그인 처리 
		HttpSession session = request.getSession(true); // true : 없으면 만들어 달라 , false : 없으면 널 리턴 (최초에는 true 넣자)
		session.setAttribute("authUser", authUser);
		
		WebUtil.redirect(request, response, request.getContextPath() + "/main");
		
	}

}
