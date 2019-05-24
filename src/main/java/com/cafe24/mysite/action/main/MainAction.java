package com.cafe24.mysite.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.web.WebUtil;
import com.cafe24.web.mvc.Action;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/* Cookie Test */
		// 몇번 방문 했는지 visitCount 라는 이름의 쿠키 
		// visitCount=0 
		
		int count = 0;
		
		// 쿠키읽기
		// Request Header에 붙어있음
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if("visitCount".equals(c.getName())) {
					count = Integer.parseInt(c.getValue());
				}
				
			}
			
		}
		
		// 쿠키쓰기 
		// (Response Header) Set-Cookie: visitCount=1; Max-Age=86400; Expires=Wed, 15-May-2019 04:33:21 GMT; Path=/mysite1
		count++;
		Cookie cookie = new Cookie("visitCount", String.valueOf(count)); // string으로 넣어야함 or ""+count
		cookie.setMaxAge(24*60*60);//24시간
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie); 
		
		// 이렇게 만든 쿠키를 다른 요청 때 보냄 
		// (Request Header) Cookie: JSESSIONID=EE8220AC477D7830DA98EC863E847CD0; visitCount=1; _ga=GA1.1.2075130250.1557725021; _gid=GA1.1.192495303.1557725021; wcs_bt=s_36b22049259b:1557725170

		
		// 없애고 싶으면 setMaxAge 0 으로 하면 됨
		// cookie.setMaxAge(0);
		WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
	}

}
