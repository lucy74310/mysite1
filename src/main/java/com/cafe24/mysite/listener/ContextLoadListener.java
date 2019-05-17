package com.cafe24.mysite.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//web.xml에 없을 시 
//@WebListener
public class ContextLoadListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent)  {
		String contextConfigLocation = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
		
		
		System.out.println("Container Starts..." + contextConfigLocation);
    }
	public void contextDestroyed(ServletContextEvent arg0)  {
    	
    }

    
	
}
