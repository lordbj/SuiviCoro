package Sen.emploi.dao;

import Sen.emploi.entities.Cv;
import Sen.emploi.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICv extends JpaRepository<Cv,Integer>, CrudRepository<Cv,Integer> {
    @Query("select c from Cv c where c.email = :email")
    public Cv getCvByEmail(@Param("email") String e);
}
