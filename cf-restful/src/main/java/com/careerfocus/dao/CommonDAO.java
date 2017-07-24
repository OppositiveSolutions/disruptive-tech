package com.careerfocus.dao;

import com.careerfocus.entity.States;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class CommonDAO {

	private JdbcTemplate template;

	@Autowired
	public CommonDAO(JdbcTemplate template) {
		this.template = template;
	}

	public List<States> getStates() {

		String query = "SELECT * FROM states";

		return template.query(query,
				(ResultSet result, int arg1) -> new States(result.getInt("state_id"), result.getString("name")));
	}

	public long getTimeDifferenceInSec(Date timeLastUpdated) {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		Date date = new Date();
		String timeCurrent = format.format(date);
		String timePrevious = format.format(timeLastUpdated);
		
	    Date d1 = null;
	    Date d2 = null;
	    try {
	        d1 = format.parse(timePrevious);
	        d2 = format.parse(timeCurrent);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    // Get msec from each, and subtract.
	    long diff = d2.getTime() - d1.getTime();
	    long diffSeconds = diff / 1000 % 60;
	    long diffMinutes = diff / (60 * 1000) % 60;
	    long diffHours = diff / (60 * 60 * 1000);
	    System.out.println("Time in seconds: " + diffSeconds + " seconds.");
	    System.out.println("Time in minutes: " + diffMinutes + " minutes.");
	    System.out.println("Time in hours: " + diffHours + " hours.");
		return diffSeconds;
	}
	
//	public DateTime getCurrentIST() {
//		String TIME_SERVER = "time-a.nist.gov";
//		NTPUDPClient timeClient = new NTPUDPClient();
//		InetAddress inetAddress;
//		DateTime time = null;
//		try {
//			inetAddress = InetAddress.getByName(TIME_SERVER);
//			TimeInfo timeInfo;
//			timeInfo = timeClient.getTime(inetAddress);
//			long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
//			time = new DateTime(returnTime).withZone(DateTimeZone.UTC);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return time;
//	}

}
