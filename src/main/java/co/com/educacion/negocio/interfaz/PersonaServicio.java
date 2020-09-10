package co.com.educacion.negocio.interfaz;

import java.util.List;

import co.com.educacion.negocio.transferencia.PersonaTO;

public interface PersonaServicio {

	List<PersonaTO> obtenerTodo();

	PersonaTO agregar(PersonaTO p);

	public PersonaTO editar(PersonaTO p);

	public boolean eliminar(PersonaTO p);

}
