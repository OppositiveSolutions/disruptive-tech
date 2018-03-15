package com.careerfocus.util;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

	private static final Logger log = Logger.getLogger(DateUtils.class.getClass());

	public static final String PRIMARY_TIMEZONE = "Asia/Kolkata";

	static final ZoneId zoneId = ZoneId.of(PRIMARY_TIMEZONE);

	public static final String PRIMARY_DATE_FORMAT = "dd/MM/yyyy";

	public static final String PRIMARY_TIME_FORMAT = "HH:mm a";

	public static final String PRIMARY_DATE_TIME_FORMAT = PRIMARY_DATE_FORMAT + " " + PRIMARY_TIME_FORMAT;

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

	public static Date convertYYYYMMDDToJavaDate(String dateTime, String format) {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(dateTime);
		} catch (Exception e) {
			log.error("convertYYYYMMDDToJavaDate", e);
		}
		return date;
	}

	public static String toFormat(java.sql.Date date, String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String convertToDDMMYYYY(Timestamp timestamp) {
		ZonedDateTime time = ZonedDateTime.ofInstant(timestamp.toInstant(), zoneId);
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PRIMARY_DATE_FORMAT);
		return formatter.format(time);
	}

	public static String convertToDDMMYYYYHHmma(Timestamp timestamp) {
		ZonedDateTime time = ZonedDateTime.ofInstant(timestamp.toInstant(), zoneId);
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PRIMARY_DATE_TIME_FORMAT);
		return formatter.format(time);
	}
}
