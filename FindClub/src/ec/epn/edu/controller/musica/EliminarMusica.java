package ec.epn.edu.controller.musica;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class EliminarMusica
 */
@WebServlet("/Musica/Eliminar")
public class EliminarMusica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarMusica() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr = (Usuario) request.getSession().getAttribute("usuarioActivo");
		if (usr == null || usr.isAdmin() == false) {
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/home.jsp").forward(request,
					response);
		} else {
			Musica msc = new Musica();
			String tipoMusica = request.getParameter("tipoMusicaEliminar");
			ServiceMusica sm = new ServiceMusica();
			if (tipoMusica != null && tipoMusica.equals("") == false) {
				msc = sm.buscarMusica(tipoMusica);
				if (msc != null) {
					sm.eliminarMusica(msc);
					getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/home.jsp")
							.forward(request, response);
				}
			}
		}
	}

}
