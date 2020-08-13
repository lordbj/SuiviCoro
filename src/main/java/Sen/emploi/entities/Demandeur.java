package Sen.emploi.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Demandeur {
    @Id
    @Column(length = 200)
    private String email;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String password;
    @Column
    private String telephone;
    @Column
    private int etat;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "demandeurs_roles",
            joinColumns = { @JoinColumn(name = "email", nullable = false, updatable = false)},
            inverseJoinColumns = { @JoinColumn(name = "nom",nullable = false,updatable = false)})
    private List<Roles> roles = new ArrayList<>();

    public Demandeur() {

    }

    public Demandeur(String email, String nom, String prenom, String password, String telephone, int etat, List<Roles> roles) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.telephone = telephone;
        this.etat = etat;
        this.roles = roles;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }
}

