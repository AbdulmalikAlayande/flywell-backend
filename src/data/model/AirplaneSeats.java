package data.model;

public class AirplaneSeats {
	
	static int numberOfSeats = 20;
	private static final boolean[] seats = new boolean[numberOfSeats];
	
	public      static boolean[] getSeats() {
		return seats;
	}
	
	public static boolean getSeats(int seatIndex){
		return seats[seatIndex];
	}
	
	public int getNumberOfSeats(){
		return seats.length;
	}
	
	public boolean getInitialStateOfSeat(){  //seats[i] == false ==> !seats[i], why because it is boolean and initially false, so if true
		boolean isEmptySeat = false;
		for (boolean seat : seats) {
			if (!seat) {
				isEmptySeat = true;
				break;
			}
		}
		return isEmptySeat;
	}
}
