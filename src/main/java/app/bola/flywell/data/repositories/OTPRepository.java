package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.users.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OTPRepository extends JpaRepository<Otp, String> {
	Optional<Otp> findByData(long data);
}
