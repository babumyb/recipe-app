package app.recipe.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// TODO: Auto-generated Javadoc
/**
 * The Class RestApiApplication.
 */
@SpringBootApplication
public class RestApiApplication extends SpringBootServletInitializer {

	/** The Constant APPLICATIONCLASS. */
	private static final Class<RestApiApplication> APPLICATIONCLASS = RestApiApplication.class;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {
		SpringApplication.run(APPLICATIONCLASS, args); }


	/**
	 * Configuration to run on the web container.
	 *
	 * @param application the application
	 * @return the spring application builder
	 */
	@Override
	protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
		return application.sources(APPLICATIONCLASS);
	}

}
