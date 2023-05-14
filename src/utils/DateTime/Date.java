package utils.DateTime;

import java.util.EnumSet;

public class Date {
	private final int[] daysPerMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int month;
	private int day;
	private int year;
	
	public Date setDate(int day, int month, int year) {
		validateDate(day, month, year);
		this.day = day;
		this.month = month;
		this.year = year;
		return this;
	}
	
	public int[] getDaysPerMonth() {
		return daysPerMonth;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		validateMonth(month);
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		validateDay(day, month);
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		validateLeapYear(year);
		this.year = year;
	}
	
	public void validateDate(int day, int month, int year) {
		validateDay(day, month);
		validateMonth(month);
		validateLeapYear(year);
	}
	
	public boolean validateDay(int days, int month) throws ArrayIndexOutOfBoundsException, IllegalArgumentException{
		boolean isNotValidDay = (days > daysPerMonth[month] || days < 1) && daysPerMonth[month] != 29;
		if (isNotValidDay ){
			throw new IllegalArgumentException("invalid Day: the days for month "+ month + " is between 0 and "+ daysPerMonth[month]);
		}
		this.day = days;
		return true;
	}
	
	public boolean validateMonth(int month) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
		if (month < 1 || month > 12){
			throw new IllegalArgumentException("invalid month: Month ranges between 1 - 12");
		}
		if (month > 12) {
			throw new  ArrayIndexOutOfBoundsException("Invalid month: Month ranges between 1 - 12");
		}
		this.month = month;
		return true;
	}
	public boolean validateLeapYear(int year) throws ArrayIndexOutOfBoundsException, IllegalArgumentException{
		boolean isLeapYear = year % 4 == 0 || (year % 100 == 0 && year % 400 == 0);
		boolean isNotLeapYear = year % 100 == 0 && !(year % 400 == 0);
		boolean isAlsoNotLeapYear = year % 100 != 0 && year % 400 != 0;
		if (isLeapYear) {
			daysPerMonth[3] = 29;
			this.year = year;
			return true;
		}
		
		if (isNotLeapYear || isAlsoNotLeapYear) {
			return true;
		}
		throw new IllegalArgumentException("Year " + year + " is invalid");
	}
	private DateEnum month() {
		for (DateEnum months: EnumSet.range(DateEnum.JANUARY, DateEnum.DECEMBER)) {
			boolean isIntegerValueOfMonth = months.ordinal() == getMonth() + 1;
			if (isIntegerValueOfMonth)
				return months;
		}
		return null;
	}
	
	@Override
	public String toString() {
		return String.format("%02d-%s-%d\n", getDay(), month() , getYear());
	}
}
