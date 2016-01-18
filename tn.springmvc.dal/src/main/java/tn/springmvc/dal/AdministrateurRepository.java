package tn.springmvc.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.springmvc.dom.administration.Administrateur;


@Repository
public interface AdministrateurRepository extends
        JpaRepository<Administrateur, Long> {

    @Query("SELECT admin FROM Administrateur admin  WHERE username = :username")
    public Administrateur findAdminByUsername(@Param("username") String username);

}
