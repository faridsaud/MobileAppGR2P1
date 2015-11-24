package ec.epn.edu.controller.discoteca;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.cuenta.ServiceUsuario;
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
 * Servlet implementation class AdministrarDiscoteca
 */
@WebServlet("/Discoteca/Administrar")
public class AdministrarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarDiscoteca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usrIniciado = new Usuario();
		boolean redireccion = true;
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado.isEstado() == true) {
				redireccion = false;
			}
		} catch (Exception e) {
			System.out.println("Error obteniendo discoteca");
		}
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
		} else {
			ServiceUsuario su = new ServiceUsuario();
			ServicePais sp = new ServicePais();
			ServiceCiudad sc = new ServiceCiudad();
			ServiceDiscoteca sd = new ServiceDiscoteca();
			ServiceFiesta sf = new ServiceFiesta();
			ServiceMusica sm = new ServiceMusica();
			
			String emailUsuario = "";
			try {
				emailUsuario = request.getParameter("email");
			} catch (Exception e) {

			}
			if (emailUsuario == null)
				emailUsuario = "";

			Pais pais = new Pais();
			Ciudad ciudad = new Ciudad();
			Discoteca discoteca = new Discoteca();
			Fiesta fiesta = new Fiesta();

			String nombrePais = request.getParameter("pais");
			String nombreCiudad = request.getParameter("ciudad");
			String nombreDiscoteca = request.getParameter("discoteca");
			String nombreFiesta = request.getParameter("fiesta");

			if (nombrePais == null) {
				nombrePais = "";
			}
			pais.setNombrePais("");

			java.util.List<Pais> listaPais = sp.listarPais(pais);
			request.setAttribute("listaPais", listaPais);

			if (nombreCiudad == null)
				nombreCiudad = "";
			if (nombrePais == "")
				nombrePais = listaPais.get(0).getNombrePais();

			ciudad.setNombreCiudad("");
			ciudad.setNombrePais(nombrePais);

			java.util.List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
			request.setAttribute("listaCiudad", listaCiudad);

			if (nombreCiudad == "")
				nombreCiudad = listaCiudad.get(0).getNombreCiudad();

			discoteca.setCiudad(sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad());
			discoteca.setNombre("");

			java.util.List<Discoteca> listaDiscoteca = sd.listarDiscotecaByNombre(nombreCiudad, nombrePais);
			request.setAttribute("listaDiscoteca", listaDiscoteca);
			
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/administrar.jsp").forward(request, response);	
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
