package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil_v1 {
	private static final String STANDARD_YMD = "yyyy-MM-dd";

	public static String format(Date date) {
		DateFormat formatter = new SimpleDateFormat(STANDARD_YMD);
		String str = formatter.format(date);
		return str;
	}

	public static Date parse(String dateStr) throws ParseException {
		DateFormat formatter = new SimpleDateFormat(STANDARD_YMD);
		Date date = formatter.parse(dateStr);
		return date;
	}

	public static void main(String[] args) throws Exception {
		final int LOOP_COUNT = 1000000;
		Date d = new Date();
		
		for (int i = 0; i < 10000; i++) {
			format(d);
		}
		Thread.sleep(1000);
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < LOOP_COUNT; i++) {
			format(d);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

}
