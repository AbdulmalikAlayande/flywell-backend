package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.users.OTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<OTP, String> {
	Optional<OTP> findByData(long data);
}
