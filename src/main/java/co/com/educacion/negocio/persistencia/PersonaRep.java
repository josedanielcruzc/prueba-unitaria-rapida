package co.com.educacion.negocio.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.educacion.negocio.persistencia.entidad.Persona;

@Repository
public interface PersonaRep extends JpaRepository<Persona, String> {

}
