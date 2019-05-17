package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		Long no = Long.parseLong(request.getParameter("no"));
		
		new BoardDao().update(no, title, contents);
		
		WebUtil.redirect(request, response, request.getContextPath() + "/board");
	}

}
