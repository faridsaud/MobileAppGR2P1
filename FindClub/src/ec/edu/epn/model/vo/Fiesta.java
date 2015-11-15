package ec.edu.epn.model.vo;

import java.sql.Date;

/**
 * @author Samantha Molina
 */
public class Fiesta {
	private int idFiesta;
	private String email;
	private int idDiscoteca;
	private String nombreFiesta;
	private String fecha;
	private String hora;
	private String descripción;
	
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
	public String getDescripción() {
		return descripción;
	}
	public void setDescripción(String descripción) {
		this.descripción = descripción;
	}
	
}
