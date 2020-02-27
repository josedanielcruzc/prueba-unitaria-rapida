package co.com.educacion.manejador.configuracion;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class ConfiguracionSeguridadWeb extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/swagger-ui/**")
			.antMatchers("/v3/api-docs/**")
		;
	}
	
}
