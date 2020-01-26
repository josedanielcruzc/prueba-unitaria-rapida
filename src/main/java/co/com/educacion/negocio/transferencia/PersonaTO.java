package co.com.educacion.negocio.transferencia;

import java.io.Serializable;

import co.com.educacion.negocio.persistencia.entidad.Persona;

public class PersonaTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String codigo;

	private String mail;

	private String nombre;

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
		p.setDireccion(this.direccionTO.entidad());
		return p;
	}

	public static PersonaTO objectTransfer(Persona per) {
		PersonaTO p = new PersonaTO();
		p.setCodigo(per.getCodigo());
		p.setMail(per.getMail());
		p.setNombre(per.getNombre());
		p.setTelefono(per.getTelefono());
		p.setDireccionTO(DireccionTO.objectTransfer(per.getDireccion()));
		return p;
	}
}