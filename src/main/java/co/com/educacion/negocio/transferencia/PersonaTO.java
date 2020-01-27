package co.com.educacion.negocio.transferencia;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import co.com.educacion.manejador.OnCreate;
import co.com.educacion.manejador.OnDelete;
import co.com.educacion.manejador.OnUpdate;
import co.com.educacion.negocio.persistencia.entidad.Persona;

public class PersonaTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(groups = { OnUpdate.class, OnDelete.class }, message = "codigo obligatorio")
	@NotNull(groups = { OnUpdate.class, OnDelete.class }, message = "codigo obligatorio")
	@Length(groups = { OnUpdate.class }, min = 6, max = 6, message = "cantidad de caracteres permitida 6")
	private String codigo;

	@NotNull(groups = { OnUpdate.class, OnCreate.class }, message = "mail obligatorio")
	@NotBlank(groups = { OnUpdate.class, OnCreate.class }, message = "mail obligatorio")
	@Email(groups = { OnUpdate.class, OnCreate.class }, message = "mail mal formado")
	@Length(groups = { OnUpdate.class, OnCreate.class }, min = 1, max = 150, message = "cantidad maxima de caracteres 150")
	private String mail;

	@NotBlank(groups = { OnUpdate.class, OnCreate.class }, message = "nombre obligatorio")
	@NotNull(groups = { OnUpdate.class, OnCreate.class }, message = "nombre obligatorio")
	@Length(groups = { OnUpdate.class, OnCreate.class }, min = 1, max = 150, message = "cantidad maxima de caracteres 150")
	private String nombre;

	@NotNull(groups = { OnUpdate.class, OnCreate.class }, message = "telefono obligatorio")
	@Max(groups = { OnUpdate.class, OnCreate.class }, value = 2147483647, message = "cantidad maxima de caracteres 2147483647")
	@Min(groups = { OnUpdate.class, OnCreate.class }, value = -2147483648, message = "cantidad maxima de caracteres -2147483647")
	private Integer telefono;

	private DireccionTO direccionTO;

	public PersonaTO() {
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

	public DireccionTO getDireccionTO() {
		return this.direccionTO;
	}

	public void setDireccionTO(DireccionTO direccionTO) {
		this.direccionTO = direccionTO;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonaTO [codigo=");
		builder.append(codigo);
		builder.append(", mail=");
		builder.append(mail);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", direccionTO=");
		builder.append(direccionTO);
		builder.append("]");
		return builder.toString();
	}

	public Persona entidad() {
		Persona p = new Persona();
		p.setCodigo(this.codigo);
		p.setMail(this.mail);
		p.setNombre(this.nombre);
		p.setTelefono(this.telefono);
		p.setDireccion(this.direccionTO != null ? this.direccionTO.entidad() : null);
		return p;
	}

	public static PersonaTO objectTransfer(Persona per) {
		PersonaTO p = new PersonaTO();
		p.setCodigo(per.getCodigo());
		p.setMail(per.getMail());
		p.setNombre(per.getNombre());
		p.setTelefono(per.getTelefono());
		p.setDireccionTO(per.getDireccion() != null ? DireccionTO.objectTransfer(per.getDireccion()) : null);
		return p;
	}
}