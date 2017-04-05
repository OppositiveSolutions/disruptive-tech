package com.careerfocus.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.careerfocus.dao.CommonDAO;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Service
public class CommonService {

	private static final int LOG4J_TRACE = 5;
	private static final int LOG4J_OFF = 4;
	private static final int LOG4J_ERROR = 3;
	private static final int LOG4J_INFO = 2;
	private static final int LOG4J_DEBUG = 1;
	
	@Autowired
	CommonDAO commonDAO;
	
	private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

	public String setLogLevel(int level) throws Exception {
		String message = "log4j set to ";

		Logger rootLogger = (Logger) LoggerFactory.getLogger("com.careerfocus");

		switch (level) {
		case LOG4J_DEBUG:
			rootLogger.setLevel(Level.DEBUG);
			message += "Debug";
			log.debug(message);
			break;
		case LOG4J_INFO:
			rootLogger.setLevel(Level.INFO);
			message += "Info";
			log.info(message);
			break;
		case LOG4J_ERROR:
			rootLogger.setLevel(Level.ERROR);
			message += "Error";
			log.error(message);
			break;
		case LOG4J_OFF:
			rootLogger.setLevel(Level.OFF);
			message += "Off";
			log.debug(message);
			break;
		case LOG4J_TRACE:
			rootLogger.setLevel(Level.TRACE);
			message += "Trace";
			log.trace(message);
			break;
		default:
			rootLogger.setLevel(Level.ERROR);
			message += "-Error";
			log.error(message);
			break;
		}
		return message;
	}
}
