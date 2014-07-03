package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// Hahaha222
public class DateUtil_v3 {
	private static final String STANDARD_YMD = "yyyy-MM-dd";
	private static final ThreadLocal<DateFormat> LOCAL = new ThreadLocal<DateFormat>();

	public static String format(Date date) {
		String str;
		DateFormat formatter = LOCAL.get();
		if (formatter == null) {
			formatter = new SimpleDateFormat(STANDARD_YMD);
			LOCAL.set(formatter);
		}
		str = formatter.format(date);
		return str;
	}

	private static void test2() throws InterruptedException {
		final Date d = new Date();
		final int LOOP_COUNT = 100000;
		
		for (int i = 0; i < 10000; i++) {
			format(d);
		}
		Thread.sleep(1000);

		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					long start = System.currentTimeMillis();
					Random r = new Random();
					for (int i = 0; i < LOOP_COUNT; i++) {
						format(d);
						//Thread.sleep(0, 2000);
					}
					long end = System.currentTimeMillis();
					System.out.println(Thread.currentThread() + ":" + (end - start));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread[] threads = new Thread[2];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(r);
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

	public static void main(String[] args) throws Exception {
		//test2();
		for (int i = 0; i < 1000; i++) {
			new SimpleDateFormat("yyyy-MM-dd");
		}
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			new SimpleDateFormat("yyyy-MM-dd");
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

}
