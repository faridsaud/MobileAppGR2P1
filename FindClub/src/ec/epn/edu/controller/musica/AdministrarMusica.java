package ec.epn.edu.controller.musica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class AdministrarMusica
 */
@WebServlet("/Musica/Administrar")
public class AdministrarMusica extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministrarMusica() {
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
		ServiceMusica sm = new ServiceMusica();
		String tipoMusica = "";
		List<Musica> listaMusica=new ArrayList<Musica>();
		try {
			Usuario usr = (Usuario) request.getSession().getAttribute("usuarioActivo");
			tipoMusica = (String) request.getParameter("tipoMusica");
			if ((usr == null) || (usr.isAdmin() == false)) {
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/home.jsp").forward(request,
						response);
			} else {
				if (tipoMusica == null)
					tipoMusica = "";
				if(tipoMusica==""){
					listaMusica=sm.listarMusica();
				}else{
					listaMusica=sm.listarMusicaByTipo(tipoMusica);
				}
				request.setAttribute("listaMusica", listaMusica);
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/administrar.jsp").forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
