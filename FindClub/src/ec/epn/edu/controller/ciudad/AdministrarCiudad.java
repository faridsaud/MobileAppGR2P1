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
 * Servlet implementation class AdministrarCiudad
 */
@WebServlet("/Ciudad/Administrar")
public class AdministrarCiudad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarCiudad() {
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
			System.out.println("Error obteniendo usuario");
		}
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Pais/Home").forward(request, response);
		} else{
			ServicePais sp = new ServicePais();
			ServiceCiudad sc = new ServiceCiudad();
			Ciudad ciudad = new Ciudad();
			Pais pais = new Pais();
			
			String nombreCiudad = request.getParameter("ciudad");
			if (nombreCiudad == null)
				nombreCiudad = "";
			ciudad.setNombreCiudad(nombreCiudad);
			
			String nombrePais = request.getParameter("pais");
			if (nombrePais == null)
				nombrePais = "";
			pais.setNombrePais("");
			ciudad.setNombrePais(nombrePais);
			
			java.util.List<Pais> listaPais = sp.listarPais(pais);
			request.setAttribute("listaPais", listaPais);
			
			java.util.List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
			request.setAttribute("listaCiudad", listaCiudad);
			request.setAttribute("listaPais", listaPais);
			
			getServletConfig().getServletContext().
				getRequestDispatcher("/vistas/ciudad/administrar.jsp").forward(request, response);
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
