package app.bola.flywell.basemodules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface FlyWellRepository<T extends FlyWellModel> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {

    Optional<T> findByPublicId(String publicId);

    boolean existsByPublicId(String publicId);
}
