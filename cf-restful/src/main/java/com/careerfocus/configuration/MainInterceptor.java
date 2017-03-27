package com.careerfocus.configuration;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MainInterceptor extends HandlerInterceptorAdapter {
	
private Logger log = Logger.getLogger(Logger.class.getName());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("INSIDE PRE-HANDLE");
		
		HttpSession session = request.getSession(true);
		
		log.info(session.getAttributeNames());
		
		Enumeration<String> e = session.getAttributeNames();
		// log.info("hasMoreElements: " + e.hasMoreElements());
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			 log.info(name + ": " + session.getAttribute(name) + "<BR>");
		}
		
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("INSIDE POST-HANDLE");
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("INSIDE AFTER-COMPLETION");
		super.afterCompletion(request, response, handler, ex);
	}

}
