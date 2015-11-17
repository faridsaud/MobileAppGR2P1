package ec.edu.epn.model.service.discoteca;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;
public class ServiceDiscoteca {
	
	public Discoteca buscarDiscoteca(String nombreDisco){
		Discoteca disco = new Discoteca();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from DISCOTECA where NOMBREDISCOTECA LIKE ?");
			st.setString(1, nombreDisco);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				
				disco.setNombre(rs.getString("NOMBREDISCOTECA"));
				disco.setCiudad(rs.getInt("IDCIUDAD"));
				disco.setEmailUsr(rs.getString("EMAILUSR"));
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
	public int identificadorCiudad(String nombreCiudad){
		ServiceCiudad sc = new ServiceCiudad();
		Ciudad c = new Ciudad();
		c=sc.buscarCiudad(c, nombreCiudad);
		return c.getIdCiudad();
	}
	public void registrarDiscoteca(Discoteca disco) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"Insert into DISCOTECA (NOMBREDISCOTECA,NOMBRETIPOMUSICA, IDCIUDAD, EMAILUSR, DESCRIPCIONDISCOTECA, PATHIMAGENDISCOTECA) values (?,?,?,?,?,?) ");
			st.setString(1, disco.getNombre());
			st.setString(2, disco.getTipoMusica());
			st.setInt(3, disco.getCiudad());
			st.setString(4, disco.getEmailUsr());
			st.setString(5, disco.getDescripcion());
			st.setString(6, disco.getImagen());
			st.execute();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error");
		}
	}
	public List<Discoteca> listarDiscoteca(Discoteca disco) {
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
				disco1.setCiudad(rs.getInt("IDCIUDAD"));
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
					"UPDATE DISCOTECA SET NOMBREDISCOTECA=?,NOMBRETIPODEMUSICA=?, IDCIUDAD=?, EMAILUSER=?, DESCRIPCIONDISCOTECA=?, PATHIMAGENDISCOTECA=? WHERE NOMBREDISCOTECA=?");
			st.setString(1, discoModificador.getNombre());
			st.setString(2, discoModificador.getTipoMusica());
			st.setInt(3, discoModificador.getCiudad());
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
	public Discoteca buscarDiscotecaByCiudad(int ciudad){
		Discoteca disco = new Discoteca();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from DISCOTECA where IDCIUDAD=?");
			st.setInt(1, ciudad);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setNombre(rs.getString("NOMBREUSR"));
				disco.setCiudad(rs.getInt("IDCIUDAD"));
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
				disco.setCiudad(rs.getInt("IDCIUDAD"));
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
