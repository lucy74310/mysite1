package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mysite.vo.UserVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		UserVo vo = (UserVo) session.getAttribute("authUser");
		if(session == null || vo == null ) {
			WebUtil.redirect(request, response, request.getContextPath()+"/board");
			return;
		}
		
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");

	}

}
