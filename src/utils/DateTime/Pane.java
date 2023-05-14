package utils.DateTime;

import javax.swing.*;

public class Pane {
	
	Object o = 5;
	
	public static String abdulmalikPrint(String message){
		JOptionPane.showMessageDialog(null, message);
		return message;
	}
	public static String abdulmalikInput(String message){
		return JOptionPane.showInputDialog(message);
	}
	
	public static int abdulmalikInput(int message){
		return Integer.parseInt(JOptionPane.showInputDialog(message));
	}
	
}
