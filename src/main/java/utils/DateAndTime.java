/**
 * 
 */
package utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;

/**
 * @Author Chandu
 * @Date 15-Nov-2018
 */
public class DateAndTime 
{
	/*	To get the Current Time */
	public static String getTime()
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("kk.mm");
		String TimeNow = dateFormat.format(date);
		return TimeNow;
	}

	/*	To get the Current Date */
	public static String getDate()
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String DateNow = dateFormat.format(date);
		return DateNow;
	}
	/*	To get the Current Date */
	public static String getDate2()
	{
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		String DateNow = dateFormat.format(date);
		return DateNow;
	}

	/*	To get the Current Month in Integer */
	public static int getMonth_Integer()
	{
		DateTime datetime = DateTime.now();
		int month = datetime.getMonthOfYear();
		return month;
	}
	
	/*	To get the Current Month Text as Full in String*/
	public static String getMonth_Full()
	{
		DateTime datetime = DateTime.now();
		String month_Full = datetime.monthOfYear().getAsText();
		return month_Full;
	}
	
	/*	To get the Current Month Text as Short in String*/
	public static String getMonth_Short()
	{
		DateTime datetime = DateTime.now();
		String month_Short = datetime.monthOfYear().getAsShortText();
		return month_Short;
	}
	
	/*	To get the Current Day of the Month */
	public static String getDayOfTheMonth()
	{
		DateTime datetime = DateTime.now();
		String dayOfTheMonth = datetime.dayOfMonth().getAsText();
		return dayOfTheMonth;
	}
	
	/*	To get the Current Day Count in the Year */
	public static String getDayCount()
	{
		DateTime datetime = DateTime.now();
		String dayCountYear = datetime.dayOfYear().getAsText();
		return dayCountYear;
	}
	
	/*	To get the Current Minute of the Hour in String */
	public static String getMinuteOfTheHourAsString()
	{
		DateTime datetime = DateTime.now();
		String minuteOfTheHour = datetime.minuteOfHour().getAsText();
		return minuteOfTheHour;
	}
	
	/*	To get the Current Year as Integer */
	public static int getYear()
	{
		DateTime datetime = DateTime.now();
		int year = datetime.getYear();
		return year;
	}
	
	/*	To get the Current Hour of the Day in String */
	public static String getHourOfTheDay()
	{
		DateTime datetime = DateTime.now();
		String hour = datetime.hourOfDay().getAsShortText();
		return hour;
	}
	
	/*	To get the Current Week Count */
	public static String getWeekCount()
	{
		DateTime datetime = DateTime.now();
		String hour = datetime.weekOfWeekyear().getAsText();
		return hour;
	}
	/*	return number of milliseconds */
			public static String getMilliseconds () 
			{	
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());	
			return Long.toString(timestamp.getTime());
			}
	
			
//			To get the Current Date 
			public static String getDateAPI()
			{
			Date date = new Date();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
			String DateNow = dateFormat.format(date);
			return DateNow;
			}
			public static String getFutureDate2(int value) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd hh:mm:ss"); 
			LocalDate today = LocalDate.now();
			LocalDate future = today.plus(value, ChronoUnit.DAYS);
			return dtf.format(future);	
			}
			public static String getFutureDateTime24(int value) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd:kk:mm:ss"); 
			LocalDateTime today = LocalDateTime.now();
			LocalDateTime future = today.plus(value, ChronoUnit.DAYS);
			return dtf.format(future);	
			}
			public static String getFutureDateTime12(int value) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy:MM:dd:hh:mm:ss"); 
				LocalDateTime today = LocalDateTime.now();
				LocalDateTime future = today.plus(value, ChronoUnit.DAYS);
				return dtf.format(future);	
				}
			public String todayDate() {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			String date = format1.format(cal.getTime());
			return date;

			}
			public String getTodayDate() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			String todatdate=sdf.format(date);
			return todatdate;	
			}

			public String getTodayDate(String dateformat) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			String todatdate=sdf.format(date);
			return todatdate;	
			}

			public String getYearDate(String dateformat) {	
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.YEAR, 1); // Adds 365 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			return date;	
			}
			public String getTomorrowDate(String dateformat) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 1); // Adds 7 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			return date;	
			}
			public String getweekDate(String dateformat) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 7); // Adds 7 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			return date;	
			}
			public String getFutureDateTime(String dateformat, int days) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, days); // Adds 7 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			return date;	
			}

			public String getTodayDateTime() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
			LocalDateTime now = LocalDateTime.now(); 
			return dtf.format(now);	
			}

			public String getTodayDateOnly() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate today = LocalDate.now();
			return dtf.format(today);	
			}
			public String getFutureDate(int value) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate today = LocalDate.now();
			LocalDate future = today.plus(value, ChronoUnit.DAYS);
			return dtf.format(future);	
			}
			public String getPastDate(int value) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate today = LocalDate.now();
			LocalDate past = today.minusDays(value); 
			return dtf.format(past);	
			}

			public String getWeekDate(String dateformat) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 7); // Adds 7 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			return date;	
			}
			public String getTwoWeekDate(String dateformat) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 14); // Adds 4 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			return date;	
			}
			public String getTenDaysDate(String dateformat) {

			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c.add(Calendar.DATE, 10); // Adds 4 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			return date;	
			}
			public String getTime(String dateformat) {
			DateFormat dateFormat = new SimpleDateFormat(dateformat);
			Date date = new Date();
			return dateFormat.format(date);	
			}
			public String getCurrentTime() {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			return dateFormat.format(date);	
			}
			public String getCurrentMonth() {	
			Calendar cal = Calendar.getInstance();	
			String currentMonth =new SimpleDateFormat("MMM").format(cal.getTime());
			//System.out.println("currentMonth------------------"+currentMonth);
			return currentMonth;	
			}	public String getCurrentYear() {
			Calendar cal = Calendar.getInstance();	
			String currentyear =new SimpleDateFormat("YYYY").format(cal.getTime());
			//System.out.println("currentyear"+currentyear);
			return currentyear;	
			}
			public String getDate3() {
			Calendar cal = Calendar.getInstance();	
			String curerntdate =new SimpleDateFormat("dd").format(cal.getTime());
			//System.out.println(curerntdate);
			return curerntdate;
			}
			
			
			public static String getUTCTimeLessInMinutes(int inMinutes) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"); 
				LocalDateTime today = LocalDateTime.now(Clock.systemUTC());
				LocalDateTime future = today.minus(inMinutes, ChronoUnit.MINUTES);
				return dtf.format(future);	
				}
			public static String getUTCTimeLessInPlus(int inDays) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"); 
				LocalDateTime today = LocalDateTime.now(Clock.systemUTC());
				LocalDateTime future = today.plus(inDays, ChronoUnit.DAYS);
				return dtf.format(future);	
				}
			public static String getUTCTimePlusMinuts(int inMinutes) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss"); 
				LocalDateTime today = LocalDateTime.now(Clock.systemUTC());
				LocalDateTime future = today.plus(inMinutes, ChronoUnit.MINUTES);
				return dtf.format(future);	
				}
			
}
