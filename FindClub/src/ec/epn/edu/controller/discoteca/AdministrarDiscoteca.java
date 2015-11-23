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
			String email = usrIniciado.getEmail();
			ServicePais sp = new ServicePais();
			ServiceCiudad sc = new ServiceCiudad();
			ServiceMusica sm = new ServiceMusica();
			Ciudad ciudad = new Ciudad();
			Pais pais = new Pais();
			Musica m = new Musica();
			String tipoMusica = request.getParameter("tipoMusica");
			String imagen = request.getParameter("inputFile");
			Discoteca disco = new Discoteca();
			String nombre = request.getParameter("nombre");
			if(nombre==null)
				nombre="";
			disco.setNombre(nombre);
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
			
			String descripcion = request.getParameter("descripcion");
			if(descripcion==null)
				descripcion="";
			disco.setDescripcion(descripcion);
			ServiceDiscoteca sd = new ServiceDiscoteca();
			
			List<Discoteca> listarDiscotecas = sd.listarDiscoteca(nombre, disco);
			request.setAttribute("listaDiscotecas", listarDiscotecas);
			
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/administrar.jsp").forward(request, response);	
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre="";
		String nombreCiudad="";
		String tipoMusica="";
		String imagen="";
		String nombrePais="";
		String descripcion="";
		String emailUsr="";
		Usuario usrIniciado= new Usuario();
		usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
   		emailUsr = usrIniciado.getEmail();
   		Ciudad c = new Ciudad();
   		ServiceCiudad sc = new ServiceCiudad();
		try{
			nombre=request.getParameter("nombre");
			nombrePais=request.getParameter("pais");
			nombreCiudad=request.getParameter("ciudad");
			tipoMusica=request.getParameter("tipoMusica");
			imagen=request.getParameter("inputFile");
			descripcion=request.getParameter("descripcion");			
		}catch(Exception e){
			nombre="";
			emailUsr="";
			descripcion="";
			imagen="";
			nombrePais="";
			nombreCiudad="";
			tipoMusica="";
			doGet(request, response);
		}
	}

}
