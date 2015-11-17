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
			ServiceDiscoteca sd = new ServiceDiscoteca();
			String nombre = "";
			String tipoMusica = "";
			String imagen = "";
			String nombreCiudad = request.getParameter("ciudad");
			String nombrePais = request.getParameter("pais");
			String descripcion = "";
			String email = "";
			ServicePais sp = new ServicePais();
			ServiceCiudad sc = new ServiceCiudad();
			ServiceMusica sm = new ServiceMusica();
			Ciudad ciudad = new Ciudad();
			Pais pais = new Pais();
			if (nombreCiudad == null)
				nombreCiudad = "";
			ciudad.setNombreCiudad(nombreCiudad);
			if (nombrePais == null)
				nombrePais = "";
			pais.setNombrePais("");
			ciudad.setNombrePais(nombrePais);
			List<Pais> listaPais = sp.listarPais(pais);
			request.setAttribute("listaPais", listaPais);
			List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
			request.setAttribute("listaCiudad", listaCiudad);
			request.setAttribute("listaPais", listaPais);
			List<Musica> listaMusica = sm.listarMusicaByTipo(tipoMusica);
			request.setAttribute("listaMusica", listaMusica);
			try{
				nombre=request.getParameter("nombre");
				nombrePais=request.getParameter("pais");
				nombreCiudad=request.getParameter("ciudad");
				tipoMusica=request.getParameter("tipoMusica");
				imagen=request.getParameter("imagen");
				descripcion=request.getParameter("descripcion");
				email=request.getParameter("email");
			}catch(Exception e){
			
			}
			Discoteca disco = new Discoteca();
			List<Discoteca> listarDiscotecas = sd.listarDiscoteca(disco);
			request.setAttribute("listaDiscotecas", listarDiscotecas);
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
