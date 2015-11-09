package ec.edu.epn.model.vo;

public class Discoteca {
	private int idDiscoteca;
	private String nombreDiscoteca;
	private String nombreMusica;
	private int idCiudad;
	private String email;
	private String descripcionMusica;
	private String path;
	
	private Discoteca(){
		
	}
	
	public int getIdDiscoteca() {
		return idDiscoteca;
	}
	public void setIdDiscoteca(int idDiscoteca) {
		this.idDiscoteca = idDiscoteca;
	}
	public String getNombreDiscoteca() {
		return nombreDiscoteca;
	}
	public void setNombreDiscoteca(String nombreDiscoteca) {
		this.nombreDiscoteca = nombreDiscoteca;
	}
	public String getNombreMusica() {
		return nombreMusica;
	}
	public void setNombreMusica(String nombreMusica) {
		this.nombreMusica = nombreMusica;
	}
	public int getIdCiudad() {
		return idCiudad;
	}
	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescripcionMusica() {
		return descripcionMusica;
	}
	public void setDescripcionMusica(String descripcionMusica) {
		this.descripcionMusica = descripcionMusica;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
