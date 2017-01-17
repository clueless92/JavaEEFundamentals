package bg.tilchev.web.filter;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bg.tilchev.entity.User;

//import bg.tilchev.dto.UserDto;

public class AuthenticationFilter implements Filter, Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PATH_INDEX = "/index.jsp";
	public static final String PATH_LOGIN = "/page/login.html";
	public static final String PATH_LOGOUT = "/page/logout.jsp";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {
	}

	/**
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String requestedPath = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

		/*
		 * Skip the action with path "index.jsp" - there is no logged user, but
		 * the user is redirected to login screen
		 */
		if (PATH_INDEX.equals(requestedPath)) {
			chain.doFilter(request, response);
			return;
		}
		/*
		 * Skip the action with path "/login.login.html" - there is no logged
		 * user, but the user is trying to log in
		 */
		if (PATH_LOGIN.equals(requestedPath)) {
			chain.doFilter(request, response);
			return;
		}

		/*
		 * Get logged user from the HttpSession
		 */
		HttpSession session = httpRequest.getSession();
		User loggedUsername = (User) session.getAttribute("LOGGED_USER");

		/*
		 * Redirect to login page if there is no logged user and trying to
		 * access protected resource
		 */
		if (loggedUsername == null) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH_LOGIN);
			requestDispatcher.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
			return;
		}
	}
}
