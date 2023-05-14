package utils.DateTime;

import javax.swing.*;

public class DateUserMenu {
	Date date = new Date();
	public void displayMenu(){
		String mainMenu ="""
				Welcome
				1. ==> Set Date
				2. ==> View the set Date
				3. ==> Save Date
				4. ==> Exit
				""";
		String userInput = JOptionPane.showInputDialog(mainMenu);
		switch (userInput.charAt(0)){
			case '1' -> setDate();
			case '2' -> viewSetDate();
			case '3' -> saveDate();
			case '4' -> System.exit(0);
			default -> {
				Pane.abdulmalikPrint("You entered a wrong input");
				displayMenu();
			}
		}
	}
	
	private void setDate() {
		try {
			int day = Integer.parseInt(Pane.abdulmalikInput("""
					Enter day in the range of the month
					e.g January is in the range 1 to 31"""));
			int month = Integer.parseInt(Pane.abdulmalikInput("""
					Enter month in the range of the month
					e.g January is 1, February is 2"""));
			int year = Integer.parseInt(Pane.abdulmalikInput("""
					Enter year that corresponds with the date
					e.g 1999, 2008 and so on"""));
			
			date.setDate(day,month,year);
			Pane.abdulmalikPrint("Date successfully set");
			displayMenu();
		} catch (Exception ex){
			Pane.abdulmalikPrint(ex.getMessage());
			displayMenu();
		}
	}
	private void viewSetDate() {
		Pane.abdulmalikPrint(String.valueOf(date));
		displayMenu();
	}
	
	private void saveDate() {
	
	}
}
