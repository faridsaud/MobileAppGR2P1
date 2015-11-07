package ec.edu.epn.model.service.musica;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Usuario;

public class ServiceMusica {

	public void registrarMusica(Musica msc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con
					.prepareStatement("Insert into MUSICA (NOMBRETIPOMUSICA,DESCRIPCIONMUSICA) values (?,?) ");
			st.setString(1, msc.getNombreTipo());
			st.setString(2, msc.getDescripcion());
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

	public void eliminarMusica(Musica msc) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement("delete from MUSICA where NOMBRETIPOMUSICA=?");
			st.setString(1, msc.getNombreTipo());
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

	public Musica buscarMusica(String tipoMusica) {
		Musica msc = new Musica();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement("Select * from MUSICA where NOMBRETIPOMUSICA=? ");
			st.setString(1, tipoMusica);
			st.execute();
			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				msc.setNombreTipo(rs.getString("NOMBRETIPOMUSICA"));
				msc.setDescripcion(rs.getString("DESCRIPCIONMUSICA"));
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
		return msc;
	}
	
	public List<Musica> listarMusica(){
		List<Musica> listaMusica=new ArrayList<Musica>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = null;
			st = con.prepareStatement("Select * from MUSICA");
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Musica msc = new Musica();
				msc.setNombreTipo(rs.getString("NOMBRETIPOMUSICA"));
				msc.setDescripcion(rs.getString("DESCRIPCIONMUSICA"));
				listaMusica.add(msc);
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
		return listaMusica;
	}
	
	public List<Musica> listarMusicaByTipo(String tipoMusica){
		List<Musica> listaMusica=new ArrayList<Musica>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = null;
			st = con.prepareStatement("Select * from MUSICA where NOMBRETIPOMUSICA LIKE ?");
			st.setString(1, "%"+tipoMusica+"%");
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Musica msc = new Musica();
				msc.setNombreTipo(rs.getString("NOMBRETIPOMUSICA"));
				msc.setDescripcion(rs.getString("DESCRIPCIONMUSICA"));
				listaMusica.add(msc);
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
		return listaMusica;
	}
	public void modificarMusica(Musica mscModificar, Musica mscModificador) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"UPDATE MUSICA SET NOMBRETIPOMUSICA=?,DESCRIPCIONMUSICA=? WHERE NOMBRETIPOMUSICA=?");
			st.setString(1, mscModificador.getNombreTipo());
			st.setString(2, mscModificador.getDescripcion());
			st.setString(3, mscModificar.getNombreTipo());
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
