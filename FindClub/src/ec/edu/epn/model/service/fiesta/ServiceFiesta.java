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

/***
 * @author Samantha Molina
 */
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
	
	public boolean existeFiesta(String nombreFiesta, String email){
		Fiesta fiesta = new Fiesta();
		buscarFiesta(fiesta, nombreFiesta, email);
		
		try{
			if (fiesta.getNombreFiesta().equals(nombreFiesta)){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	public void registrarFiesta(Fiesta fiesta){
		if (existeFiesta(fiesta.getNombreFiesta(), fiesta.getEmail()) == false){
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = con.prepareStatement("insert into FIESTA (IDFIESTA, EMAILUSR, IDDISCOTECA, NOMBREFIESTA, FECHAFIESTA, HORAFIESTA, DESCRIPCIONFIESTA) values (NULL,?,?,?,?,?,?)");
				st.setString(1, fiesta.getEmail());
				st.setInt(2, fiesta.getIdDiscoteca());
				st.setString(3, fiesta.getNombreFiesta());
				st.setString(4, fiesta.getFecha());
				st.setString(5, fiesta.getHora());
				st.setString(6, fiesta.getDescripcion());
				
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
	
	public Fiesta buscarFiesta(Fiesta fiesta, String nombreFiesta, String emailUsuario){
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			if ((nombreFiesta.equals("")) && (emailUsuario.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA");
			}
			else if ((nombreFiesta.equals("")) && (!emailUsuario.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA where EMAILUSR = ?");
				st.setString(1, "%" + emailUsuario + "%");
			}
			else if ((!nombreFiesta.equals("")) && (!emailUsuario.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA where EMAILUSR = ? and NOMBREFIESTA = ?");
				st.setString(1, emailUsuario);
				st.setString(2, nombreFiesta);
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
				fiesta.setDescripcion(rs.getString("DESCRIPCIONFIESTA"));
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
	
	public List<Fiesta> listarFiesta(Fiesta fiesta, Usuario usr){
		List<Fiesta> listaFiesta = new ArrayList<Fiesta>();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		PreparedStatement st = null;
		try{
			java.sql.Connection con = establecerConexion();
			if (usr.isAdmin() == true){
				st = con.prepareStatement("SELECT * FROM FIESTA WHERE IDDISCOTECA=?");
				st.setInt(1, fiesta.getIdDiscoteca());System.out.println(fiesta.getIdDiscoteca());
			}else{
				st = con.prepareStatement("SELECT * FROM FIESTA WHERE IDDISCOTECA=? and EMAILUSR=?");
				st.setInt(1, fiesta.getIdDiscoteca());
				st.setString(2, usr.getEmail());System.out.println("rrr"+usr.getEmail()+" "+fiesta.getIdDiscoteca());
			}
			st.execute();
			ResultSet rs = st.getResultSet();
			
			while (rs.next()){
				Fiesta f = new Fiesta();
				f.setIdFiesta(rs.getInt("IDFIESTA"));
				f.setNombreFiesta(rs.getString("NOMBREFIESTA"));
				f.setIdDiscoteca(rs.getInt("IDDISCOTECA"));
				f.setEmail(rs.getString("EMAILUSR"));
				f.setFecha(rs.getString("FECHAFIESTA"));
				f.setHora(rs.getString("HORAFIESTA"));
				f.setDescripcion(rs.getString("DESCRIPCIONFIESTA"));
				f.setNombreDiscoteca(sd.buscarDiscoteca(f.getIdDiscoteca()).getNombre());
				listaFiesta.add(f);
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
		return listaFiesta;
	}
//	
//	public void modificarCiudad(Ciudad ciudadModificar, Ciudad ciudadModificador){
//		Fiesta fiesta = new Fiesta();
//		if (existeFiesta(fiesta.getNombreFiesta()) == false){
//		ServicePais sp = new ServicePais();
//				
//			try {
//				java.sql.Connection con = establecerConexion();
//				PreparedStatement st = 
//					con.prepareStatement("Update CIUDAD set NOMBRECIUDAD=?, IDPAIS=? where NOMBRECIUDAD=?");
//				st.setString(1, ciudadModificador.getNombreCiudad());
//				st.setInt(2, sp.buscarPais(cuidad, identificadorPais).identificadorPais(ciudadModificador.getNombrePais()));
//				st.setString(3, ciudadModificar.getNombreCiudad());
//				st.execute();
//				st.close();
//				con.close();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	public void eliminarCiudad(Ciudad ciudad){
//		try {
//			java.sql.Connection con = establecerConexion();
//			PreparedStatement st = con.prepareStatement("Delete from CIUDAD where NOMBRECIUDAD=?");
//			st.setString(1, ciudad.getNombreCiudad());
//			st.execute();
//			st.close();
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
