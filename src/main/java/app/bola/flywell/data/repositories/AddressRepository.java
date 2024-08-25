package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.persons.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {

}
