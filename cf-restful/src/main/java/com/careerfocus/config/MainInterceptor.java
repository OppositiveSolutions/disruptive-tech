package com.careerfocus.config;

import com.careerfocus.constants.Constants;
import com.careerfocus.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			CommonUtils.setUnauthorizedResponse(response);
			return false;
		}

		if (session != null && session.getAttribute("role") != null) {
			int role = (Integer) session.getAttribute("role");
			if (!roleHasAuthorisation(role, request.getRequestURI(), request.getMethod())) {
				CommonUtils.setUnauthorizedResponse(response);
				return false;
			}
		}
		return true;
	}

	private boolean requestUriRequiresSession(String uri) {
		// api's which can be accessed without session goes here.
		return !uri.equals(Constants.RESTFUL_PATH_PREFIX + "/login");
	}

	private boolean roleHasAuthorisation(int role, String uri, String requestMethod) {
		switch (role) {
		case Constants.ROLE_STUDENT:
			return checkAuthorisationForStudent(uri, requestMethod);
		case Constants.ROLE_SUPER_ADMIN:
			return checkAuthorizationForSuperAdmin(uri, requestMethod);
		case Constants.ROLE_BRANCH_ADMIN:
			return checkAuthorisationForBranchAdmin(uri, requestMethod);
		}
		return false;
	}

	private boolean checkAuthorisationForStudent(String uri, String requestMethod) {
		PathMatcher pathMatcher = new AntPathMatcher();
		return pathMatcher.match(Constants.RESTFUL_PATH_PREFIX + "/login", uri)
				|| pathMatcher.matchStart(Constants.RESTFUL_PATH_PREFIX + "/logout", uri)
				|| pathMatcher.match(Constants.RESTFUL_PATH_PREFIX + "/video-tutorial*", uri)
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/testimonial")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/bundle")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/test")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/exam");
	}

	private boolean checkAuthorizationForSuperAdmin(String uri, String requestMethod) {
		PathMatcher pathMatcher = new AntPathMatcher();
		return pathMatcher.match(Constants.RESTFUL_PATH_PREFIX + "/login", uri)
				|| pathMatcher.matchStart(Constants.RESTFUL_PATH_PREFIX + "/logout", uri)
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/category")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/common")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/question-paper")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/student")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/center")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/bundle")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/test")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/exam")
				|| pathMatcher.matchStart(uri, Constants.RESTFUL_PATH_PREFIX + "/announcement");
	}

	private boolean checkAuthorisationForBranchAdmin(String uri, String requestMethod) {
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
