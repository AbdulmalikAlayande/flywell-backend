package app.bola.flywell.data.model.notifications;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Data
@AllArgsConstructor
public class Sender {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("email")
	private String email;
}
