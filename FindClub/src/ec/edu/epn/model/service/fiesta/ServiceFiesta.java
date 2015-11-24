package ec.edu.epn.model.service.fiesta;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.vo.Discoteca;
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

	public void registrarFiesta(Fiesta fiesta, String nombreCiudad, String nombrePais) {
		if (existeFiesta(fiesta.getNombreFiesta(), fiesta.getEmail()) == false) {
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = con.prepareStatement(
						"insert into FIESTA (IDFIESTA, EMAILUSR, IDDISCOTECA, NOMBREFIESTA, FECHAFIESTA, HORAFIESTA, DESCRIPCIONFIESTA) "
								+ "values (NULL,?, (select d.IDDISCOTECA from DISCOTECA d where d.NOMBREDISCOTECA=? AND d.IDCIUDAD = (select IDCIUDAD from CIUDAD c where c.IDPAIS = (select IDPAIS from PAIS where NOMBREPAIS=?) and c.NOMBRECIUDAD=?)),?,?,?,?)");
				st.setString(1, fiesta.getEmail());
				st.setString(2, fiesta.getNombreDiscoteca());
				st.setString(3, nombrePais);
				st.setString(4, nombreCiudad);
				st.setString(5, fiesta.getNombreFiesta());
				st.setString(6, fiesta.getFecha());
				st.setString(7, fiesta.getHora());
				st.setString(8, fiesta.getDescripcion());

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

	public Fiesta buscarFiesta(Fiesta fiesta, String nombreFiesta, String emailUsuario) {
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

	public Fiesta buscarFiesta(Fiesta fiesta, int idFiesta) {
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
				fiesta.setNombreDiscoteca(sd.buscarDiscoteca(fiesta.getIdDiscoteca()).getNombre());
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

	public List<Fiesta> listarFiesta(Fiesta fiesta, Usuario usr) {
		List<Fiesta> listaFiesta = new ArrayList<Fiesta>();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		PreparedStatement st = null;
		try {
			java.sql.Connection con = establecerConexion();
			if (usr.isAdmin() == true && (fiesta.getNombreFiesta().equals(""))) {
				st = con.prepareStatement("SELECT * FROM FIESTA WHERE IDDISCOTECA=? ORDER BY NOMBREFIESTA");
				st.setInt(1, fiesta.getIdDiscoteca());
			} else if (usr.isAdmin() == true && (fiesta.getNombreFiesta().equals(""))) {
				st = con.prepareStatement(
						"SELECT * FROM FIESTA WHERE IDDISCOTECA=? NOMBREFIESTA like ? ORDER BY NOMBREFIESTA");
				st.setInt(1, fiesta.getIdDiscoteca());
				st.setString(2, "%" + fiesta.getNombreFiesta() + "%");
			} else if (fiesta.getIdDiscoteca() != 0 && (!usr.getEmail().equals(""))
					&& (fiesta.getNombreFiesta().equals(""))) {
				st = con.prepareStatement(
						"SELECT * FROM FIESTA WHERE IDDISCOTECA=? and EMAILUSR=? ORDER BY NOMBREFIESTA");
				st.setInt(1, fiesta.getIdDiscoteca());
				st.setString(2, usr.getEmail());
			} else if (fiesta.getIdDiscoteca() != 0 && (!usr.getEmail().equals(""))
					&& (!fiesta.getNombreFiesta().equals(""))) {
				st = con.prepareStatement(
						"SELECT * FROM FIESTA WHERE IDDISCOTECA=? and EMAILUSR=? and NOMBREFIESTA like ? ORDER BY NOMBREFIESTA");
				st.setInt(1, fiesta.getIdDiscoteca());
				st.setString(2, usr.getEmail());
				st.setString(3, "%" + fiesta.getNombreFiesta() + "%");
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

	public void modificarFiesta(Fiesta fiestaModificar, Fiesta fiestaModificador, String nombreCiudad, String nombrePais) {
		Fiesta fiesta = new Fiesta();
		if (existeFiesta(fiestaModificador.getNombreFiesta(), fiestaModificador.getEmail()) == false) {
			try {
				java.sql.Connection con = establecerConexion();
				PreparedStatement st = con.prepareStatement("Update FIESTA set NOMBREFIESTA=?, "
						+ "IDDISCOTECA= (select d.IDDISCOTECA from DISCOTECA d where d.NOMBREDISCOTECA=? AND d.IDCIUDAD = (select IDCIUDAD from CIUDAD c where c.IDPAIS = (select IDPAIS from PAIS where NOMBREPAIS=?) and c.NOMBRECIUDAD=?)), "
						+ "FECHAFIESTA=?, HORAFIESTA=?, DESCRIPCIONFIESTA=?  where IDFIESTA=?");
				st.setString(1, fiestaModificador.getNombreFiesta());
				st.setString(2, fiestaModificador.getNombreDiscoteca());
				st.setString(3, nombrePais);
				st.setString(4, nombreCiudad);
				st.setString(5, fiestaModificador.getFecha());
				st.setString(6, fiestaModificador.getHora());
				st.setString(7, fiestaModificador.getDescripcion());
				st.setInt(8, fiestaModificar.getIdFiesta());
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

	public void eliminarFiesta(Fiesta fiesta) {
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = con.prepareStatement("Delete from FIESTA where IDFIESTA=?");
			st.setInt(1, fiesta.getIdFiesta());
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
	public List<Fiesta> buscarFiestaByDisco(Discoteca disc) {
		ServiceDiscoteca sd = new ServiceDiscoteca();
		List<Fiesta> listaFiestas= new ArrayList<Fiesta>();
		try {
			java.sql.Connection con = establecerConexion();
			PreparedStatement st = null;
			st = con.prepareStatement("Select * from FIESTA where IDDISCOTECA=?");
			st.setInt(1, disc.getIdDiscoteca());
			st.execute();

			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				Fiesta fiesta= new Fiesta();
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