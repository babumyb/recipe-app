package app.recipe.restapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The Class OAuthSecurityConfig.
 */
@EnableWebSecurity

public class OAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	/** The security enabled. */
	@Value("${security.enabled:true}")
	private boolean securityEnabled;
	/**
	 * Configure.
	 *
	 * @param http the http
	 * @throws Exception the exception
	 */
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
		if (securityEnabled) {
			http.authorizeRequests(authorizeRequests -> authorizeRequests.antMatchers("/api/*").authenticated().anyRequest().permitAll())
            .oauth2ResourceServer().jwt();
		} else {
			http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().permitAll());
		}
        
    }
	 
}
