package co.com.educacion.negocio.transferencia;

import java.io.Serializable;

import co.com.educacion.negocio.persistencia.entidad.Direccion;

public class DireccionTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String calle;

	private String ciudad;

	private String codigoPostal;

	private String estado;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Direccion [calle=");
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
		return d;
	}

	public static DireccionTO objectTransfer(Direccion dir) {
		DireccionTO d = new DireccionTO();
		d.setCalle(dir.getCalle());
		d.setCiudad(dir.getCiudad());
		d.setCodigoPostal(dir.getCodigoPostal());
		d.setEstado(dir.getEstado());
		d.setPais(dir.getPais());
		return d;
	}

}