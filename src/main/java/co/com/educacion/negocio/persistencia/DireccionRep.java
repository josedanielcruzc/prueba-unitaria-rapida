package co.com.educacion.negocio.persistencia;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.educacion.negocio.persistencia.entidad.Direccion;

public interface DireccionRep extends JpaRepository<Direccion, String> {

}
