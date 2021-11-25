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

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
/*		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("login");
		
		if(ObjectUtils.isEmpty(user)) {
			throw new LoginException("로그인 해주세요.","CRUD/user/login.do");
		} else {
			return true;
		} */
		return true;
	}
	
}
