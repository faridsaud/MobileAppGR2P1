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
import ec.edu.epn.model.vo.Usuario;

public class ServiceDiscoteca {

	public Discoteca buscarDiscoteca(String nombreDisco) {
		Discoteca disco = new Discoteca();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con.prepareStatement("Select * from DISCOTECA where NOMBREDISCOTECA LIKE ?");
			st.setString(1, nombreDisco);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {

				disco.setNombre(rs.getString("NOMBREDISCOTECA"));
				disco.setCiudad(rs.getInt("IDCIUDAD"));
				disco.setIdDiscoteca(rs.getInt("IDDISCOTECA"));
				disco.setEmailUsr(rs.getString("EMAILUSR"));
				disco.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
				disco.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
				disco.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
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

	public Discoteca buscarDiscoteca(int idDisco) {
		Discoteca disco = new Discoteca();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con.prepareStatement("Select * from DISCOTECA where IDDISCOTECA=?");
			st.setInt(1, idDisco);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setNombre(rs.getString("NOMBREDISCOTECA"));
				disco.setCiudad(rs.getInt("IDCIUDAD"));
				disco.setEmailUsr(rs.getString("EMAILUSR"));
				disco.setIdDiscoteca(rs.getInt("IDDISCOTECA"));
				disco.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
				disco.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
				disco.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
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

	public void registrarDiscoteca(Discoteca disco, String nombrePais, String nombreCiudad) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con.prepareStatement("Insert into DISCOTECA (NOMBREDISCOTECA, NOMBRETIPOMUSICA,"
					+ "PATHIMAGENDISCOTECA, EMAILUSR, DESCRIPCIONDISCOTECA, IDCIUDAD) values (?,?,?,?,?,"
					+ "(select IDCIUDAD from CIUDAD c where c.IDPAIS = (select IDPAIS from PAIS where NOMBREPAIS=?) and c.NOMBRECIUDAD=?))");
			st.setString(1, disco.getNombre());
			st.setString(2, disco.getTipoMusica());
			st.setString(3, disco.getImagen());
			st.setString(4, disco.getEmailUsr());
			st.setString(5, disco.getDescripcion());
			st.setString(6, nombrePais);
			st.setString(7, nombreCiudad);

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

	public List<Discoteca> listarDiscotecaByNombre(String nombreCiudad, String nombrePais) {

		List<Discoteca> listaDiscotecas = new ArrayList<Discoteca>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");

			PreparedStatement st = null;

			st = con.prepareStatement("SELECT d.IDDISCOTECA, d.NOMBREDISCOTECA, d.IDCIUDAD, d.NOMBRETIPOMUSICA, d.EMAILUSR, d.DESCRIPCIONDISCOTECA, d.PATHIMAGENDISCOTECA "
					+ "FROM DISCOTECA d, CIUDAD c "
					+ "WHERE d.IDCIUDAD = c.IDCIUDAD AND c.NOMBRECIUDAD=? AND c.IDPAIS = (SELECT IDPAIS FROM PAIS WHERE NOMBREPAIS=?);");
			st.setString(1, nombreCiudad);
			st.setString(2, nombrePais);
			
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Discoteca disco1 = new Discoteca();
				disco1.setIdDiscoteca(rs.getInt(1));
				disco1.setNombre(rs.getString(2));
				disco1.setCiudad(rs.getInt(3));
				disco1.setTipoMusica(rs.getString(4));
				disco1.setEmailUsr(rs.getString(5));
				disco1.setDescripcion(rs.getString(6));
				disco1.setImagen(rs.getString(7));
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

//	public List<Discoteca> listarDiscoteca(Discoteca disco, String nombre, nombreCiudad) {
//
//		List<Discoteca> listaDiscotecas = new ArrayList<Discoteca>();
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//
//			java.sql.Connection con = DriverManager.getConnection
//
//			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
//
//			PreparedStatement st = null;
//
//			st = con.prepareStatement(
//					"Select NOMBREDISCOTECA,d.IDCIUDAD,NOMBRETIPOMUSICA,PATHIMAGENDISCOTECA,EMAILUSR,DESCRIPCIONDISCOTECA from DISCOTECA d, CIUDAD c where d.IDCIUDAD = c.IDCIUDAD and c.NOMBRECIUDAD=?");
//			st.setString(1, nombreCiudad);
//
//			if (nombre.equals("") && disco.getCiudad() == 0) {
//				st = con.prepareStatement("Select * from DISCOTECA");
//			} else if (disco.getCiudad() != 0) {
//				st = con.prepareStatement("Select * from DISCOTECA where IDCIUDAD = ?");
//				st.setInt(1, disco.getCiudad());
//			} else if (nombre.equals("") == false) {
//				st = con.prepareStatement("Select * from DISCOTECA where NOMBREDISCOTECA like ?");
//				st.setString(1, "%" + nombre + "%");
//			}
//
//			st.execute();
//			ResultSet rs = st.getResultSet();
//
//			while (rs.next()) {
//				Discoteca disco1 = new Discoteca();
//				disco1.setNombre(rs.getString(1));
//				disco1.setCiudad(rs.getInt(2));
//				disco1.setTipoMusica(rs.getString(3));
//				disco1.setImagen(rs.getString(4));
//				disco1.setEmailUsr(rs.getString(5));
//				disco1.setDescripcion(rs.getString(6));
//				disco1.setNombre(rs.getString("NOMBREDISCOTECA"));
//				disco1.setCiudad(rs.getInt("IDCIUDAD"));
//				disco1.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
//				disco1.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
//				disco1.setEmailUsr(rs.getString("EMAILUSR"));
//
//				disco1.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
//				listaDiscotecas.add(disco1);
//			}
//			st.close();
//			con.close();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return listaDiscotecas;
//	}

	public void modificarDiscoteca(Discoteca discoModificar, Discoteca discoModificador) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"UPDATE DISCOTECA SET NOMBREDISCOTECA=?,NOMBRETIPODEMUSICA=?, IDCIUDAD=?, EMAILUSR=?,"
							+ "DESCRIPCIONDISCOTECA=?, PATHIMAGENDISCOTECA=? WHERE IDDISCOTECA=?");
			st.setString(1, discoModificador.getNombre());
			st.setString(2, discoModificador.getTipoMusica());
			st.setInt(3, discoModificador.getCiudad());
			st.setString(4, discoModificador.getEmailUsr());
			st.setString(5, discoModificador.getDescripcion());
			st.setString(6, discoModificador.getImagen());
			st.setInt(7, discoModificar.getIdDiscoteca());
			st.execute();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("error 1");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error 1");
		}

	}

	/* ToDo revisar */
	public Discoteca buscarDiscotecaByCiudad(String ciudad, String pais) {

		Discoteca disco = new Discoteca();
		try {
			ServiceCiudad sc = new ServiceCiudad();
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con.prepareStatement("Select * from DISCOTECA where IDCIUDAD=?");
			st.setInt(1, sc.buscarCiudad(ciudad, pais).getIdCiudad());
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setNombre(rs.getString("NOMBREDISCOTECA"));
				disco.setCiudad(rs.getInt("IDCIUDAD"));
				disco.setDescripcion(rs.getString("DESCRIPCIONDISCOTECA"));
				disco.setEmailUsr(rs.getString("EMAILUSR"));
				disco.setImagen(rs.getString("PATHIMAGENDISCOTECA"));
				disco.setTipoMusica(rs.getString("NOMBRETIPOMUSICA"));
				disco.setIdDiscoteca(rs.getInt("IDDISCOTECA"));

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

	/* Todo revisar */
	public Discoteca buscarDiscotecaByNombre(String discoteca, int ciudad) {
		Discoteca disco = new Discoteca();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Select * from DISCOTECA where NOMBREDISCOTECA=? and IDCIUDAD=?");
			st.setString(1, discoteca);
			st.setInt(2, ciudad);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				disco.setIdDiscoteca(rs.getInt("IDDISCOTECA"));
				disco.setNombre(rs.getString("NOMBREDISCOTECA"));
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

	public Discoteca buscarDiscotecaByMusica(String musica) {
		Discoteca disco = new Discoteca();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con.prepareStatement("Select * from DISCOTECA where NOMBRETIPOMUSICA=?");
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
				disco.setIdDiscoteca(rs.getInt("IDDISCOTECA"));
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

			java.sql.Connection con = DriverManager.getConnection

			("jdbc:mysql://192.168.216.131:3306/movilDBPrueba", "bases", "bases");
			PreparedStatement st = con.prepareStatement("delete from DISCOTECA where IDDISCOTECA=?");
			st.setInt(1, disco.getIdDiscoteca());
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
