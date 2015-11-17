package ec.edu.epn.model.vo;

import java.awt.Image;

public class Discoteca {
	private String nombre="";
	private String pais="";
	private int ciudad;
	private String tipoMusica="";
	private String emailUsr="";
	private String descripcion="";
	private String imagen;
	
	public String getEmailUsr() {
		return emailUsr;
	}

	public void setEmailUsr(String emailUsr) {
		this.emailUsr = emailUsr;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Discoteca() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCiudad() {
		return ciudad;
	}

	public void setCiudad(int ciudad) {
		this.ciudad = ciudad;
	}

	public String getTipoMusica() {
		return tipoMusica;
	}

	public void setTipoMusica(String tipoMusica) {
		this.tipoMusica = tipoMusica;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}