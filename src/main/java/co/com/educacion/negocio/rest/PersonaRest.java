package co.com.educacion.negocio.rest;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.educacion.manejador.CustomErrorMessages;
import co.com.educacion.manejador.OnCreate;
import co.com.educacion.manejador.OnDelete;
import co.com.educacion.manejador.OnUpdate;
import co.com.educacion.negocio.interfaz.PersonaServicio;
import co.com.educacion.negocio.transferencia.PersonaTO;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("persona")
@Validated
public class PersonaRest extends GeneralRest {

	Logger logger = LoggerFactory.getLogger(PersonaRest.class);

	@Autowired
	PersonaServicio personaServicio;

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
