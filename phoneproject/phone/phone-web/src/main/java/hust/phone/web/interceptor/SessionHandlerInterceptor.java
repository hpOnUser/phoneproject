package hust.phone.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 系统登录验证
 * 
 * @author WJC
 */

public class SessionHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object userId =request.getSession().getAttribute("login_user");
		if (userId != null) {
			return true;
		}
		if (isAjax(request)) {
			response.setCharacterEncoding("UTF-8");
			response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
			return false;
		}
		response.sendRedirect(request.getContextPath() + "/login");
		return false;
	}

	private boolean isAjax(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equals(requestType)) {
			return true;
		} else {
			return false;
		}
	}

}
