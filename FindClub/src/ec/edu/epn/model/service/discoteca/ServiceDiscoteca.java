package ec.edu.epn.model.service.discoteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import ec.edu.epn.model.vo.Discoteca;
@Path(value="ServiceDiscoteca")
@Produces("Application/json")
@Consumes("Application/json")
public class ServiceDiscoteca{
    private Connection conexionMYSQL ()throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.
				getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
        return con;
    }
    @GET
	@Path(value="Ingresar/{nombreDisco}/{emailUsr}/{descripcion}/{pathImagen}")
    public String IngresarDiscoteca(@PathParam("nombreDisco")String nombreDisco, 
    		@PathParam("emailUsr")String emailUsr, @PathParam("descripcion")String descripcion, 
    		@PathParam("pathImagen")String pathImagen) 
    				throws ClassNotFoundException, SQLException{
    	try{
    		Connection con = conexionMYSQL();
    		PreparedStatement st = con.prepareStatement("INSERT INTO DISCOTECA (IDDISCOTECA, NOMBREDISCOTECA, EMAILUSR, "
    				+ "DESCRIPCIONDISCOTECA, PATHIMAGENDISCOTECA) VALUES (NULL,?,?,?,?);");
    		st.setString(1, nombreDisco);
    		st.setString(2, emailUsr);
    		st.setString(3, descripcion);
    		st.setString(4, pathImagen);
    		st.execute();
			st.close();
			con.close();
    	}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error class";
		}catch (SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			return "error constraint";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error sql";
		}
    	return "Discoteca Ingresada con exito";
    }
    @GET
	@Path(value="Modificar/{nombreDisco}/{emailUsr}/{descripcion}/{pathImagen}/{idDisco}")
    public String ModificarDiscoteca(@PathParam("nombreDisco")String nombreDisco, 
    		@PathParam("emailUsr")String emailUsr, @PathParam("descripcion")String descripcion, 
    		@PathParam("pathImagen")String pathImagen, @PathParam("idDisco")int idDisco){
    	try{
    		Connection con = conexionMYSQL();
    		PreparedStatement st = con.prepareStatement("UPDATE DISCOTECA SET NOMBREDISCOTECA=?, EMAILUSR=?,"
    				+ "DESCRIPCIONDISCOTECA=?, PATHIMAGENDISCOTECA=? WHERE IDDISCOTECA = ?;");
    		st.setString(1, nombreDisco);
    		st.setString(2, emailUsr);
    		st.setString(3, descripcion);
    		st.setString(4, pathImagen);
    		st.setInt(5, idDisco);
    		st.execute();
    		st.close();
			con.close();
    	}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error class";
		} catch (SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			return "error constraint";
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error sql";
		}
    	return "Modificado con éxito";
    }
    @GET
	@Path(value="Buscar/{idDisco}")
    public Discoteca BuscarDiscoteca(@PathParam("idDisco")int idDisco) {
		Discoteca disco = new Discoteca();
		try {
			Connection con = conexionMYSQL();
			PreparedStatement st = con.prepareStatement("SELECT * FROM DISCOTECA WHERE IDDISCOTECA=?;");
			st.setInt(1, idDisco);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setIdDiscoteca(rs.getInt("IDDISCOTECA"));
				disco.setNombre(rs.getString("NOMBREDISCOTECA"));
				disco.setEmailUsr(rs.getString("EMAILUSR"));
				disco.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
				disco.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error Buscando CLASS");
		} catch (SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			System.out.println("error constraint");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error Buscando SQL");
		}
		return disco;
	}
    @GET
    @Path(value="Listar")
    public List<Discoteca> ListarDiscoteca(){
    	List<Discoteca> listaDiscotecas = new ArrayList<Discoteca>();
		try {
			Connection con = conexionMYSQL();
			PreparedStatement st = con.prepareStatement("SELECT * FROM DISCOTECA ORDER BY NOMBREDISCOTECA;");
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Discoteca disco1 = new Discoteca();
				disco1.setIdDiscoteca(rs.getInt(1));
				disco1.setNombre(rs.getString(2));
				disco1.setEmailUsr(rs.getString(3));
				disco1.setDescripcion(rs.getString(4));
				disco1.setImagen(rs.getString(5));
				listaDiscotecas.add(disco1);
			}
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error listando CLASS");
		} catch (SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			System.out.println("error constraint");
		}catch (SQLException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
			System.out.println("error listando SQL");
		} 
		return listaDiscotecas;
    }
    @GET
    @Path(value="Eliminar/{idDiscoteca}")
    public void EliminarDiscoteca(@PathParam("idDiscoteca")int idDiscoteca) {
		try {
			Connection con = conexionMYSQL();
			PreparedStatement st = con.prepareStatement("DELETE FROM DISCOTECA WHERE IDDISCOTECA=?;");
			st.setInt(1, idDiscoteca);
			st.execute();
			st.close();
			con.close();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error eliminando CLASS");
		}catch (SQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			System.out.println("error constraint");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error eliminando SQL");
		}
	}
}
