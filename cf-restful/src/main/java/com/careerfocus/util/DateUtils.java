package com.careerfocus.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {
	
	private static final Logger log = Logger.getLogger(DateUtils.class.getClass());

	public static Date convertMMDDYYYYToJavaDate(String dateTime) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			date = sdf.parse(dateTime);
		} catch (Exception e) {
			log.error("convertMMDDYYYYToJavaDate", e);
		}
		return date;
	}
}
