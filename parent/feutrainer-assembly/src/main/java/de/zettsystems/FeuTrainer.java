package de.zettsystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Bootstrap class.
 *
 * @author Michael
 *
 */
@SpringBootApplication
@EnableJpaRepositories
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
}
