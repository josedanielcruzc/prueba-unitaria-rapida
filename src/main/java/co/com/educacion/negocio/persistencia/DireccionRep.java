package co.com.educacion.negocio.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.educacion.negocio.persistencia.entidad.Direccion;

@Repository
public interface DireccionRep extends JpaRepository<Direccion, String> {

}
