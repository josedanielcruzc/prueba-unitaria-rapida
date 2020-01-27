package co.com.educacion.negocio.persistencia.entidad;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import co.com.educacion.manejador.PrefijoSecuenciaIdGenerador;

/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@Table(name = "persona")
@NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONA_CODIGO_GENERATOR")
	@GenericGenerator(name = "PERSONA_CODIGO_GENERATOR", strategy = "co.com.educacion.manejador.PrefijoSecuenciaIdGenerador", parameters = {
			@Parameter(name = PrefijoSecuenciaIdGenerador.SEQUENCE_PARAM, value = "sec_persona_id"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.INCREMENT_PARAM, value = "1"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.PREFIJO_PARAMETRO, value = "PR"),
			@Parameter(name = PrefijoSecuenciaIdGenerador.FORMATO_PARAMETRO, value = "%04d") })
	@Column(name = "codigo", unique = true, nullable = false, length = 6)
	private String codigo;

	@Column(nullable = false, length = 150)
	private String mail;

	@Column(nullable = false, length = 150)
	private String nombre;

	@Column(nullable = false)
	private Integer telefono;

	// bi-directional many-to-one association to Direccion
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "direccion", referencedColumnName = "codigo")
	private Direccion direccion;

	public Persona() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

}