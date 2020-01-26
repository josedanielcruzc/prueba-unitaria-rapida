package co.com.educacion.negocio.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.educacion.negocio.interfaz.PersonaServicio;
import co.com.educacion.negocio.transferencia.PersonaTO;

@RestController
@RequestMapping("persona")
public class PersonaRest {

	Logger logger = LoggerFactory.getLogger(PersonaRest.class);

	@Autowired
	PersonaServicio personaServicio;

	@GetMapping
	public List<PersonaTO> clientes() {
		return personaServicio.obtenerTodo();
	}

	@PostMapping
	public ResponseEntity<Object> agregar(@Valid @RequestBody PersonaTO p) {
		logger.info("La persona : " + p.toString());
		return new ResponseEntity<>(personaServicio.agregar(p), HttpStatus.OK);
	}

}
