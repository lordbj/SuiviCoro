package Sen.emploi;

import Sen.emploi.dao.IEntreprise;
import Sen.emploi.dao.IRoles;
import Sen.emploi.entities.Entreprise;
import Sen.emploi.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EmploiApplication implements CommandLineRunner {


	@Autowired
	private IEntreprise entreprisedao;
	@Autowired
	private IRoles rolesdao;
	public static void main(String[] args) {
		SpringApplication.run(EmploiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Roles 1
		Roles roles1 = new Roles();
		roles1.setNom("ROLE_Demandeurs");
		rolesdao.save(roles1);
		//Roles 2
		Roles roles2 = new Roles();
		roles2.setNom("ROLE_Entreprise");
		rolesdao.save(roles2);

		//Liste 1 de roles
		List<Roles> rolesList1 = new ArrayList<>();
		rolesList1.add(roles1);
		rolesList1.add(roles2);
		//Liste 2 de roles
		List<Roles> rolesList2 = new ArrayList<>();
		rolesList2.add(roles1);

		// Prof 1
		Entreprise ent = new Entreprise();
		ent.setNomEntreprise("SeckProd");
		ent.setEmail("ngorseck@groupeisi.com");
		//crypt password
		String password = "passer";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		ent.setPassword(hashedPassword);
		ent.setEtat(1);
		ent.setRoles(rolesList1);
		entreprisedao.save(ent);
		

		List<Entreprise> listProf = entreprisedao.findAll();
		listProf.forEach(e->{
			System.out.println(e.getNomEntreprise());
		});


	}

}
