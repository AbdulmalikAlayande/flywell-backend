package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.persons.Customer;
import app.bola.flywell.data.model.persons.UserBioData;
import java.util.Optional;

public interface CustomerRepository extends FlyWellRepository<Customer> {
	
	Optional<Customer> findByBioData(UserBioData bioData);
}
