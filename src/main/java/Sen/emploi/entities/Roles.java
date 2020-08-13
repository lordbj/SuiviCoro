package Sen.emploi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Roles implements Serializable {
    @Id
    @Column(length=100)
    private String nom;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<Entreprise> entreprises = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<Demandeur> demandeurs = new ArrayList<>();

    public Roles() {
    }

    public Roles(String nom, List<Entreprise> entreprises, List<Demandeur> demandeurs) {
        this.nom = nom;
        this.entreprises = entreprises;
        this.demandeurs = demandeurs;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(List<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }

    public List<Demandeur> getDemandeurs() {
        return demandeurs;
    }

    public void setDemandeurs(List<Demandeur> demandeurs) {
        this.demandeurs = demandeurs;
    }
}
