package io.messagebird.springbootstarter.timezone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class TimeConvertor {
	
	String portugalTime;
	public String timeZoneConvertor() {
	
	 final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
	 
	 SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
     
     Calendar cal = Calendar. getInstance();
     Date date  = cal.getTime();
     DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
     String formattedDate=dateFormat. format(date);
     
     
    /* String dateInString = "22-01-2015 10:15:55 AM";
     Date date = formatter.parse(dateInString);*/
     TimeZone tz = TimeZone.getDefault();

     // From TimeZone Asia/Singapore
     System.out.println("TimeZone : " + tz.getID() + " - " + tz.getDisplayName());
     System.out.println("TimeZone : " + tz);
     System.out.println("Date : " + formattedDate);

     // To TimeZone America/New_York
     SimpleDateFormat sdfAmerica = new SimpleDateFormat(DATE_FORMAT);
     TimeZone tzInAmerica = TimeZone.getTimeZone("Europe/Lisbon");
     sdfAmerica.setTimeZone(tzInAmerica);

     Calendar calendar = new GregorianCalendar();
     calendar.setTime(date);
     calendar.setTimeZone(tzInAmerica);

     System.out.println("\nTimeZone : " + tzInAmerica.getID() + " - " + tzInAmerica.getDisplayName());
     System.out.println("TimeZone : " + tzInAmerica);

     //Wrong! It will print the date with the system default time zone
     System.out.println("Date (New York) (Wrong!): " + calendar.getTime());

     //Correct! need formatter
     portugalTime = sdfAmerica.format(calendar.getTime());
     System.out.println("Time: "+portugalTime);
     System.out.println("Date (New York) (Correct!) : " + sdfAmerica.format(calendar.getTime()));

     int year = calendar.get(Calendar.YEAR);
     int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
     int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
     int hour = calendar.get(Calendar.HOUR); // 12 hour clock
     int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
     int minute = calendar.get(Calendar.MINUTE);
     int second = calendar.get(Calendar.SECOND);
     int ampm = calendar.get(Calendar.AM_PM); //0 = AM , 1 = PM

     //Correct
     System.out.println("\nyear \t\t: " + year);
     System.out.println("month \t\t: " + month + 1);
     System.out.println("dayOfMonth \t: " + dayOfMonth);
     System.out.println("hour \t\t: " + hour);
     System.out.println("minute \t\t: " + minute);
     System.out.println("second \t\t: " + second);
     System.out.println("ampm \t\t: " + ampm);
     return portugalTime;
	}

}
