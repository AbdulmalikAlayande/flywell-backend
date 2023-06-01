package dtos.Request;

import data.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
	private String passengerUsername;
	private int bookingCategory;
	private Payment payment;
}
