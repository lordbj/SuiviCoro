package Sen.emploi.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Entreprise {
    @Id
    @Column(length = 200)
    private String email;
    @Column
    private String password;
    @Column
    private String nomEntreprise;
    @Column
    private int etat;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "entreprises_roles",
            joinColumns = { @JoinColumn(name = "email", nullable = false, updatable = false)},
            inverseJoinColumns = { @JoinColumn(name = "nom",nullable = false,updatable = false)})
    private List<Roles> roles = new ArrayList<>();

    public Entreprise() {
    }

    public Entreprise(String email, String password, String nomEntreprise, int etat, List<Roles> roles) {
        this.email = email;
        this.password = password;
        this.nomEntreprise = nomEntreprise;
        this.etat = etat;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
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
