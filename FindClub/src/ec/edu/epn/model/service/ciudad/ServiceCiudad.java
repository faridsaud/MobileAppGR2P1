package ec.edu.epn.model.service.ciudad;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Pais;

/**
 * @author Samantha Molina
 */
public class ServiceCiudad {

	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.216.131:3306/movilDBPrueba";
	private String userName = "bases";
	private String password = "bases";
	
	private java.sql.Connection establecerConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		java.sql.Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}
	
	public boolean existeCiudad(String nombreCiudad, String nombrePais){
		Ciudad ciudad = new Ciudad();
		ciudad = buscarCiudad(nombreCiudad, nombrePais);
		try{
			if (ciudad.getNombreCiudad().equals(nombreCiudad)){
				return true; 
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	public void registrarCiudad(Ciudad ciudad){
		boolean insertarRegistro = existeCiudad(ciudad.getNombreCiudad(), ciudad.getNombrePais());
		ServicePais sp = new ServicePais();
				
		if (insertarRegistro == false){
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = con.prepareStatement("insert into CIUDAD (IDCIUDAD, IDPAIS, NOMBRECIUDAD) values (NULL,?,?)");
				st.setInt(1, sp.buscarPais(ciudad.getNombrePais()).getIdPais());
				st.setString(2, ciudad.getNombreCiudad());
				
				st.execute();
				st.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Ciudad buscarCiudad(String nombreCiudad, String nombrePais){
		ServicePais sp= new ServicePais();
		Ciudad ciudad = new Ciudad();
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			st = con.prepareStatement("Select * from CIUDAD where NOMBRECIUDAD = ? and IDPAIS = ?");
			st.setString(1, nombreCiudad);
			st.setInt(2, sp.buscarPais(nombrePais).getIdPais());
			st.execute();
			
			ResultSet rs = st.getResultSet();
			while (rs.next()){
				ciudad.setIdCiudad(rs.getInt("IDCIUDAD"));
				ciudad.setNombreCiudad(rs.getString("NOMBRECIUDAD"));
				ciudad.setIdPais(rs.getInt("IDPAIS"));
			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ciudad;
	}
	
	public Ciudad buscarCiudad(int identificadorCiudad){
		Ciudad ciudad = new Ciudad();
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			st = con.prepareStatement("Select * from CIUDAD where IDCIUDAD = ?");
			st.setInt(1, identificadorCiudad);
			st.execute();
			
			ResultSet rs = st.getResultSet();
			while (rs.next()){
				ciudad.setNombreCiudad(rs.getString("NOMBRECIUDAD"));
				ciudad.setIdPais(rs.getInt("IDPAIS"));
			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ciudad;
	}
	
	public List<Ciudad> listarCiudad(Ciudad ciudad){
		List<Ciudad> listaCiudad = new ArrayList<Ciudad>();
		PreparedStatement st = null;
		ServicePais sp = new ServicePais();
			
		try{
			java.sql.Connection con = establecerConexion();
			if (ciudad.getNombreCiudad().equals("")){
				st = con.prepareStatement("Select * from CIUDAD where IDPAIS = ? ORDER BY NOMBRECIUDAD");
				st.setInt(1, (sp.buscarPais(ciudad.getNombrePais())).getIdPais());
			} 
			else {
				st = con.prepareStatement("Select * from CIUDAD where IDPAIS = ? and NOMBRECIUDAD like ? ORDER BY NOMBRECIUDAD");
				st.setInt(1, (sp.buscarPais(ciudad.getNombrePais())).getIdPais());
				st.setString(2, "%"+ciudad.getNombreCiudad()+"%");
			}
			st.execute();
			ResultSet rs = st.getResultSet();
			
			while (rs.next()){
				Ciudad c = new Ciudad();
				c.setIdPais(rs.getInt("IDPAIS"));
				c.setIdCiudad(rs.getInt("IDCIUDAD"));
				c.setNombreCiudad(rs.getString("NOMBRECIUDAD"));
				c.setNombrePais(sp.buscarPais(c.getIdPais()).getNombrePais());
				listaCiudad.add(c);
			}
			
			st.close();
			con.close();
		}catch(Exception e){
		}
		return listaCiudad;
	}
	
	public void modificarCiudad(Ciudad ciudadModificar, Ciudad ciudadModificador){
		boolean insertarRegistro = existeCiudad(ciudadModificador.getNombreCiudad(), ciudadModificador.getNombrePais());
		ServicePais sp = new ServicePais();
				
		if (insertarRegistro == false){
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = 
					con.prepareStatement("Update CIUDAD set NOMBRECIUDAD=?, IDPAIS=? where IDCIUDAD=?");
				st.setString(1, ciudadModificador.getNombreCiudad());
				st.setInt(2, ciudadModificador.getIdPais());
				st.setInt(3, ciudadModificar.getIdCiudad());
				st.execute();
				st.close();
				con.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarCiudad(Ciudad ciudad){
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = con.prepareStatement("Delete from CIUDAD where NOMBRECIUDAD=? and IDPAIS=?");
			st.setString(1, ciudad.getNombreCiudad());
			st.setInt(2, ciudad.getIdPais());
			st.execute();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
