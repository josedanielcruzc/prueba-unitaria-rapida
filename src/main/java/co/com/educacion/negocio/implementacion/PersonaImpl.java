package co.com.educacion.negocio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.com.educacion.negocio.interfaz.PersonaServicio;
import co.com.educacion.negocio.persistencia.PersonaRep;
import co.com.educacion.negocio.persistencia.entidad.Persona;
import co.com.educacion.negocio.transferencia.PersonaTO;

@Service
public class PersonaImpl implements PersonaServicio {

	@Autowired
	PersonaRep personaRep;

	@Override
	public List<PersonaTO> obtenerTodo() {
		List<Persona> personas = personaRep.findAll();
		List<PersonaTO> personasTO = new ArrayList<>();
		personas.forEach(p -> {
			personasTO.add(PersonaTO.objectTransfer(p));
		});

		return personasTO;
	}

	@Transactional
	public PersonaTO agregar(PersonaTO p) {
		Persona cDB = personaRep.save(p.entidad());
		return PersonaTO.objectTransfer(cDB);
	}

	@Transactional
	public PersonaTO editar(PersonaTO p) {
		Persona cDB = personaRep.save(p.entidad());
		return PersonaTO.objectTransfer(cDB);
	}

	@Transactional
	public boolean eliminar(PersonaTO p) {
			//personaRep.deleteById(p.getCodigo());
			return true;
	}

}
