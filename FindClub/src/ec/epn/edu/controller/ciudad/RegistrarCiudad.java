package ec.epn.edu.controller.ciudad;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class RegistrarCiudad
 * @author Samantha Molina
 */
@WebServlet("/Ciudad/Registrar")
public class RegistrarCiudad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarCiudad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServicePais sp = new ServicePais();
		Pais pais = new Pais();
		pais.setNombrePais("");
		
		java.util.List<Pais> listaPais = sp.listarPais(pais);
		request.setAttribute("listaPais", listaPais);
		
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
		} else if (usrIniciado.isAdmin())
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/ciudad/registrar.jsp").forward(request, response);
		else
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/ciudad/home.jsp").forward(request, response);			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombreCiudad;
		String nombrePais;
		
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
			getServletConfig().getServletContext().getRequestDispatcher("/Cuenta/Home").forward(request, response);
		} else if (usrIniciado.isAdmin()) {
			try{
				nombreCiudad = request.getParameter("ciudad");
				nombrePais = request.getParameter("pais");
				//nombrePais.replaceAll("\\s*$","");
				
				Ciudad ciudad = new Ciudad();
				if (nombreCiudad == null)
					nombreCiudad="";
				
				if (nombrePais == null)
					nombrePais="";
				
				ciudad.setNombreCiudad(nombreCiudad);
				ciudad.setNombrePais(nombrePais);
				
				System.out.println(ciudad.getNombreCiudad()+" "+ciudad.getNombrePais());
				
				ServiceCiudad sc = new ServiceCiudad();
				sc.registrarCiudad(ciudad);
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/ciudad/home.jsp").forward(request, response);
	
			}catch(Exception e){
				nombreCiudad = "";
				nombrePais = "";
				doGet(request, response);
			}
		}
	}

}
