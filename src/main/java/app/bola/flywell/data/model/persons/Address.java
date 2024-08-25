package app.bola.flywell.data.model.persons;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String country;
	private String state;
	private String postalCode;
	private String streetName;
	private String streetNumber;
	private String houseNumber;
}
