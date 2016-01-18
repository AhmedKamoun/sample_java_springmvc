package tn.springmvc.bl.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tn.springmvc.dal.AdministrateurRepository;
import tn.springmvc.dom.administration.Administrateur;

/**
 *
 * This is a initializing bean that inserts some test data in the database. It
 * is only active in the development profile, to see the data login with root /
 * root password.
 */
@Component
public class TestDataInitializer {
	@Autowired
	private AdministrateurRepository administrateurRepository;

	public void init() throws Exception {

		Administrateur admin = new Administrateur();
		admin.prenom = "Ahmed";
		admin.nom = "Kamoun";
		admin.username = "root";
		admin.setPassword("root");
		administrateurRepository.save(admin);
	}
}
