package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
