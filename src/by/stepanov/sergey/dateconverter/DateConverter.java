package by.stepanov.sergey.dateconverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This class provides data conversion between most usable date standard classes
 * (java.sql.Date, java.util.Date, String).
 * 
 * @author Sergey Stepanov
 * @version 1.0.2
 */
public final class DateConverter {
    private DateConverter() {
    }

    /**
     * Convert java.sql.Date to java.util.Date
     * 
     * @param sqlDate
     *            java.sql.Date to convert
     * @return java.util.Date date
     */
    public static java.util.Date convertToDateUtil(java.sql.Date sqlDate) {
	long timeInMillis = sqlDate.getTime();
	return new java.util.Date(timeInMillis);
    }

    /**
     * Convert java.util.Date to java.sql.Date
     * 
     * @param utilDate
     *            java.util.Date to convert
     * @return java.sql.Date date
     */
    public static java.sql.Date convertToDateSql(java.util.Date utilDate) {
	long timeInMillis = utilDate.getTime();
	return new java.sql.Date(timeInMillis);
    }

    /**
     * Convert java.util.Date to string. If pattern is null or it's length = 0,
     * then pattern will be given by default locale
     * 
     * @param utilDate
     *            java.util.Date to convert
     * @param pattern
     *            date pattern
     * @return string representation of date in given pattern
     */
    public static String convertToString(java.util.Date utilDate, String pattern) {
	DateFormat dateFormat = null;
	if (isValidPattern(pattern)) {
	    dateFormat = new SimpleDateFormat();
	} else {
	    dateFormat = new SimpleDateFormat(pattern);
	}
	return dateFormat.format(utilDate);
    }

    /**
     * Convert java.sql.Date to string. If pattern is null or it's length = 0,
     * then pattern will be given by default locale
     * 
     * @param sqlDate
     *            java.sql.Date to convert
     * @param pattern
     *            date pattern
     * @return string representation of date in given pattern
     */
    public static String convertToString(java.sql.Date sqlDate, String pattern) {
	java.util.Date utilDate = convertToDateUtil(sqlDate);
	return convertToString(utilDate, pattern);
    }

    /**
     * Convert string date to java.util.Date
     * 
     * @param dateString
     *            date to convert
     * @param pattern
     *            date pattern
     * @return java.util.Date date
     */
    public static java.util.Date convertToDateUtil(String dateString,
	    String pattern) {
	DateFormat dateFormat = null;
	java.util.Date date = null;
	if (isValidPattern(pattern)) {
	    dateFormat = new SimpleDateFormat(pattern);
	} else {
	    dateFormat = new SimpleDateFormat();
	}
	try {
	    date = dateFormat.parse(dateString);
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	return date;
    }

    /**
     * Convert string date to java.sql.Date
     * 
     * @param dateString
     *            date to convert
     * @param pattern
     *            date pattern
     * @return java.sql.Date date
     */
    public static java.sql.Date convertToDateSql(String dateString,
	    String pattern) {
	java.util.Date utilDate = convertToDateUtil(dateString, pattern);
	return convertToDateSql(utilDate);
    }

    private static boolean isValidPattern(String pattern) {
	if (pattern.isEmpty() || pattern == null) {
	    return false;
	} else {
	    return true;
	}
    }
}