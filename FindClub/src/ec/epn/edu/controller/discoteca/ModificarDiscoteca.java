package ec.epn.edu.controller.discoteca;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class ModificarDiscoteca
 */
@WebServlet("/Discoteca/Modificar")
public class ModificarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarDiscoteca() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,

			IOException {
		// TODO Auto-generated method stub

		Discoteca disco = new Discoteca();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		disco = sd.buscarDiscoteca((String) request.getParameter("nombreModificar"));
		request.getSession().setAttribute("discotecaModificar", disco);
		try {
			ServicePais sp = new ServicePais();
			ServiceCiudad sc = new ServiceCiudad();
			ServiceMusica sm = new ServiceMusica();
			Ciudad ciudad = new Ciudad();
			Pais pais = new Pais();
			Musica m = new Musica();
			String nombreCiudad = request.getParameter("ciudad");
			if (nombreCiudad == null)
				nombreCiudad = "";
			ciudad.setNombreCiudad(nombreCiudad);

			String nombrePais = request.getParameter("pais");
			if (nombrePais == null)
				nombrePais = "";
			pais.setNombrePais("");
			ciudad.setNombrePais(nombrePais);

			List<Pais> listaPais = sp.listarPais(pais);
			request.setAttribute("listaPais", listaPais);

			List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
			request.setAttribute("listaCiudad", listaCiudad);
			request.setAttribute("listaPais", listaPais);

			m.setNombreTipo("");
			List<Musica> listaMusica = sm.listarMusica();
			request.setAttribute("listaMusica", listaMusica);

		} catch (Exception e) {
			System.out.println("error publicacion discoteca modificar");
		}
		getServletConfig().getServletContext().getRequestDispatcher

		("/vistas/discoteca/modificar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,

			IOException {
		// TODO Auto-generated method stub
		String nombre = "";
		String nombreCiudad = "";
		String tipoMusica = "";
		String imagen = "";
		String nombrePais = "";
		String descripcion = "";
		String emailUsr = "";
		Usuario usrIniciado = new Usuario();
		usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
		emailUsr = usrIniciado.getEmail();
		Discoteca discoModificador = new Discoteca();
		Discoteca discoModificar = new Discoteca();
		Ciudad c = new Ciudad();
		ServiceCiudad sc = new ServiceCiudad();
		try {

			nombre = request.getParameter("nombre");
			nombrePais = request.getParameter("pais");
			nombreCiudad = request.getParameter("ciudad");
			tipoMusica = request.getParameter("tipoMusica");
			imagen = request.getParameter("inputFile");
			descripcion = request.getParameter("descripcion");
			c.setNombreCiudad(nombreCiudad);
			int idCiudad = c.getIdCiudad();
			discoModificar = (Discoteca) request.getSession().getAttribute("discotecaModificar");
			discoModificador.setNombre(nombre);
			discoModificador.setEmailUsr(emailUsr);
			discoModificador.setCiudad(idCiudad);
			discoModificador.setTipoMusica(tipoMusica);
			discoModificador.setDescripcion(descripcion);
			discoModificador.setImagen(imagen);
			ServiceDiscoteca sd = new ServiceDiscoteca();
			sd.modificarDiscoteca(discoModificar, discoModificador);

		} catch (Exception e) {
			nombre = "";
			emailUsr = "";
			descripcion = "";
			imagen = "";
			nombrePais = "";
			nombreCiudad = "";
			tipoMusica = "";
			doGet(request, response);
			System.out.println("Error modificacion");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);

	}

}
