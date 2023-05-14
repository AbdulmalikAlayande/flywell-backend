package utils.DateTime;

import javax.swing.*;

public class TimeMenu {
	Time time = new Time();
		public void displayMenu() {
		String mainMenu = """
				Welcome
				1. ==> Set Time
				2. ==> View the set Time
				3. ==> Save Time
				4. ==> Exit
				""";
		String userInput = JOptionPane.showInputDialog(mainMenu);
		switch (userInput.charAt(0)) {
			case '1' -> setTime();
			case '2' -> viewSetTime();
			case '3' -> saveSetTime();
			case '4' -> System.exit(0);
			default -> {
				Pane.abdulmalikPrint("You entered a wrong input");
				displayMenu();
			}
		}
	}
	
	private void setTime() {
		try {
			int hour = Integer.parseInt(Pane.abdulmalikInput("""
					Enter hour in the range of 0 - 23
					e.g 11 0'clock just enter 11"""));
			int minute = Integer.parseInt(Pane.abdulmalikInput("""
					Enter minute in the range of 0 - 59
					e.g 11:59 just enter 59 for minute"""));
			int second = Integer.parseInt(Pane.abdulmalikInput("""
					Enter seconds in the range of 0 - 59
					e.g 11:59:35 just enter 35 for minute"""));
			time.setHour(hour);
			time.setMinute(minute);
			time.setSeconds(second);
			Pane.abdulmalikPrint(time.displayUniversalTimeFormat());
			Pane.abdulmalikPrint(time.displayStandardTimeFormat());
			displayMenu();
			
		} catch (Exception ex) {
			Pane.abdulmalikPrint(ex.getMessage());
			displayMenu();
		}
	}
	
	private void viewSetTime() {
	}
	
	private void saveSetTime() {
	}
}
