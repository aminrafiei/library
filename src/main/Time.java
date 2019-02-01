package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import library.Library;
/**
 * this class calculate time between days
 * @author Amin
 *
 */
public class Time {

	public static SimpleDateFormat myFormat = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
	private long diff = 0;

	/**
	 * calculate diff between times
	 * 
	 * @param time
	 *            first time
	 */
	@SuppressWarnings("deprecation")
	public void differeceTime(String time) {

		java.util.Date date1;
		java.util.Date date2;
		try {
			date1 = myFormat.parse(time);
			date2 = myFormat.parse(currectTime());
			date1.setMinutes(date1.getMinutes() + Library.getMaxTimeBorrow());
			//date1.setHours(date1.getHours()+(24 *Library.getMaxTimeBorrow()));
			diff = date1.getTime() - date2.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return String of currect time with format
	 */
	public static String currectTime() {
		return new SimpleDateFormat("yyyy_MM_dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}

	public final long diffSeconds() {
		return diff / 1000 % 60;
	}

	public final long diffMinutes() {
		return diff / (60 * 1000) % 60;
	}

	public final long diffHours() {
		return diff / (60 * 60 * 1000) % 24;
	}

	public final long diffDays() {
		return diff / (24 * 60 * 60 * 1000);
	}

	/**
	 * show time left to borrow
	 */
	public final void printTimeConvert() {
		System.out.println("time left :" + diffDays() + " days " + diffHours() + " hour " + diffMinutes() + " minutes "
				+ diffSeconds() + " seconds");
	}
}
