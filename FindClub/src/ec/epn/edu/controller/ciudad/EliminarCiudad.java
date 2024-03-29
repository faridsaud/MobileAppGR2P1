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

/**
 * Servlet implementation class EliminarCiudad
 * @author Samantha Molina
 */
@WebServlet("/Ciudad/Eliminar")
public class EliminarCiudad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCiudad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/Ciudad/Home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Ciudad ciudad = new Ciudad();
		ServiceCiudad sc = new ServiceCiudad();
		ServicePais sp = new ServicePais();
		String nombreCiudad = (String) request.getParameter("nombreCiudadEliminar");
		String nombrePais = (String) request.getParameter("nombrePaisEliminar");
		
		if (nombreCiudad == null)
			nombreCiudad="";
		if (nombrePais == null)
			nombrePais="";
		
		ciudad.setNombreCiudad(nombreCiudad);
		ciudad.setIdPais(sp.buscarPais(nombrePais).getIdPais());
		sc.eliminarCiudad(ciudad);
		request.setAttribute("ciudadEliminar", nombreCiudad);
		doGet(request, response);
	}
}
