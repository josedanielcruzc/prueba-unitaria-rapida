package co.com.educacion.negocio.transferencia;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import co.com.educacion.manejador.OnCreate;
import co.com.educacion.manejador.OnUpdate;
import co.com.educacion.negocio.persistencia.entidad.Direccion;

public class DireccionTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank(groups = { OnUpdate.class }, message = "odigo obligatorio")
	@NotNull(groups = { OnUpdate.class }, message = "codigo obligatorio")
	@Length(groups = { OnUpdate.class }, min = 6, max = 6, message = "cantidad de caracteres permitidos 6")
	private String codigo;

	@NotBlank(groups = { OnUpdate.class, OnCreate.class }, message = "calle obligatoria")
	@NotNull(groups = { OnUpdate.class, OnCreate.class }, message = "calle obligatoria")
	@Length(groups = { OnUpdate.class,
			OnCreate.class }, min = 1, max = 100, message = "cantidad maxima de caracteres 100")
	private String calle;

	@NotBlank(groups = { OnUpdate.class, OnCreate.class }, message = "calle obligatoria")
	@NotNull(groups = { OnUpdate.class, OnCreate.class }, message = "calle obligatoria")
	@Length(groups = { OnUpdate.class,
			OnCreate.class }, min = 1, max = 100, message = "cantidad maxima de caracteres 100")
	private String ciudad;

	private String codigoPostal;

	@NotBlank(groups = { OnUpdate.class, OnCreate.class }, message = "estado obligatorio")
	@NotNull(groups = { OnUpdate.class, OnCreate.class }, message = "estado obligatorio")
	@Length(groups = { OnUpdate.class,
			OnCreate.class }, min = 1, max = 100, message = "cantidad maxima de caracteres 100")
	private String estado;

	@NotBlank(groups = { OnUpdate.class, OnCreate.class }, message = "pais obligatorio")
	@NotNull(groups = { OnUpdate.class, OnCreate.class }, message = "pais obligatorio")
	@Length(groups = { OnUpdate.class,
			OnCreate.class }, min = 1, max = 100, message = "cantidad maxima de caracteres 100")
	private String pais;

	public DireccionTO() {
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DireccionTO [codigo=");
		builder.append(codigo);
		builder.append(", calle=");
		builder.append(calle);
		builder.append(", ciudad=");
		builder.append(ciudad);
		builder.append(", codigoPostal=");
		builder.append(codigoPostal);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", pais=");
		builder.append(pais);
		builder.append("]");
		return builder.toString();
	}

	public Direccion entidad() {
		Direccion d = new Direccion();
		d.setCalle(this.calle);
		d.setCiudad(this.ciudad);
		d.setCodigoPostal(this.codigoPostal);
		d.setEstado(this.estado);
		d.setPais(this.pais);
		d.setCodigo(this.codigo);
		return d;
	}

	public static DireccionTO objectTransfer(Direccion dir) {
		DireccionTO d = new DireccionTO();
		d.setCalle(dir.getCalle());
		d.setCiudad(dir.getCiudad());
		d.setCodigoPostal(dir.getCodigoPostal());
		d.setEstado(dir.getEstado());
		d.setPais(dir.getPais());
		d.setCodigo(dir.getCodigo());
		return d;
	}

}