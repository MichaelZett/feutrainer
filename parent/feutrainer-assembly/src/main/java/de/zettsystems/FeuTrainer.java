package de.zettsystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
