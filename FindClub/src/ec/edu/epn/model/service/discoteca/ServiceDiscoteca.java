package ec.edu.epn.model.service.discoteca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.tag.common.sql.DriverManagerAccessor;

import com.sun.org.apache.bcel.internal.classfile.ClassFormatException;

import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Usuario;
public class ServiceDiscoteca {
	
	public Discoteca buscarDiscoteca(String nombreDisco){
		Discoteca disco = new Discoteca();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from DISCOTECA where NOMBREDISCOTECA=?");
			st.setString(1, nombreDisco);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setNombre(rs.getString("NOMBREDISCOTECA"));
				disco.setCiudad(rs.getString("NOMBRECIUDAD"));
				disco.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
				disco.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
				disco.setDescripcion(rs.getString("DESCRIPCION"));
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
		return disco;
	}
	public void registrarDiscoteca(Discoteca disco) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"Insert into DISCOTECA (NOMBREDISCOTECA,NOMBRETIPOMUSICA, NOMBRECIUDAD, EMAILUSR, DESCRIPCIONDISCOTECA, PATHIMAGENDISCOTECA) values (?,?,?,?,?,?) ");
			st.setString(1, disco.getNombre());
			st.setString(2, disco.getTipoMusica());
			st.setString(3, disco.getCiudad());
			st.setString(4, disco.getEmailUsr());
			st.setString(5, disco.getDescripcion());
			st.setString(6, disco.getImagen());
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
	public List<Discoteca> listarDiscoteca(String nombre, Discoteca disco) {
		List<Discoteca> listaDiscotecas = new ArrayList<Discoteca>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement("Select * from DISCOTECA ");
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Discoteca disco1 = new Discoteca();
				disco1.setNombre(rs.getString("NOMBREDISCOTECA"));
				disco1.setCiudad(rs.getString("NOMBRECIUDAD"));
				disco1.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
				disco1.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
				disco1.setEmailUsr(rs.getString("EMAILUSR"));
				disco1.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
				listaDiscotecas.add(disco1);
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
		return listaDiscotecas;
	}
	public void modificarDiscoteca(Discoteca discoModificar, Discoteca discoModificador) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"UPDATE DISCOTECA SET NOMBREDISCOTECA=?,NOMBRETIPODEMUSICA=?, NOMBRECIUDAD=?, EMAILUSER=?, DESCRIPCIONDISCOTECA=?, PATHIMAGENDISCOTECA=? WHERE NOMBREDISCOTECA=?");
			st.setString(1, discoModificador.getNombre());
			st.setString(2, discoModificador.getTipoMusica());
			st.setString(3, discoModificador.getCiudad());
			st.setString(4, discoModificador.getEmailUsr());
			st.setString(5, discoModificador.getDescripcion());
			st.setString(6, discoModificador.getImagen());
			st.setString(7, discoModificar.getNombre());
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
	public Discoteca buscarDiscotecaByCiudad(String ciudad){
		Discoteca disco = new Discoteca();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from DISCOTECA where NOMBRECIUDAD=?");
			st.setString(1, ciudad);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setNombre(rs.getString("NOMBREUSR"));
				disco.setCiudad(rs.getString("NOMBRECIUDAD"));
				disco.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
				disco.setEmailUsr(rs.getString("EMAILUSR"));
				disco.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
				disco.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
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
		return disco;
	}
	public Discoteca buscarDiscotecaByMusica(String musica){
		Discoteca disco = new Discoteca();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from DISCOTECA where NOMBRETIPOMUSICA=?");
			st.setString(1, musica);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setNombre(rs.getString("NOMBREUSR"));
				disco.setCiudad(rs.getString("NOMBRECIUDAD"));
				disco.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
				disco.setEmailUsr(rs.getString("EMAILUSR"));
				disco.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
				disco.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
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
		return disco;
	}
	
	public void eliminarDiscoteca(Discoteca disco) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("delete from DISCOTECA where NOMBREDISCOTECA=?");
			st.setString(1, disco.getNombre());
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
