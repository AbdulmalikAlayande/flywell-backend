package app.bola.flywell.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T>{
	
	private boolean successful;
	private T responseData;
	private int statusCode;
}
