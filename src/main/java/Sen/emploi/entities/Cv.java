package Sen.emploi.entities;

import javax.persistence.*;

@Entity
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String email;
    @Column
    private String titre;
    @Column
    private String adresse;
    @Column
    private String secteur;
    @Column
    private String cv;

    public Cv() {
    }

    public Cv(String nom, String prenom, String email, String titre, String adresse, String secteur, String cv) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.titre = titre;
        this.adresse = adresse;
        this.secteur = secteur;
        this.cv = cv;
    }
}
