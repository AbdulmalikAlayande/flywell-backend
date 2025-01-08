package app.bola.flywell.dto.response;

import app.bola.flywell.data.model.aircraft.Aircraft;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for {@link Aircraft}
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirCraftResponse {
	
	private String hangerId;
	private String airCraftName;
	private String model;
	private String message;
}
