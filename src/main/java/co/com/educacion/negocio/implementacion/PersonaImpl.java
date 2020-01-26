package co.com.educacion.negocio.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.educacion.negocio.interfaz.PersonaServicio;
import co.com.educacion.negocio.persistencia.PersonaRep;
import co.com.educacion.negocio.persistencia.entidad.Persona;
import co.com.educacion.negocio.transferencia.PersonaTO;

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

	public PersonaTO agregar(PersonaTO p) {
		Persona cDB = personaRep.save(p.entidad());
		return PersonaTO.objectTransfer(cDB);
	}

}
