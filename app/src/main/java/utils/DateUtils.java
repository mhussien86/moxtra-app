package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Utility class for formating input to a valid date string.
 */
public class DateUtils {

	/** The Constant DATE_ISO_FORMAT. */
	public static final String DATE_ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

	/** The Constant DATE_READABLE_FORMAT. */
	public static final String DATE_READABLE_FORMAT = "dd.MM.yyyy";

	/**
	 * Instantiates a new date utils.
	 */
	private DateUtils() {
	}

	/**
	 * Format timestamp.
	 * 
	 * @param timestamp
	 *            the timestamp
	 * @return the string
	 */
	public static String formatTimestamp(String timestamp) {
		String cleanedTimestamp = timestamp.replace("T", " ").replace("+00:00",
				"");
		return StringUtils.formatDate("dd.MM.yyyy HH:mm",
				"yyyy-MM-dd HH:mm:ss", cleanedTimestamp);
	}

	/**
	 * Format bill date.
	 * 
	 * @param billDate
	 *            the bill date
	 * @return the string
	 */
	public static String formatBillDate(String billDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date date = sdf.parse(billDate);
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.add(Calendar.MONTH, 1);
			cl.add(Calendar.DATE, -1);
			date = cl.getTime();
			return sdf.format(date);
		} catch (ParseException e) {

		}
		return null;
	}

	/**
	 * Format iso date.
	 * 
	 * @param date
	 *            the date
	 * @return the date
	 */
	public static Date formatIsoDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_ISO_FORMAT,
				Locale.GERMAN);

		Date d = null;
		try {
			d = sdf.parse(date);

			return d;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	/**
	 * Format last update date.
	 * 
	 * @param hoursBehind
	 *            the hours behind
	 * @param serverTime
	 *            the server time
	 * @return the string
	 */
	public static String formatLastUpdateDate(int hoursBehind, String serverTime) {
		serverTime = serverTime.substring(5).replace(" GMT", "");
		SimpleDateFormat sdf = new SimpleDateFormat("dd LLL yyyy HH:mm:ss",
				Locale.GERMANY);
		try {
			Date serverDate = sdf.parse(serverTime);
			Calendar cl = Calendar.getInstance();
			cl.setTime(serverDate);
			hoursBehind = 1;
			if (TimeZone.getDefault().inDaylightTime(serverDate)) {
				cl.add(Calendar.HOUR_OF_DAY, -hoursBehind + 2);
			} else {
				cl.add(Calendar.HOUR_OF_DAY, -hoursBehind + 1);
			}
			serverDate = cl.getTime();
			sdf = new SimpleDateFormat("dd.MM. HH:mm");
			return sdf.format(serverDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDayOfWeek(String dateStr) {
		// create the date format in which you will parse the date
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		// parse in the date
		Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// change the pattern to output the day of week
		df.applyPattern("EEE");
		// print the formatted date out
		System.out.println("Day of Week = " + df.format(date));
		return df.format(date);

	}
	public static String usingDateFormatter(long input){
        Date date = new Date(input);
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setCalendar(cal);
        cal.setTime(date);
        return sdf.format(date);
 
    }


	/**
	 * @return the today's date in the method format
	 */
	public static String getTodayDate(){
		Date date = new Date();
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return sdf.format(date);

	}


	public static long getTomorrowDateSQLFormat(){
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setCalendar(cal);
		cal.add(Calendar.DATE, 1);
		Date date =cal.getTime();
		return date.getTime();

	}

	public static String getTomorrowDate(){
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setCalendar(cal);
		cal.add(Calendar.DATE, 1);
		Date date =cal.getTime();
		return sdf.format(date);

	}
	public  static long  getDateAfterThirtyDaysSQLFormat(){
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setCalendar(cal);
		cal.add(Calendar.DATE, 30);
		Date date =cal.getTime();
		return date.getTime();

	}


	public  static long  getDateAfterTomorrowSQLFormat(){
		Calendar cal = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setCalendar(cal);
//		cal.after(getTomorrowDate());
		cal.set(Calendar.MONTH, cal.getActualMaximum(Calendar.MONTH));
//		cal.add(Calendar.DATE, 30);
		Date date =cal.getTime();
		return date.getTime();

	}

	public static boolean isDateInCurrentWeek(Date date)
	{
		Date currentWeekStart, currentWeekEnd;

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setFirstDayOfWeek(Calendar.SATURDAY);
		while(currentCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
		{
			currentCalendar.add(Calendar.DATE,-1);//go one day before
		}
		currentWeekStart = currentCalendar.getTime();

		currentCalendar.add(Calendar.DATE, 6); //add 6 days after Monday
		currentWeekEnd = currentCalendar.getTime();

		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setFirstDayOfWeek(Calendar.SATURDAY);
		targetCalendar.setTime(date);


		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(currentWeekStart);

		boolean result = false;
		while(!(tempCal.getTime().after(currentWeekEnd)))
		{
			if(tempCal.get(Calendar.DAY_OF_YEAR)==targetCalendar.get(Calendar.DAY_OF_YEAR))
			{
				result=true;
				break;
			}
			tempCal.add(Calendar.DATE,1);//advance date by 1
		}

		return result;
	}

	public static boolean isDateInNexttWeek(Date date)
	{
		Date nextWeekStart, nextWeekEnd;

		Calendar currentCalendar = Calendar.getInstance();
		currentCalendar.setFirstDayOfWeek(Calendar.SATURDAY);
		while(currentCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
		{
			currentCalendar.add(Calendar.DATE,+1);//go one day after
		}

		nextWeekStart = currentCalendar.getTime();

		currentCalendar.add(Calendar.DATE, 6); //add 6 days after Saturday
		nextWeekEnd = currentCalendar.getTime();

		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setFirstDayOfWeek(Calendar.SATURDAY);
		targetCalendar.setTime(date);


		Calendar tempCal = Calendar.getInstance();
		tempCal.setTime(nextWeekStart);

		boolean result = false;
		while(!(tempCal.getTime().after(nextWeekEnd)))
		{
			if(tempCal.get(Calendar.DAY_OF_YEAR)==targetCalendar.get(Calendar.DAY_OF_YEAR))
			{
				result=true;
				break;
			}
			tempCal.add(Calendar.DATE, 1);//advance date by 1
		}

		return result;
	}
	public static long getFourWeeksAgoDateSQLFormat(){
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.SATURDAY);
		cal.add(Calendar.WEEK_OF_MONTH, -4);
		Date fourWeeksAgo = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setCalendar(cal);
		cal.setTime(fourWeeksAgo);

		return fourWeeksAgo.getTime();
	}
	public static long getTodayDateSQLFormat(){
		Date date = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setFirstDayOfWeek(Calendar.SATURDAY);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		sdf.setCalendar(cal);
		cal.setTime(date);
		return date.getTime();

	}
	public static boolean isThisDateWithinLastWeekRange(Date date) {


			Calendar currentDateThisWeek = Calendar.getInstance();
		    currentDateThisWeek.setFirstDayOfWeek(Calendar.SATURDAY);
			currentDateThisWeek.add(Calendar.WEEK_OF_MONTH,0);

			// current date before 1 week
			Calendar currentDateBeforeThisWeek = Calendar.getInstance();
		    currentDateBeforeThisWeek.setFirstDayOfWeek(Calendar.SATURDAY);
			currentDateBeforeThisWeek.add(Calendar.WEEK_OF_MONTH, -1);

			if (date.before(currentDateThisWeek.getTime()) && date.after(currentDateBeforeThisWeek.getTime())) {

				//ok everything is fine, date in range
				return true;

			} else {

				return false;

			}
}


	public static boolean isThisDateWithinMonth(Date date) {


		Calendar currentDateThisWeek = Calendar.getInstance();
		currentDateThisWeek.setFirstDayOfWeek(Calendar.SATURDAY);
		currentDateThisWeek.add(Calendar.MONTH,0);

		// current date before 1 week
		Calendar currentDateBeforeThisWeek = Calendar.getInstance();
		currentDateBeforeThisWeek.setFirstDayOfWeek(Calendar.SATURDAY);
		currentDateBeforeThisWeek.add(Calendar.MONTH, -1);


		if (date.after(currentDateThisWeek.getTime()) && date.before(currentDateBeforeThisWeek.getTime())) {

			//ok everything is fine, date in range
			return true;

		} else {

			return false;

		}
	}



	public static boolean isThisDateWithinNextWeek(Date date){

		Calendar currentDateThisWeek = Calendar.getInstance();
		currentDateThisWeek.setFirstDayOfWeek(Calendar.SATURDAY);
		currentDateThisWeek.add(Calendar.WEEK_OF_MONTH , 0);

		// current date after 1 week
		Calendar currentDateAfterThisWeek = Calendar.getInstance();
		currentDateAfterThisWeek.setFirstDayOfWeek(Calendar.SATURDAY);
		currentDateAfterThisWeek.add(Calendar.WEEK_OF_MONTH, 1);

		if (date.before(currentDateThisWeek.getTime()) && date.after(currentDateAfterThisWeek.getTime())) {

			//ok everything is fine, date in range
			return true;

		} else {

			return false;

		}
	}

	public static String getDate(long milliSeconds, String dateFormat)
	{
		// Create a DateFormatter object for displaying date in specified format.
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in milliseconds to date.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}
}
