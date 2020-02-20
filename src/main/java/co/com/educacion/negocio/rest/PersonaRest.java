package co.com.educacion.negocio.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.educacion.manejador.CustomErrorMessages;
import co.com.educacion.manejador.OnCreate;
import co.com.educacion.manejador.OnDelete;
import co.com.educacion.manejador.OnUpdate;
import co.com.educacion.negocio.interfaz.PersonaServicio;
import co.com.educacion.negocio.transferencia.PersonaTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.validation.annotation.Validated;


@OpenAPIDefinition(
		info =  @Info(
					title = "API personas",
					description = "Este es un ejemplo de servidor Personas-Server.  "
							+ "Usted puyede encontrar mas acerca de Swagger "
							+ "[http://swagger.io](http://swagger.io) o en "
							+ "[irc.freenode.net, #swagger](http://swagger.io/irc/)."
							+ "Para este ejemplo, usted puede usar el api key `FamiSoft` para testear la autorizacion filters.",
					termsOfService = "http://swagger.io/terms/",
					contact = @Contact(
								email = "famisanar-tecnologia@famisanar.com.co",
								name = "tecnologia contacto",
								url = "https://tecnologia.famisanar.com.co"
							),
					license = @License(
								name = "Apache 2.0",
								url = "http://springdoc.org"
							),
					version = "otra"
					
				),
		security = {
				@SecurityRequirement(
						name = "personas_auth2",
						scopes = { "escritura", "lectura" }
						) 
		}		
		)
@SecurityScheme(
		name = "personas_auth2", 
		type = SecuritySchemeType.OAUTH2, 
		flows = @OAuthFlows(
				
				implicit = @OAuthFlow(
//						redirect_uri=http://localhost:8080
//						https://estudiantes.aha.io/oauth/authorize?client_id=654c3658029e98044ebc71ad9850c2bb383947c8813128451b8830eaf4439b90&redirect_uri=https%3A%2F%2Flocalhost%3A8080%2F&response_type=token
						authorizationUrl = "http://localhost:8081/oauth/authorize", 
//								authorizationUrl = "http://172.17.7.192:8763/platform-security/oauth/token"
						scopes = {
								@OAuthScope(name = "ESCRITURA", description = "modificar las personas"),
								@OAuthScope(name = "LECTURA", description = "leer las personas"),								
								}
//						tokenUrl = "https://login.microsoftonline.com/ce35a8ea-e13d-4d26-8749-ef685f99173c/oauth2/token"
						)
				)
		)
@Tag(name = "persona", description = "API para personas")
@RestController
@RequestMapping("persona")
@Validated
public class PersonaRest extends GeneralRest {

	Logger logger = LoggerFactory.getLogger(PersonaRest.class);

	@Autowired
	PersonaServicio personaServicio;

	@Operation(
			summary = "traer todas las personas", 
			description = "api para traer todas las personas, aqui no se tienen en cuenta paginaciones, ni filtros, trae todos los registros", 
			security = {
					@SecurityRequirement(
							name = "personas_auth2",
							scopes = { "ESCRITURA", "LECTURA" }
							) 
					}, 
			tags = { "persona" }
			)
	@PreAuthorize("#oauth2.hasAnyScope('LECTURA')")
	@GetMapping
	public List<PersonaTO> personas() {
		return personaServicio.obtenerTodo();
	}

	@PostMapping
	@Validated(OnCreate.class)
	public ResponseEntity<Object> agregar(@Valid @RequestBody PersonaTO p) {
		logger.info("La persona : " + p.toString());
		return new ResponseEntity<>(personaServicio.agregar(p), HttpStatus.OK);
	}

	@PostMapping("/actualizar")
	@Validated(OnUpdate.class)
	public ResponseEntity<Object> editar(@Valid @RequestBody PersonaTO p) {
		logger.info("La persona update: " + p.toString());
		return new ResponseEntity<>(personaServicio.agregar(p), HttpStatus.OK);
	}

	@PostMapping("/eliminar")
	@Validated(OnDelete.class)
	public ResponseEntity<Object> eliminar(@Valid @RequestBody PersonaTO p) {
		logger.info("La persona eliminar: " + p.toString());
		try {
			personaServicio.eliminar(p);
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(
					new CustomErrorMessages("No se encontro una persona con el identificador " + p.getCodigo()),
					HttpStatus.OK);
		}

	}
	
}
