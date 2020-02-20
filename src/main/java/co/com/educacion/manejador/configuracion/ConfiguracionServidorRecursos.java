package co.com.educacion.manejador.configuracion;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ConfiguracionServidorRecursos extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
			.resourceId("recursos-server-rest-api")
			.stateless(false)
			.authenticationManager(authenticationManagerBean())
			.tokenExtractor(new ExtractorTokensPersonalizado()
						);
	}

	@Bean
	public ResourceServerTokenServices tokenService() {
		ServicioTokenRemotoPersonalizado tokenServices = new ServicioTokenRemotoPersonalizado();
		return tokenServices;
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
		authenticationManager.setTokenServices(tokenService());
		return authenticationManager;
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
//		.anonymous( anonymous -> { 
//			anonymous.authorities("ROLE_ANONIMO"); 
//			})
		.headers().frameOptions().disable()
		.and().authorizeRequests()
		.antMatchers("/swagger-ui/**").permitAll()
		.antMatchers("/documentacion-api/**").permitAll()
		.anyRequest().authenticated()
		;

	}

	
	

	
//		.and().authorizeRequests()
//		.antMatchers( HttpMethod.POST, "/persona*").access("hasRole('USUARIO')")
//		.antMatchers("/admin", "/admin/oauth").access("hasRole('USUARIO') and hasRole('ADMIN') ")
//		.anyRequest().authenticated()
//		.and().formLogin().permitAll()
//		.and().logout().permitAll()
//		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler())
//		;
//	}
	
	
	
	
}
