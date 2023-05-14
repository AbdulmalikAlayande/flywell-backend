package AirLineReservation;

public class Awwal {
	
	public static void main(String[] args) {
		int numberOfSpacesBetweenT1AndT2 = 4;
		int numberOfStarsForT2 = 4;
		int numberOfSpacesBetweenT2AndT3 = 4;
		int numberOfStarsForT4 = 4;
		for (int row = 0; row < 4; row++) {
			for (int firstTriangle = 0; firstTriangle <= row; firstTriangle++) {
				System.out.print("*");
			}
			for (int firstSpacing = 0; firstSpacing < numberOfSpacesBetweenT1AndT2; firstSpacing++) {
				System.out.print(" ");
			}
			for (int secondTriangle = 0; secondTriangle < numberOfStarsForT2; secondTriangle++) {
				System.out.print("*");
			}
			for (int secondSpacing = 0; secondSpacing < numberOfSpacesBetweenT2AndT3; secondSpacing++) {
				System.out.print(" ");
			}
			for (int thirdTriangle = 0; thirdTriangle <= row; thirdTriangle++) {
				System.out.print("*");
			}
			for (int thirdSpacing = 0; thirdSpacing <= row; thirdSpacing++) {
				System.out.print(" ");
			}
			for (int fourthTriangle = 0; fourthTriangle < numberOfStarsForT4; fourthTriangle++) {
				System.out.print("*");
			}
			numberOfSpacesBetweenT1AndT2--;
			numberOfStarsForT2--;
			numberOfStarsForT4--;
			System.out.println();
		}
	}
}
