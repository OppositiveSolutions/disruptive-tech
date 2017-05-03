package com.careerfocus.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.careerfocus.util.response.Response;

public class CommonUtils {

	private static Logger log = LoggerFactory.getLogger(CommonUtils.class.getClass());

	public static void setUnauthorizedResponse(HttpServletResponse response) {
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

}
