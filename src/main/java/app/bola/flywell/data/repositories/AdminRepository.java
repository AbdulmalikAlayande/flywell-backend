package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.persons.Admin;
import app.bola.flywell.data.model.persons.UserBioData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(
		path = "admins",
		itemResourceRel = "admin", itemResourceDescription = @Description(value = ""),
		collectionResourceRel = "admins", collectionResourceDescription = @Description(value = "")
)
public interface AdminRepository extends JpaRepository<Admin, String> {
	
	
	
	Optional<Admin> findByBioData(UserBioData bioData);
	
	Optional<Admin> findByEmail(String email);
}
