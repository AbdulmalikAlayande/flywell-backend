package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.aircraft.AirCraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirCraftsRepo extends JpaRepository<AirCraft, String> {

}
