package de.zettsystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.vaadin.spring.http.HttpService;
import org.vaadin.spring.security.annotation.EnableVaadinSharedSecurity;
import org.vaadin.spring.security.config.VaadinSharedSecurityConfiguration;
import org.vaadin.spring.security.web.VaadinRedirectStrategy;
import org.vaadin.spring.security.web.authentication.VaadinAuthenticationSuccessHandler;
import org.vaadin.spring.security.web.authentication.VaadinUrlAuthenticationSuccessHandler;

/**
 * Bootstrap class.
 *
 * @author Michael
 *
 */
@SpringBootApplication
public class FeuTrainer {

	/**
	 * Starts the server.
	 *
	 * @param args
	 *            to use
	 */
	public static void main(String[] args) {
		SpringApplication.run(FeuTrainer.class, args);
	}

	/**
	 * Configure Spring Security.
	 */
	@Configuration
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
	@EnableVaadinSharedSecurity
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser("student").password("student").roles("STUDENT").and()
					.withUser("admin").password("admin").roles("ADMIN").and().withUser("data").password("data")
					.roles("DATA");
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable(); // Use Vaadin's built-in CSRF protection
									// instead
			http.authorizeRequests().antMatchers("/login/**").anonymous().antMatchers("/vaadinServlet/UIDL/**")
					.permitAll().antMatchers("/vaadinServlet/HEARTBEAT/**").permitAll().anyRequest().authenticated();
			http.httpBasic().disable();
			http.formLogin().disable();
			http.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
			http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/VAADIN/**");
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

		@Bean(name = VaadinSharedSecurityConfiguration.VAADIN_AUTHENTICATION_SUCCESS_HANDLER_BEAN)
		VaadinAuthenticationSuccessHandler vaadinAuthenticationSuccessHandler(HttpService httpService,
				VaadinRedirectStrategy vaadinRedirectStrategy) {
			return new VaadinUrlAuthenticationSuccessHandler(httpService, vaadinRedirectStrategy, "/");
		}
	}

}
