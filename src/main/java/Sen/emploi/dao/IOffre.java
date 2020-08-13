package Sen.emploi.dao;

import Sen.emploi.entities.Entreprise;
import Sen.emploi.entities.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOffre extends JpaRepository<Offre,Integer>, CrudRepository<Offre,Integer> {
    @Query("select o from Offre o where o.categorie = :categorie")
    public Offre getOffreByCategorie(@Param("categorie") String e);
}

