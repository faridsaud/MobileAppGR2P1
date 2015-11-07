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
 * Servlet implementation class RegistrarMusica
 */
@WebServlet("/Musica/Registrar")
public class RegistrarMusica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarMusica() {
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
		Usuario usr = new Usuario();
		usr = (Usuario) request.getSession().getAttribute("usuarioActivo");
		if ((usr == null) || (usr.isAdmin() == false)) {
			getServletConfig().getServletContext().getRequestDispatcher("/Musica/Home").forward(request, response);
		} else {

			getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/registrar.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Musica msc = new Musica();
		Usuario usr = new Usuario();
		usr = (Usuario) request.getSession().getAttribute("usuarioActivo");
		if ((usr == null) || (usr.isAdmin() == false)) {
			getServletConfig().getServletContext().getRequestDispatcher("/Musica/Home").forward(request, response);
		} else {
			try {
				msc.setNombreTipo(request.getParameter("tipoMusica"));
				msc.setDescripcion(request.getParameter("descripcion"));
				ServiceMusica sm = new ServiceMusica();
				sm.registrarMusica(msc);
				getServletConfig().getServletContext().getRequestDispatcher("/Musica/Home").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
