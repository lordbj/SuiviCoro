package Sen.emploi.dao;

import Sen.emploi.entities.Demandeur;
import Sen.emploi.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEntreprise extends JpaRepository<Entreprise,String>, CrudRepository<Entreprise,String> {
    @Query("select e from Entreprise e where e.email = :email")
    public Entreprise getEntrepriseByEmail(@Param("email") String e);
}
