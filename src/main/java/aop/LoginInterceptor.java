package aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import dto.User;
import exception.LoginException;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter{
	/* 페이지 요청 정보 저장. */
	private void saveDestination(HttpServletRequest request) {
		String uri = request.getRequestURI(); //이전 페이지 정보 저장.
		String query = request.getQueryString();
		if(query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?" + query;
		}
		
		if(request.getMethod().equals("GET")) {
			System.out.println("destination = " + (uri + query));
			request.getSession().setAttribute("destination", uri + query);
		}
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login");
		
		//로그인 안한 경우
		if(ObjectUtils.isEmpty(user)) {
			response.sendRedirect("localhost:8080/CRUD/user/login.do");
			return false;
		} else {
			return true;
		}  

	}
}
