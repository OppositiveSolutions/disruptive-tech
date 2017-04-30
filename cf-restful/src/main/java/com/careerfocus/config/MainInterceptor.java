package com.careerfocus.config;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.careerfocus.constants.Constants;
import com.careerfocus.util.response.Response;

public class MainInterceptor extends HandlerInterceptorAdapter {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	// private static String EXPIRED_KEY =
	// "org.springframework.session.security.SpringSessionBackedSessionInformation.EXPIRED";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!hasAuthorisation(request, response))
			return false;

		setResponseHeaders(response);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	private boolean hasAuthorisation(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (requestUriRequiresSession(request.getRequestURI())
				&& (session == null || !request.isRequestedSessionIdValid() || session.getAttribute("role") == null)) {
			setUnauthorizedResponse(response);
			return false;
		}

		if (session != null && session.getAttribute("role") != null) {
			int role = (Integer) session.getAttribute("role");
			if (!roleHasAuthorisation(role, request.getRequestURI(), request.getMethod())) {
				setUnauthorizedResponse(response);
				return false;
			}
		} //
		return true;
	}

	private boolean requestUriRequiresSession(String uri) {
		log.info(uri);
		// api's which can be accessed without session goes here.
		if (uri.equals(Constants.RESTFUL_PATH_PREFIX + "/login")) {
			return false;
		}
		return true;
	}

	private void setUnauthorizedResponse(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json");
		Response unAuthorizedResponse = Response.unauthorized().build();
		try {
			PrintWriter out = response.getWriter();
			out.println(unAuthorizedResponse.toJsonString());
		} catch (IOException e) {
			log.error("Error", e);
		}

	}

	private boolean roleHasAuthorisation(int role, String uri, String requestMethod) {
		switch (role) {
		case Constants.ROLE_STUDENT:
			return checkAutorisationForStudent(uri, requestMethod);
		case Constants.ROLE_SUPER_ADMIN:
			return checkAutorizationForSuperAdmin(uri, requestMethod);
		case Constants.ROLE_BRANCH_ADMIN:
			return checkAutorisationForBranchAdmin(uri, requestMethod);
		}
		return false;
	}

	private boolean checkAutorisationForStudent(String uri, String requestMethod) {
		PathMatcher pathMatcher = new AntPathMatcher();
		if (pathMatcher.match(Constants.RESTFUL_PATH_PREFIX + "/video-tutorial*", uri))
			return true;
		return false;
	}

	private boolean checkAutorizationForSuperAdmin(String uri, String requestMethod) {

		PathMatcher pathMatcher = new AntPathMatcher();
		if (pathMatcher.match(Constants.RESTFUL_PATH_PREFIX + "/category*", uri)
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/common")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/question-paper"))
			return true;
		return false;
	}

	private boolean checkAutorisationForBranchAdmin(String uri, String requestMethod) {
		// PathMatcher pathMatcher = new AntPathMatcher();
		// if (pathMatcher.match("/cf-restful/video-tutorial*", uri))
		return true;
		// return false;
	}

	private void setResponseHeaders(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		// response.setDateHeader("Expires", 3600);
	}

}
