package ec.edu.epn.model.service.fiesta;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Fiesta;
import ec.edu.epn.model.vo.Usuario;

/***
 * @author Samantha Molina
 */
@Path(value = "Fiesta")
@Produces("application/json")
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

	@GET
	@Path(value = "Prueba")
	public String pruebaFiesta() {
		return "Prueba Fiesta";
	}

	@GET
	@Path(value = "Consultar/{id}")
	public Fiesta consultarFiesta(@PathParam("id") int id) {
		return new Fiesta(id, "sam@dominio.com", 1, "fiestaPrueba", "2016-01-25", "10:00", "Fiesta");
	}

	public boolean existeFiesta(String nombreFiesta, String email) {
		Fiesta fiesta = new Fiesta();

		try {
			if (fiesta.getNombreFiesta().equals(nombreFiesta)) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@GET
	@Path(value = "Registrar")
	public String registrarFiesta(@QueryParam("nombre") String nombre, @QueryParam("email") String email,
			@QueryParam("idDiscoteca") int idDiscoteca, @QueryParam("fecha") String fecha,
			@QueryParam("hora") String hora, @QueryParam("descripcion") String descripcion) {
		Fiesta fiesta = new Fiesta(nombre, email, idDiscoteca, fecha, hora, descripcion);
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = con.prepareStatement(
					"insert into FIESTA (IDFIESTA, EMAILUSR, IDDISCOTECA, NOMBREFIESTA, FECHAFIESTA, HORAFIESTA, DESCRIPCIONFIESTA) "
							+ "values (NULL,?,?,?,?,?,?)");
			st.setString(1, fiesta.getEmail());
			st.setInt(2, fiesta.getIdDiscoteca());
			st.setString(3, fiesta.getNombreFiesta());
			st.setString(4, fiesta.getFecha());
			st.setString(5, fiesta.getHora());
			st.setString(6, fiesta.getDescripcion());

			st.execute();
			st.close();
			con.close();
			return "Fiesta registrada";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ha ocurrido un error al registrar fiesta";
		} catch (SQLIntegrityConstraintViolationException e) {
			// Error message for integrity constraint violation
			return "Error ya ha registrado una fiesta con ese nombre";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ha ocurrido un error al registrar fiesta";
		}
	}

	@GET
	@Path(value = "BuscarNombre")
	public Fiesta buscarFiesta(@QueryParam("nombre") String nombreFiesta, @QueryParam("email") String emailUsuario) {
		Fiesta fiesta = new Fiesta();
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			if ((nombreFiesta.equals("")) && (emailUsuario.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA");
			} else if ((nombreFiesta.equals("")) && (!emailUsuario.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA where EMAILUSR = ?");
				st.setString(1, "%" + emailUsuario + "%");
			} else if ((!nombreFiesta.equals("")) && (!emailUsuario.equals(""))) {
				st = con.prepareStatement("Select * from FIESTA where EMAILUSR = ? and NOMBREFIESTA = ?");
				st.setString(1, emailUsuario);
				st.setString(2, nombreFiesta);
			}
			st.execute();

			ResultSet rs = st.getResultSet();
			while (rs.next()) {
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

	@GET
	@Path(value = "Buscar/{idFiesta}")
	public Fiesta buscarFiesta(@PathParam("idFiesta") int idFiesta) {
		Fiesta fiesta = new Fiesta();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			st = con.prepareStatement("Select * from FIESTA where IDFIESTA = ?");
			st.setInt(1, idFiesta);
			st.execute();

			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				fiesta.setIdFiesta(rs.getInt("IDFIESTA"));
				fiesta.setNombreFiesta(rs.getString("NOMBREFIESTA"));
				fiesta.setIdDiscoteca(rs.getShort("IDDISCOTECA"));
				fiesta.setNombreDiscoteca(sd.BuscarDiscoteca(fiesta.getIdDiscoteca()).getNombre());
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

	@GET
	@Path(value = "Listar")
	public List<Fiesta> listarFiesta(@QueryParam("nombreFiesta") String nombre,
			@QueryParam("idDiscoteca") int idDiscoteca, @QueryParam("email") String email,
			@QueryParam("admin") boolean isAdmin) {
		if (nombre == null || nombre.equals("null"))
			nombre = "";
		if (email == null || email.equals("null"))
			email = "";
		Fiesta fiesta = new Fiesta(nombre, email, idDiscoteca, "", "", "");
		List<Fiesta> listaFiesta = new ArrayList<Fiesta>();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		PreparedStatement st = null;
		try {
			java.sql.Connection con = establecerConexion();
			if (isAdmin == true) {
				st = con.prepareStatement(
						"SELECT * FROM FIESTA WHERE IDDISCOTECA=? AND NOMBREFIESTA like ? ORDER BY NOMBREFIESTA");
				st.setInt(1, fiesta.getIdDiscoteca());
				st.setString(2, "%" + fiesta.getNombreFiesta() + "%");
			} else if (fiesta.getIdDiscoteca() != 0 && (!fiesta.getEmail().equals(""))
					&& (fiesta.getNombreFiesta().equals(""))) {
				st = con.prepareStatement(
						"SELECT * FROM FIESTA WHERE IDDISCOTECA=? AND NOMBREFIESTA like ? and EMAILUSR=? ORDER BY NOMBREFIESTA");
				st.setInt(1, fiesta.getIdDiscoteca());
				st.setString(2, "%" + fiesta.getNombreFiesta() + "%");
				st.setString(3, fiesta.getEmail());
			} else {
				st = con.prepareStatement("SELECT * FROM FIESTA WHERE IDFIESTA=0");
			}
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Fiesta f = new Fiesta();
				f.setIdFiesta(rs.getInt("IDFIESTA"));
				f.setNombreFiesta(rs.getString("NOMBREFIESTA"));
				f.setIdDiscoteca(rs.getInt("IDDISCOTECA"));
				f.setEmail(rs.getString("EMAILUSR"));
				f.setFecha(rs.getString("FECHAFIESTA"));
				f.setHora(rs.getString("HORAFIESTA"));
				f.setDescripcion(rs.getString("DESCRIPCIONFIESTA"));
				f.setNombreDiscoteca(sd.BuscarDiscoteca(f.getIdDiscoteca()).getNombre());
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

	@GET
	@Path(value = "Modificar")
	public String modificarFiesta(@QueryParam("idFiesta") int idFiesta, @QueryParam("nombre") String nombre,
			@QueryParam("idDiscoteca") int idDiscoteca, @QueryParam("fecha") String fecha, 
			@QueryParam("hora") String hora, @QueryParam("descripcion") String descripcion) {
		Fiesta fiestaModificador = new Fiesta(idFiesta, "", idDiscoteca, nombre, fecha, hora, descripcion);

		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = con.prepareStatement("Update FIESTA set NOMBREFIESTA=?, "
					+ "IDDISCOTECA = ?, FECHAFIESTA=?, HORAFIESTA=?, DESCRIPCIONFIESTA=?  where IDFIESTA=?");
			st.setString(1, fiestaModificador.getNombreFiesta());
			st.setInt(2, fiestaModificador.getIdDiscoteca());
			st.setString(3, fiestaModificador.getFecha());
			st.setString(4, fiestaModificador.getHora());
			st.setString(5, fiestaModificador.getDescripcion());
			st.setInt(6, fiestaModificador.getIdFiesta());
			st.execute();
			st.close();
			con.close();
			return "Fiesta modificada";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ha ocurrido un error al modificar fiesta";
		} catch (SQLIntegrityConstraintViolationException e) {
			// Error message for integrity constraint violation
			return "Error ya ha registrado una fiesta con ese nombre";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ha ocurrido un error al modificar fiesta";
		}
	}

	@GET
	@Path(value = "Eliminar/{idFiesta}")
	public String eliminarFiesta(@PathParam("idFiesta") int idFiesta) {
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = con.prepareStatement("Delete from FIESTA where IDFIESTA=?");
			st.setInt(1, idFiesta);
			st.execute();
			st.close();
			con.close();
			return "Fiesta eliminada";
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ha ocurrido un error al eliminar fiesta";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Ha ocurrido un error al eliminar fiesta";
		}
	}

	@GET
	@Path(value = "ListarPorDiscoteca/{idDiscoteca}")
	public List<Fiesta> buscarFiestaByDisco(@PathParam("idDiscoteca") int idDiscoteca) {
		ServiceDiscoteca sd = new ServiceDiscoteca();
		List<Fiesta> listaFiestas = new ArrayList<Fiesta>();
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			st = con.prepareStatement("Select * from FIESTA where IDDISCOTECA=?");
			st.setInt(1, idDiscoteca);
			st.execute();

			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Fiesta fiesta = new Fiesta();
				fiesta.setIdFiesta(rs.getInt("IDFIESTA"));
				fiesta.setNombreFiesta(rs.getString("NOMBREFIESTA"));
				fiesta.setIdDiscoteca(rs.getShort("IDDISCOTECA"));
				fiesta.setEmail(rs.getString("EMAILUSR"));
				fiesta.setFecha(rs.getDate("FECHAFIESTA").toString());
				fiesta.setHora(rs.getTime("HORAFIESTA").toString());
				fiesta.setDescripcion(rs.getString("DESCRIPCIONFIESTA"));
				listaFiestas.add(fiesta);
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

		return listaFiestas;
	}
}