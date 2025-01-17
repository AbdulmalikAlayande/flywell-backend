package app.bola.flywell.data.model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.UUID;


@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OTP {
	
	@Id
	@GeneratedValue(strategy = UUID)
	private String id;
	private long data;
	private String secretKey;
	private long staleTime;
	private boolean isExpired;
	private boolean isUsed;
	private String userEmail;
	
}
