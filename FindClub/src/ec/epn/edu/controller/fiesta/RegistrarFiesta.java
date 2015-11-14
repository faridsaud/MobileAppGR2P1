package ec.epn.edu.controller.fiesta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.service.fiesta.ServiceFiesta;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Fiesta;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class RegistrarFiesta
 */
@WebServlet("/Fiesta/Registrar")
public class RegistrarFiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarFiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServicePais sp = new ServicePais();
		ServiceCiudad sc = new ServiceCiudad();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		
		Pais pais = new Pais();
		Ciudad ciudad = new Ciudad();
		Discoteca discoteca = new Discoteca();
		
		pais.setNombrePais("");
		java.util.List<Pais> listaPais = sp.listarPais(pais);
		request.setAttribute("listaPais", listaPais);
		
		String nombrePais = (String) request.getParameter("pais");
		if(nombrePais == null)
			nombrePais = listaPais.get(0).getNombrePais();
		ciudad.setNombrePais(nombrePais);
		ciudad.setNombreCiudad("");
		java.util.List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
		request.setAttribute("listaCiudad", listaCiudad);
		
		discoteca = sd.buscarDiscotecaByCiudad(request.getParameter("ciudad"));
		discoteca.setNombre("");
		java.util.List<Discoteca> listaDiscoteca = sd.listarDiscoteca(discoteca.getNombre(), discoteca);
		request.setAttribute("listaDiscoteca", listaDiscoteca);
		
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/fiesta/registrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Fiesta fiesta = new Fiesta();
		String email="";
		String nombreDiscoteca="";
		String nombreFiesta="";
		String fecha="";
		String hora="";
		String descripcion="";
		
		try{
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
				getServletConfig().getServletContext().getRequestDispatcher("/Ciudad/Home").forward(request, response);
			} else {
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/ciudad/registrar.jsp").forward(request, response);
				email = usrIniciado.getEmail();
				nombreDiscoteca = request.getParameter("discoteca");
				nombreFiesta = request.getParameter("nombreFiesta");
				fecha = request.getParameter("fecha");
				hora = request.getParameter("hora");
				descripcion = request.getParameter("descripcion");
				ServiceFiesta sf= new ServiceFiesta();
				sf.registrarFiesta(fiesta);
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/fiesta/home.jsp").forward(request, response);
			}
		}catch(Exception e){
			email="";
			nombreDiscoteca="";
			nombreFiesta="";
			fecha="";
			hora="";
			descripcion="";
			doGet(request, response);
		}
	}

}
