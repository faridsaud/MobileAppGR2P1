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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				Discoteca disco = new Discoteca();

				int idDiscoteca = 0;
				try {
					idDiscoteca = Integer.parseInt(request.getParameter("dModificar"));
					disco = sd.buscarDiscoteca(idDiscoteca);
					request.getSession().setAttribute("discotecaModificar", disco);

				} catch (Exception e) {
					System.out.println("Error al modificar discoteca, se recibió:" + request.getParameter("dModificar"));
				}

				String nombrePais = request.getParameter("pais");
				String nombreCiudad = request.getParameter("ciudad");
				String nombreDiscoteca = request.getParameter("discoteca");
				String tipoMusica = request.getParameter("tipoMusica");
				String descripcion = (String) request.getParameter("descripcion");
				String path = (String) request.getParameter("pathDiscoteca");

				if (nombreDiscoteca == null)
					nombreDiscoteca = "";
				if (descripcion == null)
					descripcion = "";
				if (path == null)
					path = "";

				request.setAttribute(nombreDiscoteca, "nombreFiesta");
				request.setAttribute(descripcion, "descripcion");
				request.setAttribute(path, "pathDiscoteca");
				
				Pais pais = new Pais();
				Ciudad ciudad = new Ciudad();
				Discoteca discoteca = new Discoteca();

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
				
				java.util.List<Musica> listaMusica = sm.listarMusica();
				request.setAttribute("listaMusica", listaMusica);

				getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/modificar.jsp")
						.forward(request, response);
			} else {
				Discoteca discotecaModificar = new Discoteca();
				Discoteca discotecaModificador = new Discoteca();
				
				ServiceDiscoteca sd = new ServiceDiscoteca();

				String nombrePais = "";
				String nombreCiudad = "";
				String email = usrIniciado.getEmail();
				String nombreDiscoteca = "";
				String tipoMusica = "";
				String descripcion = "";
				String path = "";
				
				try {
					nombreDiscoteca = request.getParameter("discoteca");
					nombrePais = request.getParameter("pais");
					nombreCiudad = request.getParameter("ciudad");
					tipoMusica = request.getParameter("tipoMusica");
					descripcion = request.getParameter("descripcion");
					path = request.getParameter("pathDiscoteca");
					
					discotecaModificar = (Discoteca) request.getSession().getAttribute("discotecaModificar");
					discotecaModificador.setNombre(nombreDiscoteca);
					discotecaModificador.setDescripcion(descripcion);
					discotecaModificador.setTipoMusica(tipoMusica);
					discotecaModificador.setImagen(path);
					discotecaModificador.setEmailUsr(email);
					
					sd.modificarDiscoteca(discotecaModificar, discotecaModificador, nombrePais, nombreCiudad);
				} catch (Exception e) {
					System.out.println("Error en la modificación");
				}
				
				getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
			}
		}
	}
}
