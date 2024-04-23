package bforbank.cagnotte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for the ProjetCagnotteApplication.
 * It uses the @SpringBootApplication annotation, which is a convenience annotation
 * that adds all the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
 *   other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in
 *   the 'bforbank.cagnotte' package, allowing it to find and register the controllers.
 */
@SpringBootApplication
public class ProjetCagnotteApplication {

    /**
     * The main method uses Spring Boot’s SpringApplication.run() method to launch an application.
     * @param args The command line arguments
     */
	public static void main(String[] args) {
		SpringApplication.run(ProjetCagnotteApplication.class, args);
	}

}