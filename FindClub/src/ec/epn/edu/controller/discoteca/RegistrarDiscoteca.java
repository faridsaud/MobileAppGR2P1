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
import ec.edu.epn.model.service.fiesta.ServiceFiesta;
import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Fiesta;
import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class RegistrarDiscoteca
 */
@WebServlet("/Discoteca/Registrar")
public class RegistrarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarDiscoteca() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String combos = request.getParameter("combos");

		Usuario usrIniciado = new Usuario();
		boolean redireccion = true;
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado.isEstado() == true) {
				redireccion = false;
			}
		} catch (Exception e) {
			System.out.println("Error obteniendo usuario");
		}
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
		} else {
			if (combos == null || combos.equals("si")) {
				ServicePais sp = new ServicePais();
				ServiceCiudad sc = new ServiceCiudad();
				ServiceDiscoteca sd = new ServiceDiscoteca();
				ServiceMusica sm = new ServiceMusica();
				
				Pais pais = new Pais();
				Ciudad ciudad = new Ciudad();

				String nombrePais = (String) request.getParameter("pais");
				String nombreCiudad = (String) request.getParameter("ciudad");
				String nombreFiesta = (String) request.getParameter("nombreDiscoteca");
				String descripcion = (String) request.getParameter("descripcion");
				String path = (String) request.getParameter("pathDiscoteca");

				if (nombreFiesta == null)
					nombreFiesta = "";
				if (descripcion == null)
					descripcion = "";
				if (path == null)
					path = "";

				request.setAttribute(nombreFiesta, "nombreFiesta");
				request.setAttribute(descripcion, "descripcion");
				request.setAttribute(path, "pathDiscoteca");

				if (nombrePais == null) {
					nombrePais = "";
				}
				pais.setNombrePais("");

				java.util.List<Pais> listaPais = sp.listarPais(pais);
				request.setAttribute("listaPais", listaPais);

				if (nombreCiudad == null)
					nombreCiudad = "";

				try {
					if (nombrePais.equals(""))
						nombrePais = listaPais.get(0).getNombrePais();
				} catch (Exception e) {
				}

				ciudad.setNombreCiudad("");
				ciudad.setNombrePais(nombrePais);

				java.util.List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
				request.setAttribute("listaCiudad", listaCiudad);

				try {
					if (nombreCiudad.equals(""))
						nombreCiudad = listaCiudad.get(0).getNombreCiudad();
				} catch (Exception e) {
				}
				
				java.util.List<Musica> listaMusica = sm.listarMusica();
				request.setAttribute("listaMusica", listaMusica);

				getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/registrar.jsp")
						.forward(request, response);

			} else {
				String nombre = "";
				String nombreCiudad = "";
				String tipoMusica = "";
				String imagen = "";
				String nombrePais = "";
				String descripcion = "";
				String emailUsr = "";

				usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
				emailUsr = usrIniciado.getEmail();
				Ciudad c = new Ciudad();
				ServiceCiudad sc = new ServiceCiudad();
				
				try {
					nombre = request.getParameter("nombreDiscoteca");
					nombrePais = request.getParameter("pais");
					nombreCiudad = request.getParameter("ciudad");
					tipoMusica = request.getParameter("tipoMusica");
					imagen = request.getParameter("inputFile");
					descripcion = request.getParameter("descripcion");
					Discoteca disco = new Discoteca();

					disco.setNombre(nombre);
					disco.setTipoMusica(tipoMusica);
					disco.setImagen(imagen);
					disco.setDescripcion(descripcion);
					disco.setEmailUsr(emailUsr);
					
					ServiceDiscoteca sd = new ServiceDiscoteca();
					sd.registrarDiscoteca(disco, nombrePais, nombreCiudad);

					getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/home.jsp").forward

					(request, response);
				} catch (Exception e) {
					nombre = "";
					emailUsr = "";
					descripcion = "";
					imagen = "";
					nombrePais = "";
					nombreCiudad = "";
					tipoMusica = "";
					doGet(request, response);
				}
			}
		}
	}
}
