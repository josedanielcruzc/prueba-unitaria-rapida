package co.com.educacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;


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
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };

	/*@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().
				components(
						new Components().addSecuritySchemes(
								"basicScheme", new SecurityScheme().type( SecurityScheme.Type.HTTP).scheme("basic")
						)
				)
				.info( new Info().title("API personas").version("1.0"))
				.addTagsItem( new Tag().name("mi tag") );

	}*/

    }
}
