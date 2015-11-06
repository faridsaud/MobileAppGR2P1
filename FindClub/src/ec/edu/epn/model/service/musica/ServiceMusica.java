package ec.edu.epn.model.service.musica;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ec.edu.epn.model.vo.Musica;

public class ServiceMusica {

	public void registrarMusica(Musica msc){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://192.168.216.131:3306/movilDBPrueba",
					"bases", "bases");
			PreparedStatement st = con.prepareStatement(
					"Insert into MUSICA (NOMBRETIPOMUSICA,DESCRIPCIONMUSICA) values (?,?) ");
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
}
