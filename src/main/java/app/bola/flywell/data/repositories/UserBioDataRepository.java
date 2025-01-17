package app.bola.flywell.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.bola.flywell.data.model.users.UserBioData;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "userbiodatas", collectionResourceRel = "userbiodatas", itemResourceRel = "userbiodata")
public interface UserBioDataRepository extends JpaRepository<UserBioData, String> {

}
