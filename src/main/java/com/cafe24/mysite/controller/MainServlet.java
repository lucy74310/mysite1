package com.cafe24.mysite.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mysite.action.main.MainActionFactory;
import com.cafe24.web.mvc.Action;

//@WebServlet({"", "/main", "/index"})
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		String configPath = getServletConfig().getInitParameter("config");
		System.out.println("init() called: " + configPath);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionName = request.getParameter("a");
		
		Action action = new MainActionFactory().getAction(actionName);
		action.execute(request, response);
		//1.
		
		//System.out.println("MainServlet:doGetCalled");
		//개행으로 바디 나눔 - 순서가 중요 
		//response.setContentType("text/html; charset=utf-8");
		//get writer 전에 써줘야 헤더로 들어감
		//PrintWriter pw = response.getWriter();
		//pw.println("<h1></h1>");
		
		
		
		//2.
		//RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/index.jsp"); // 이쪽으로 요청을 연장시킨다.
		//rd.forward(request, response);
		
		//3.
		//WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
