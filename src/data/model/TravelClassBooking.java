package data.model;

import data.model.AirplaneSeats;

public class TravelClassBooking {
	public void bookTravelClassSeat(int firstSeatNumber){
		int numberOfSeats = 0;
		if (firstSeatNumber == 0)  numberOfSeats= AirplaneSeats.getSeats().length - 15;
		else if (firstSeatNumber == 5)  numberOfSeats= AirplaneSeats.getSeats().length - 10;
		else if (firstSeatNumber == 10)  numberOfSeats= AirplaneSeats.getSeats().length - 5;
		else if (firstSeatNumber == 15)  numberOfSeats= AirplaneSeats.getSeats().length;
		for (int i = firstSeatNumber; i < numberOfSeats; i++) {
			boolean isOccupiedSeat = AirplaneSeats.getSeats()[i];
			if (!isOccupiedSeat) {
				AirplaneSeats.getSeats()[i] = true;
				break;
			}
		}
	}
}
