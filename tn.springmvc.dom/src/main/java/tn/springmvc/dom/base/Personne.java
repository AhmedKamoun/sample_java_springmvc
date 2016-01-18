package tn.springmvc.dom.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
public class Personne extends BaseEntity {
    @Column(nullable = false)
    public String nom;

    @Column(nullable = false)
    public String prenom;

    @Column(unique = true, nullable = true)
    public String cin;

    public Date dateNaissance;

    public Boolean isMale;


}
