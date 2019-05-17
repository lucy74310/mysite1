package com.cafe24.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.action.guestbook.GuestbookActionFactory;
import com.cafe24.web.mvc.Action;


public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글깨짐 해결 - 필터에서 
		//request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		Action action = new GuestbookActionFactory().getAction(actionName);
		action.execute(request, response);
			
//		if("add".equals(actionName)) {
//			
//			//넘어오는 값은 모두다 string
//			String name = request.getParameter("name");
//			String password = request.getParameter("password");
//			String message = request.getParameter("message");
//			
//			GuestbookVo vo = new GuestbookVo();
//			vo.setName(name);
//			vo.setPassword(password);
//			vo.setMessage(message);
//			
//			new GuestbookDao().insert(vo);
//			
//			//리다이렉트 정보를 보낸다.
//			WebUtil.redirect(request,response, request.getContextPath()+"/guestbook");
//			
//		} else if("deleteform".equals(actionName)) {
//			
//			String no = request.getParameter("no");
//			request.setAttribute("no", no);
//			
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
//			
//		} else if("delete".equals(actionName)) {
//			String no = request.getParameter("no");
//			String password = request.getParameter("password");
//			
//			GuestbookVo vo = new GuestbookVo();
//			
//			vo.setNo(Long.parseLong(no));
//			vo.setPassword(password);
//			
//			new GuestbookDao().delete(vo);
//			
//			WebUtil.redirect(request,response, request.getContextPath()+"/guestbook");
//		} else {
//			//list action
//			GuestbookDao dao = new GuestbookDao();
//			List<GuestbookVo> list = dao.getList();
//			
//			request.setAttribute("list", list);
//			
//			
//			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
//		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
