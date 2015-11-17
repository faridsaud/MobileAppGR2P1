package ec.edu.epn.model.vo;

import java.sql.Date;

/**
 * @author Samantha Molina
 */
public class Fiesta {
	private int idFiesta;
	private String email;
	private int idDiscoteca;
	private String nombreDiscoteca;
	private String nombreFiesta;
	private String fecha;
	private String hora;
	private String descripcion;
	
	public Fiesta(){
		
	}
	
	
	public String getNombreFiesta() {
		return nombreFiesta;
	}
	public void setNombreFiesta(String nombreFiesta) {
		this.nombreFiesta = nombreFiesta;
	}
	public int getIdFiesta() {
		return idFiesta;
	}
	public void setIdFiesta(int idFiesta) {
		this.idFiesta = idFiesta;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdDiscoteca() {
		return idDiscoteca;
	}
	public void setIdDiscoteca(int idDiscoteca) {
		this.idDiscoteca = idDiscoteca;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getNombreDiscoteca() {
		return nombreDiscoteca;
	}
	public void setNombreDiscoteca(String nombreDiscoteca) {
		this.nombreDiscoteca = nombreDiscoteca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
