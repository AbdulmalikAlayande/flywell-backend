package app.bola.flywell.data.repositories;

import app.bola.flywell.data.model.persons.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMemberRepository extends JpaRepository<CrewMember, String> {

}
