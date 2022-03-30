package app.recipe.restapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;

// TODO: Auto-generated Javadoc
/**
 * The Class SwaggerConfig.
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	/**
	 * Product api.
	 *
	 * @return the docket
	 */
	@Bean
	public Docket recipeApi() {
		List<SecurityContext> list = new ArrayList<SecurityContext>();
		list.add(securityContext());
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("app.recipe.restapi.controller"))
				.build().securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(list).apiInfo(getApiInfo());

	}

	/**
	 * Gets the api info.
	 *
	 * @return the api info
	 */
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Recipe API",
				"This API can be used to create, update, get or delete the recipes",
				"0.1",
				"",
				new Contact("Babu Madgundi","URL","babu_myb@rediffmail.com"),
				"",
				"",
				Collections.emptyList()
				);
	}
	
	  /**
  	 * Api key.
  	 *
  	 * @return the api key
  	 */
  	private ApiKey apiKey() {
	        return new ApiKey("JWT", "Authorization", "header");
	    }

	    /**
    	 * Security context.
    	 *
    	 * @return the security context
    	 */
    	private SecurityContext securityContext() {
	        return SecurityContext.builder()
	            .securityReferences(defaultAuth())
	            .forPaths(PathSelectors.any())
	            .build();
	    }

	    /**
    	 * Default auth.
    	 *
    	 * @return the list
    	 */
    	List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope
	            = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        List securityReference = new ArrayList<SecurityReference>();
	        		securityReference.add(
	            new SecurityReference("JWT", authorizationScopes));
	        return securityReference;
	    }
}
