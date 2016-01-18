package tn.springmvc.dom.administration;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tn.springmvc.dom.base.Personne;

@Entity
@Table(name = "Administrateur")
public class Administrateur extends Personne {

	@Column(unique = true)
	public String username;

	private String password;

	public Date date_adhésion;

	// Retourner un password encrypté
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);

	}

}
