package com.careerfocus.configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.careerfocus.response.Response;

public class MainInterceptor extends HandlerInterceptorAdapter {

	private Logger log = Logger.getLogger(Logger.class.getName());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(true);

		if (requestUriRequiresSession(request.getRequestURI()) && session.getAttribute("role") == null) {
			setUnauthorizedResponse(response);
			return false;
		}

		setResponseHeaders(response);

		Enumeration<String> e = session.getAttributeNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			log.info(name + ": " + session.getAttribute(name));
		}

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

	private boolean requestUriRequiresSession(String uri) {
		// api's which can be accessed without session goes here.
		if (uri.equals("/cf-restful/students/login")) {
			return false;
		}
		return true;
	}

	private void setUnauthorizedResponse(HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		Response unAuthorizedResponse = Response.unauthorized().build();
		try {
			PrintWriter out = response.getWriter();
			out.println(unAuthorizedResponse.toJsonString());
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	private void setResponseHeaders(HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	}

}
