package ec.edu.epn.model.service.cuenta;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import ec.edu.epn.model.vo.Usuario;

@Path("/Usuario/")
@Produces("application/json")
public class ServiceUsuario {


	@GET
	@Path("/buscar")
	public Usuario buscarUsuario(@QueryParam("email")String email, @QueryParam("password")String password) {
		Usuario usr = new Usuario();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from USUARIO where EMAILUSR=? and PASSWORDUSR=? and ESTADOUSR=?");
			st.setString(1, email);
			st.setString(2, password);
			st.setBoolean(3, true);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
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

	@POST
	@Path("/")
	public void registrarUsuario(@QueryParam("email")String email,@QueryParam("password")String password,@QueryParam("nombre")String nombre,@QueryParam("apellido")String apellido) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"Insert into USUARIO (EMAILUSR,PASSWORDUSR, NOMBREUSR, APELLIDOUSR, ADMINUSR, ESTADOUSR) values (?,?,?,?,?,?) ");
			st.setString(1, email);
			st.setString(2, password);
			st.setString(3, nombre);
			st.setString(4, apellido);
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

	@GET
	@Path("/")
	public List<Usuario> listarUsuario(@QueryParam("email")String emailUsuario,@QueryParam("emailLog")String emailLogeado) {
		ServiceUsuario su= new ServiceUsuario();
		Usuario usrLogeado=su.buscarUsuarioByEmail(emailLogeado);
		if(emailUsuario==null){
			emailUsuario="";
		}
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = null;
			if ((usrLogeado.isAdmin() == true) && (emailUsuario.equals(""))) {
				st = con.prepareStatement("Select * from USUARIO");
			}
			if ((usrLogeado.isAdmin() == true) && (emailUsuario.equals("") == false)) {
				st = con.prepareStatement("Select * from USUARIO where EMAILUSR LIKE ?");
				st.setString(1, "%" + emailUsuario + "%");
			}
			if ((usrLogeado.isAdmin() == false)) {
				st = con.prepareStatement("Select * from USUARIO where EMAILUSR=?");
				st.setString(1, usrLogeado.getEmail());
			}
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Usuario usr = new Usuario();
				usr.setNombre(rs.getString("NOMBREUSR"));
				usr.setEmail(rs.getString("EMAILUSR"));
				usr.setPassword(rs.getString("PASSWORDUSR"));
				usr.setApellido(rs.getString("APELLIDOUSR"));
				usr.setAdmin(rs.getBoolean("ADMINUSR"));
				usr.setEstado(rs.getBoolean("ESTADOUSR"));
				listaUsuarios.add(usr);
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
		return listaUsuarios;
	}

	@PUT
	@Path("/")
	public void modificarUsuario(@QueryParam("email") String email, @QueryParam("newPassword") String newPassword, @QueryParam("newNombre") String newNombre, @QueryParam("newApellido") String newApellido, @QueryParam("newEmail") String newEmail) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"UPDATE USUARIO SET EMAILUSR=?,PASSWORDUSR=?, NOMBREUSR=?, APELLIDOUSR=? WHERE EMAILUSR=?");
			st.setString(1, newEmail);
			st.setString(2, newPassword);
			st.setString(3, newNombre);
			st.setString(4, newApellido);
			st.setString(5, email);
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
	
	@GET
	@Path("/{email}")
	public Usuario buscarUsuarioByEmail(@PathParam("email")String email) {
		Usuario usr = new Usuario();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from USUARIO where EMAILUSR=?");
			st.setString(1, email);

			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
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
	
	@DELETE
	@Path("/{email}")
	public void eliminarUsuario(@PathParam("email")String email) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("delete from USUARIO where EMAILUSR=?");
			st.setString(1, email);
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
