package co.com.educacion.negocio.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.educacion.manejador.PrefijoSecuenciaIdGenerador;

/**
 * The persistent class for the direccion database table.
 * 
 */
@Entity
@Table(name = "direccion")
public class Direccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIRECCION_CODIGO_GENERATOR")
	@GenericGenerator(name = "DIRECCION_CODIGO_GENERATOR", strategy = "co.com.educacion.manejador.PrefijoSecuenciaIdGenerador", parameters = {
			@Parameter(name = PrefijoSecuenciaIdGenerador.SEQUENCE_PARAM, value = "sec_direccion_id"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.PREFIJO_PARAMETRO, value = "DR"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.FORMATO_PARAMETRO, value = "%04d") })
	@Column(name = "codigo", unique = true, nullable = false, length = 6)
	private String codigo;

	@Column(nullable = false, length = 100)
	private String calle;

	@Column(nullable = false, length = 100)
	private String ciudad;

	@Column(name = "codigo_postal", length = 6)
	private String codigoPostal;

	@Column(nullable = false, length = 100)
	private String estado;

	@Column(nullable = false, length = 100)
	private String pais;

	@OneToOne(mappedBy = "direccion", cascade = CascadeType.ALL)
	private Persona persona;

	public Direccion() {
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}