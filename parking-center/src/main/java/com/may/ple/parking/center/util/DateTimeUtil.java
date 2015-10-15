package com.may.ple.parking.center.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTimeUtil {

	public static Map<String, Long> dateTimeDiff(Date dateStart, Date dateStop) {
		long diff = dateStop.getTime() - dateStart.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		Map<String, Long> map = new HashMap<String, Long>();
		map.put("days", diffDays);
		map.put("hours", diffHours);
		map.put("minutes", diffMinutes);
		map.put("seconds", diffSeconds);
		
		return map;
	}

}