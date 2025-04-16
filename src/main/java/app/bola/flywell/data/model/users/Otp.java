package app.bola.flywell.data.model.users;

import com.google.common.base.MoreObjects;
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
public class Otp {
	
	@Id
	@GeneratedValue(strategy = UUID)
	private String id;
	private long data;
	private String secretKey;
	private long staleTime;
	private boolean expired;
	private boolean used;
	private String userEmail;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("data", data)
				.add("secretKey", secretKey)
				.add("staleTime", staleTime)
				.add("expired", expired)
				.add("used", used)
				.add("userEmail", userEmail)
				.toString();
	}
}
