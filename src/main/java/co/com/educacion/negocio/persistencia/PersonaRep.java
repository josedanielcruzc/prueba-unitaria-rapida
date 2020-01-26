package co.com.educacion.negocio.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.educacion.negocio.persistencia.entidad.Persona;

public interface PersonaRep extends JpaRepository<Persona, String> {

}
