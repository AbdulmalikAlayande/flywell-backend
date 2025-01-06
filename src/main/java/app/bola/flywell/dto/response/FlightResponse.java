package app.bola.flywell.dto.response;

import app.bola.flywell.basemodules.BaseResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightResponse extends BaseResponse {

	String message;
	String displayImage;
	String arrivalCity;
	String departureCity;
	String arrivalAirportName;
	String departureAirportName;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("publicId", getPublicId())
				.add("message", message)
				.add("displayImage", displayImage)
				.add("arrivalCity", arrivalCity)
				.add("departureCity", departureCity)
				.add("arrivalAirportName", arrivalAirportName)
				.add("departureAirportName", departureAirportName)
				.add("createdDate", getCreatedDate())
				.add("lastModifiedDate", getLastModifiedDate())
				.add("createdBy", getCreatedBy())
				.add("createdByRole", getCreatedByRole())
				.add("lastModifiedBy", getLastModifiedBy())
				.toString();
	}
}
