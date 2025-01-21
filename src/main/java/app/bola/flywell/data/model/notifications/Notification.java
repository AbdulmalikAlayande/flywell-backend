package app.bola.flywell.data.model.notifications;

import app.bola.flywell.data.model.users.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private Sender sender;
	private LocalDateTime createdOn;
	private String htmlContent;
	@OneToMany
	private List<Recipients> to;
	private String subject;
	@OneToMany
	private List<User> person;
}
