package utils.DateTime;

public class Time {
	private int hour;
	private int minute;
	
	private int seconds;
	
	public Time(int hour, int minute, int seconds) {
		if(!validateTime(hour,minute,seconds)){
			throw new IllegalArgumentException("incorrect time");
		}
		this.hour = hour;
		this.minute = minute;
		this.seconds = seconds;
	}
	
	public Time(){
	
	}
	public Time(int hour, int minute){
		this(hour,minute, 0);
	}
	public Time(int hour){
		this(hour,0);
	}
	public Time(Time time){
		this(time.hour, time.minute, time.seconds);
		this.minute = time.minute;
		this.seconds = time.seconds;
		this.hour = time.hour;
	}
	public boolean validateTime(int hour, int minute, int seconds){
		validateHour(hour);
		validateMinute(minute);
		validateSeconds(seconds);
		return true;
	}
	
	private void validateHour(int hour) {
		boolean isValid = hour >= 0 && hour < 24;
		if(isValid)
			this.hour = hour;
		else throw new IllegalArgumentException("value out range for hour");
	}
	
	private void validateMinute(int minute) {
		boolean isValid = minute >= 0 && minute < 60;
		if (isValid){
			this.minute = minute;
		}else throw new IllegalArgumentException("value out of range for seconds");
	}
	
	private void validateSeconds(int seconds) {
		boolean isValid = seconds >= 0 && seconds < 60;
		if (isValid){
			this.seconds = seconds;
		}
		else throw new IllegalArgumentException("value out of range for seconds");
	}
	
	public String displayUniversalTimeFormat() {
		return String.format("%s : %s : %s %s", getHour(),getMinute(),getSeconds(),hour < 12 ? "AM" : "PM");
	}
	public String displayStandardTimeFormat(){
		return String.format("%02d : %02d : %02d", hour,minute,seconds);
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	public String getHour() {
		return String.format("%02d",hour == 0|| hour == 12 ? hour = 12 : hour % 12 );
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public String getMinute() {
		return String.format("%02d",minute);
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public String getSeconds() {
		return String.format("%02d",seconds);
	}
}