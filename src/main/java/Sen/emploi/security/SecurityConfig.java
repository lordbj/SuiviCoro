package Sen.emploi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("SELECT email as principal, password as credentials, etat FROM entreprise WHERE email =  ?")
			.authoritiesByUsernameQuery("SELECT email as principal, nom as role FROM entreprises_roles WHERE email = ?")
			.passwordEncoder(new BCryptPasswordEncoder());

		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("SELECT email as principal, password as credentials, etat FROM demandeur WHERE email =  ?")
				.authoritiesByUsernameQuery("SELECT email as principal, nom as role FROM demandeurs_roles WHERE email = ?")
				.passwordEncoder(new BCryptPasswordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.formLogin();//pour afficher un formulaire de connexion par defaut
		http.formLogin().loginPage("/online/login");//personnaliser le form de login
		//les droits du role Demandeurs
		http.authorizeRequests().antMatchers("/Demandeur/liste").hasAnyAuthority("ROLE_Demandeurs");
		//les droits du role Entreprise
		http.authorizeRequests().antMatchers("/Entreprise/liste").hasAuthority("ROLE_Entreprise");
		//gestion des droits
		http.exceptionHandling().accessDeniedPage("/online/403");
		http.csrf().disable();
	}

}
