package Sen.emploi.dao;

import Sen.emploi.entities.Demandeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IDemandeur extends JpaRepository<Demandeur,String>, CrudRepository<Demandeur,String> {
    @Query("select d from Demandeur d where d.email = :email")
    public Demandeur getDemandeurByEmail(@Param("email") String e);
}

