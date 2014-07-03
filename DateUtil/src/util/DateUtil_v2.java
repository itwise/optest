package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateUtil_v2 {
	private static final String STANDARD_YMD = "yyyy-MM-dd";
	private static final DateFormat STANDARD_FORMATTER = new SimpleDateFormat(STANDARD_YMD);

	public static String format(Date date) {
		String str;
		synchronized (DateUtil_v2.class) {
			str = STANDARD_FORMATTER.format(date);
		}
		return str;
	}

	public static Date parse(String dateStr) throws ParseException {
		Date date = STANDARD_FORMATTER.parse(dateStr);
		return date;
	}

	private static void test1() throws InterruptedException {
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
		test2();
	}

}
