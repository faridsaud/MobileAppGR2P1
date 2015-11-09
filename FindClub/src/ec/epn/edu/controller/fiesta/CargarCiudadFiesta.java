package ec.epn.edu.controller.fiesta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.vo.Ciudad;

/**
 * Servlet implementation class CargarCiudadFiesta
 */
@WebServlet("/Fiesta/CargarCiudad")
public class CargarCiudadFiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarCiudadFiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Ciudad ciudad = new Ciudad();
		ServiceCiudad sc = new ServiceCiudad();
		
		String nombrePais = (String) request.getParameter("pais");
		if(nombrePais == null)
			nombrePais = "";
		ciudad.setNombrePais(nombrePais);
		ciudad.setNombreCiudad("");
		java.util.List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
		request.setAttribute("listaCiudad", listaCiudad);
		
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/fiesta/registrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
