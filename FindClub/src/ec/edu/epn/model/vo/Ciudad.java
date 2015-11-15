package ec.edu.epn.model.vo;

/***
 * @author Samantha Molina
 */

public class Ciudad {
	private int idCiudad;
	private int idPais;
	private String nombrePais;
	private String nombreCiudad;
	
	public Ciudad(){
		
	}

	public int getIdCiudad() {
		return idCiudad;
	}


	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}


	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}



	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombeCiudad) {
		this.nombreCiudad = nombeCiudad;
	}
	
	
}
