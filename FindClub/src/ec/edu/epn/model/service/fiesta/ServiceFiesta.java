package ec.edu.epn.model.service.fiesta;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Fiesta;
import ec.edu.epn.model.vo.Usuario;

public class ServiceFiesta {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.216.131:3306/movilDBPrueba";
	private String userName = "bases";
	private String password = "bases";
	
	private java.sql.Connection establecerConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		java.sql.Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}
	
	public boolean existeFiesta(String nombreFiesta){
		Fiesta fiesta = new Fiesta();
		Usuario usrLogeado = new Usuario();
		usrLogeado.setEmail("");
		
		buscarFiesta(fiesta, nombreFiesta, usrLogeado, usrLogeado.getEmail());

		try{
			if (fiesta.getNombreFiesta().equals(nombreFiesta)){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	public void registrarCiudad(Fiesta fiesta){
		if (existeFiesta(fiesta.getNombreFiesta()) == false){
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = con.prepareStatement("insert into FIESTA (IDFIESTA, EMAILUSR, IDDISCOTECA, NOMBREFIESTA, FECHAFIESTA, HORAFIESTA, DESCRIPCIONFIESTA) values (NULL,?,?,?,?,?,?)");
				st.setString(1, fiesta.getEmail());
				st.setInt(2, fiesta.getIdDiscoteca());
				st.setString(3, fiesta.getNombreFiesta());
				st.setString(3, fiesta.getFecha());
				st.setString(4, fiesta.getHora());
				st.setString(5, fiesta.getDescripción());
				
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
	
	public Fiesta buscarFiesta(Fiesta fiesta, String nombreFiesta, Usuario usrLogeado, String emailUsuario){
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			if ((usrLogeado.isAdmin() == true) && (emailUsuario.equals("")) && (nombreFiesta.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA");
			}
			else if ((usrLogeado.isAdmin() == true) && (nombreFiesta.equals("") == false) && (nombreFiesta.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA where EMAILUSR LIKE ?");
				st.setString(1, "%" + emailUsuario + "%");
			}
			else if ((usrLogeado.isAdmin() == true) && (emailUsuario.equals("") == false) && (nombreFiesta.equals("") == false)) {
				st = con.prepareStatement("Select * from FIESTA where EMAILUSR LIKE ? and NOMBREFIESTA LIKE ?");
				st.setString(1, "%" + emailUsuario + "%");
				st.setString(2, "%" + nombreFiesta + "%");
			}
			else if ((usrLogeado.isAdmin() == false) && (nombreFiesta.equals(""))) {
				st = con.prepareStatement("Select * from USUARIO where EMAILUSR=?");
				st.setString(1, usrLogeado.getEmail());
			}
			else if ((usrLogeado.isAdmin() == false) && (nombreFiesta.equals("") == false)) {
				st = con.prepareStatement("Select * from USUARIO where EMAILUSR=?");
				st.setString(1, usrLogeado.getEmail());
				st.setString(2, "%" + nombreFiesta + "%");
			}
			st.execute();
			
			ResultSet rs = st.getResultSet();
			while (rs.next()){
				fiesta.setIdFiesta(rs.getInt("IDFIESTA"));
				fiesta.setNombreFiesta(rs.getString("NOMBREFIESTA"));
				fiesta.setIdDiscoteca(rs.getShort("IDDISCOTECA"));
				fiesta.setEmail(rs.getString("EMAILUSR"));
				fiesta.setFecha(rs.getDate("FECHAFIESTA").toString());
				fiesta.setHora(rs.getTime("HORAFIESTA").toString());
				fiesta.setDescripción(rs.getString("DESCRIPCIONFIESTA"));
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
		
		return fiesta;
	}
	
	public List<Fiesta> listarFiesta(Fiesta fiesta){
		List<Fiesta> listaFiesta = new ArrayList<Fiesta>();
		PreparedStatement st;
		ServiceCiudad sc = new ServiceCiudad();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		ServiceMusica sm = new ServiceMusica();
		
//		try{
//			java.sql.Connection con = establecerConexion();
//			if (ciudad.getNombreCiudad().equals("")){
//				st = con.prepareStatement("Select * from CIUDAD where IDPAIS = ?");
//				st.setInt(1, sp.identificadorPais(ciudad.getNombrePais()));
//			}else{
//				st = con.prepareStatement("Select * from CIUDAD where IDPAIS = ? and NOMBRECIUDAD = ?");
//				st.setInt(1, sp.identificadorPais(ciudad.getNombrePais()));
//				st.setString(2, ciudad.getNombreCiudad());
//			}
//			st.execute();
//			ResultSet rs = st.getResultSet();
//			
//			while (rs.next()){
//				Ciudad c = new Ciudad();
//				c.setIdPais(rs.getInt("IDPAIS"));
//				c.setIdCiudad(rs.getInt("IDCIUDAD"));
//				c.setNombreCiudad(rs.getString("NOMBRECIUDAD"));
//				c.setNombrePais(sp.nombrePais(c.getIdPais()));
//				listaCiudad.add(c);
//			}
//			
//			st.close();
//			con.close();
//		}catch(Exception e){
//		}
		return listaFiesta;
	}
	
	public void modificarCiudad(Ciudad ciudadModificar, Ciudad ciudadModificador){
		Fiesta fiesta = new Fiesta();
		if (existeFiesta(fiesta.getNombreFiesta()) == false){
		ServicePais sp = new ServicePais();
				
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = 
					con.prepareStatement("Update CIUDAD set NOMBRECIUDAD=?, IDPAIS=? where NOMBRECIUDAD=?");
				st.setString(1, ciudadModificador.getNombreCiudad());
				st.setInt(2, sp.identificadorPais(ciudadModificador.getNombrePais()));
				st.setString(3, ciudadModificar.getNombreCiudad());
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
			PreparedStatement st = con.prepareStatement("Delete from CIUDAD where NOMBRECIUDAD=?");
			st.setString(1, ciudad.getNombreCiudad());
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
