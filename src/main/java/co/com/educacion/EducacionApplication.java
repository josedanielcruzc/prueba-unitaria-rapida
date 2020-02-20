package co.com.educacion;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SpringBootApplication
public class EducacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducacionApplication.class, args);
    }


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("**").allowedOrigins("*");
			}
		};
	}
	
	
	@Bean
	public GroupedOpenApi personaOpenApi() {
		String[] paths = { "/persona*" };
		String[] packagedToMatch = { "co.com.educacion.negocio.rest" };
		return GroupedOpenApi.builder().setGroup("Persona lectura").pathsToMatch(paths).packagesToScan(packagedToMatch)
				.build();
	}
	
	@Bean
	public GroupedOpenApi personaOpenApiAdmin() {
		String[] paths = { "/persona/*" };
		String[] packagedToMatch = { "co.com.educacion.negocio.rest" };
		return GroupedOpenApi.builder().setGroup("Persona Admin").pathsToMatch(paths).packagesToScan(packagedToMatch)
				.build();
	}
	

//							.addSecuritySchemes(
//									"basicScheme", 
//									new SecurityScheme()
//										.type( 
//												SecurityScheme.Type.HTTP )
//										.scheme("basic") )


}










