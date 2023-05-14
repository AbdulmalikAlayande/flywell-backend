package utils.appUtils;

public class IdGenerator {
	static int count = 0;
	public static String generateIdForFlightForm() {
		return "Form-"+getCountOfForms()+IdLetterCharacter.getCharacter()+"-"+getCountOfForms();
	}
	
	private static int getCountOfForms(){
		return count + 1;
	}
}
