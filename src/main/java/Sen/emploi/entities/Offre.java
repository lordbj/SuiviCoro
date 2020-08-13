package Sen.emploi.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Offre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String poste;
    @Column
    private String lieu;
    @Column
    private String typePoste;
    @Column
    private String categorie;
    @Column
    private String description;
    @Column
    private String nomEntr;

    public Offre() {
    }

    public Offre(String poste, String lieu, String typePoste, String categorie, String description, String nomEntr) {
        this.poste = poste;
        this.lieu = lieu;
        this.typePoste = typePoste;
        this.categorie = categorie;
        this.description = description;
        this.nomEntr = nomEntr;
    }
}
