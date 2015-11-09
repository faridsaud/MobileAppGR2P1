package ec.epn.edu.controller.ciudad;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class ModificarCiudad
 */
@WebServlet("/Ciudad/Modificar")
public class ModificarCiudad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarCiudad() {
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
		ciudad = sc.buscarCiudad(ciudad, (String)request.getParameter("nombreCiudadModificar"));
		
		request.getSession().setAttribute("ciudadModificar", ciudad);
		
		ServicePais sp = new ServicePais();
		Pais pais = new Pais();
		pais.setNombrePais("");
		
		java.util.List<Pais> listaPais = sp.listarPais(pais);
		request.setAttribute("listaPais", listaPais);
		
		try{
		}catch(Exception e){
			System.out.println("error publicacion ciudad modificar");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/ciudad/modificar.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Ciudad ciudadModificador = new Ciudad();
		Ciudad ciudadModificar = new Ciudad();
		ServiceCiudad sc = new ServiceCiudad();
		
		try{
			ciudadModificar = (Ciudad) request.getSession().getAttribute("ciudadModificar");
			ciudadModificador.setNombreCiudad(request.getParameter("ciudad"));
			ciudadModificador.setNombrePais(request.getParameter("pais"));
			
			sc.modificarCiudad(ciudadModificar, ciudadModificador);
		}catch(Exception e){
			System.out.println("Error modificacion");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/Ciudad/Home").forward(request, response);

	}

}
