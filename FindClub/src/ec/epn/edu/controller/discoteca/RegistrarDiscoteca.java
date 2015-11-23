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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 

IOException {
		// TODO Auto-generated method stub
		Usuario usrIniciado = new Usuario();
		boolean redireccion = true;
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado.isEstado() == true) {
				redireccion = false;
			}
			if(usrIniciado.isAdmin()==true){
				redireccion = false;
			}
		} catch (Exception e) {
			System.out.println("Error obteniendo usuario");
		}	
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
		}else {
			ServicePais sp = new ServicePais();
			ServiceCiudad sc = new ServiceCiudad();
			ServiceMusica sm = new ServiceMusica();
			Ciudad ciudad = new Ciudad();
			Pais pais = new Pais();
			Musica m = new Musica();
			Discoteca disco = new Discoteca();

			/*
			String nombrePais = request.getParameter("pais");
			if (nombrePais == null)
				nombrePais = "";
			pais.setNombrePais("");
			ciudad.setNombrePais(nombrePais);
			
			List<Pais> listaPais = sp.listarPais(pais);
			request.setAttribute("listaPais", listaPais);
			ciudad.setIdPais(listaPais.get(0).getIdPais());
			List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
			request.setAttribute("listaCiudad", listaCiudad);
			*/

			String nombrePais = request.getParameter("pais");
			String nombreCiudad = request.getParameter("ciudad");
			
			if (nombrePais == null){
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
			
			m.setNombreTipo("");
			List<Musica> listaMusica = sm.listarMusica();
			request.setAttribute("listaMusica", listaMusica);
			
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/registrar.jsp").forward

(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 

IOException {
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
   		Pais pais= new Pais();
   		ServiceCiudad sc = new ServiceCiudad();
   		ServicePais sp= new ServicePais();
		try{
			nombre=request.getParameter("nombre");
			System.out.println(nombre);
			nombrePais=request.getParameter("pais");
			System.out.println(nombrePais);
			nombreCiudad=request.getParameter("ciudad");
			System.out.println(nombreCiudad);
			tipoMusica=request.getParameter("tipoMusica");
			//imagen=request.getParameter("inputFile");
			System.out.println(tipoMusica);
			descripcion=request.getParameter("descripcion");
			System.out.println(descripcion);
			Discoteca disco = new Discoteca();
			disco.setNombre(nombre);
			disco.setTipoMusica(tipoMusica);
			System.out.println(sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad());
			disco.setCiudad(sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad());
			//disco.setImagen(imagen);
			disco.setDescripcion(descripcion);
			disco.setEmailUsr(emailUsr);
			ServiceDiscoteca sd = new ServiceDiscoteca();
			sd.registrarDiscoteca(disco);
			
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/home.jsp").forward

(request, response);
		}catch(Exception e){
			e.printStackTrace();
			doGet(request, response);
		}
		
	}
}