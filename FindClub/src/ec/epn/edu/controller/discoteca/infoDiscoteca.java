package ec.epn.edu.controller.discoteca;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;

/**
 * Servlet implementation class InfoDiscoteca
 */
@WebServlet("/Discoteca/Info")
public class InfoDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoDiscoteca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idDiscoteca="1780";
		
		ServiceCiudad sc= new ServiceCiudad();
		ServiceDiscoteca sd= new ServiceDiscoteca();
		Discoteca disco=sd.buscarDiscoteca(Integer.parseInt(idDiscoteca));
		Ciudad ciudad=sc.buscarCiudad(disco.getCiudad());
		request.getSession().setAttribute("discoInfo", disco);
		request.getSession().setAttribute("ciudadDisco", ciudad);
		System.out.println(disco.getNombre());
		System.out.println(ciudad.getNombreCiudad());
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/info.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
