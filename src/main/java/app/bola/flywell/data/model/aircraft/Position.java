package app.bola.flywell.data.model.aircraft;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Position {
	@Id
	@GeneratedValue(strategy = UUID)
	private String id;
	private int longitude;
	private int latitude;
}
