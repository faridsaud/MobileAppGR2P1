package ec.edu.epn.model.service.pais;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import ec.edu.epn.model.vo.Pais;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/***
 * @author Samantha Molina
 */
public class ServicePais {
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://192.168.216.131:3306/movilDBPrueba";
	private String userName = "bases";
	private String password = "bases";
	
	private java.sql.Connection establecerConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		java.sql.Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}
	
	public boolean existePais(String nombrePais){
		Pais pais = buscarPais(nombrePais);

		try{
			if (pais.getNombrePais().equals(nombrePais)){
				return true;
			}
		}catch(Exception e){
			return false;
		}
		return false;
	}
	
	public void registrarPais(Pais pais){
		boolean insertarRegistro = existePais(pais.getNombrePais());
		
		if (insertarRegistro == false){
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = con.prepareStatement("Insert into PAIS (IDPAIS, NOMBREPAIS) values (NULL, ?)");
				st.setString(1, pais.getNombrePais());
				st.execute();
				st.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Pais buscarPais(String nombrePais){
		Pais pais = new Pais();
		PreparedStatement st = null;
		try {
			java.sql.Connection con = establecerConexion();
			st = con.prepareStatement("Select * from PAIS where NOMBREPAIS = ?");
			st.setString(1, nombrePais);
			st.execute();
			
			ResultSet rs = st.getResultSet();
			while (rs.next()){
				pais.setIdPais(rs.getInt("IDPAIS"));
				pais.setNombrePais(rs.getString("NOMBREPAIS"));
			}
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pais;
	}
	
	public Pais buscarPais(int identificadorPais){
		Pais pais = new Pais();
		PreparedStatement st = null;
		try {
			java.sql.Connection con = establecerConexion();
			st = con.prepareStatement("Select * from PAIS where IDPAIS = ?");
			st.setInt(1, identificadorPais);
			st.execute();
			
			ResultSet rs = st.getResultSet();
			while (rs.next()){
				pais.setIdPais(rs.getInt("IDPAIS"));
				pais.setNombrePais(rs.getString("NOMBREPAIS"));
			}
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pais;
	}
	
	public List<Pais> listarPais(Pais pais){
		List<Pais> listaPais = new ArrayList<Pais>();
		PreparedStatement st;
		
		try {
			java.sql.Connection con = establecerConexion();
			if(pais.getNombrePais().equals("")){
				st = con.prepareStatement("Select * from PAIS ORDER BY NOMBREPAIS");
			}else{
				st = con.prepareStatement("Select * from PAIS where NOMBREPAIS LIKE ? ORDER BY NOMBREPAIS");
				st.setString(1, "%"+pais.getNombrePais()+"%");
			}
			st.execute();
			ResultSet rs = st.getResultSet();
			
			while (rs.next()){
				Pais p = new Pais();
				p.setNombrePais(rs.getString("NOMBREPAIS"));
				listaPais.add(p);
			}
			
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaPais;
	}
	
	public void modificarPais(Pais paisModificar, Pais paisModificador){
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = con.prepareStatement("Update PAIS set NOMBREPAIS = ?  where NOMBREPAIS = ?");
			st.setString(1, paisModificador.getNombrePais());
			st.setString(2, paisModificar.getNombrePais());
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
	
	public void eliminarPais(Pais pais){
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = con.prepareStatement("Delete from PAIS where NOMBREPAIS = ?");
			st.setString(1, pais.getNombrePais());
			st.execute();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
