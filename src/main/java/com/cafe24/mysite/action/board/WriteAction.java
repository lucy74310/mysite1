package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		BoardVo vo = new BoardVo();
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContents(contents);
		
		new BoardDao().insert(vo);
		
		WebUtil.redirect(request, response, request.getContextPath() + "/board");
		
		//리스트 가져와 줘야 하니까 redirect로 
		//WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
