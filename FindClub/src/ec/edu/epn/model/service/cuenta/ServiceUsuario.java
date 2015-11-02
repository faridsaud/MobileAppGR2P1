package ec.edu.epn.model.service.cuenta;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ec.edu.epn.model.vo.Usuario;

public class ServiceUsuario {
	
	public Usuario buscarUsuario(String email, String password) {
		Usuario usr = new Usuario();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st=con.prepareStatement("Select * from USUARIO where EMAILUSR=? and PASSWORDUSR=? and ESTADOUSR=?");
			st.setString(1, email);
			st.setString(2, password);
			st.setBoolean(3, true);
			st.execute();
			ResultSet rs=st.getResultSet();
			 while(rs.next()){
		    	  usr.setNombre(rs.getString("NOMBREUSR"));
		    	  usr.setEmail(rs.getString("EMAILUSR"));
		    	  usr.setPassword(rs.getString("PASSWORDUSR"));
		    	  usr.setApellido(rs.getString("APELLIDOUSR"));
		    	  usr.setAdmin(rs.getBoolean("ADMINUSR"));
		    	  usr.setEstado(rs.getBoolean("ESTADOUSR"));
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
		return usr;
	}
	
	public void registrarUsuario(Usuario usr){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st=con.prepareStatement("Insert into USUARIO (EMAILUSR,PASSWORDUSR, NOMBREUSR, APELLIDOUSR, ADMINUSR, ESTADOUSR) values (?,?,?,?,?,?) ");
			st.setString(1,usr.getEmail());
			st.setString(2,usr.getPassword());
			st.setString(3,usr.getNombre());
			st.setString(4,usr.getApellido());
			st.setBoolean(5, false);
			st.setBoolean(6, true);
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
