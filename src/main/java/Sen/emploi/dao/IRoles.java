package Sen.emploi.dao;

import Sen.emploi.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoles extends JpaRepository<Roles,String> {
}
