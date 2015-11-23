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
 * @author Samantha Molina
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
		ciudad = sc.buscarCiudad((String)request.getParameter("nombreCiudadModificar"),(String)request.getParameter("nombrePaisModificar"));
		
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
		ServicePais sp = new ServicePais();
		
		try{
			String nombreCiudad = (String) request.getParameter("ciudad");
			String nombrePais = (String) request.getParameter("pais");
			
			if (nombreCiudad == null)
				nombreCiudad="";
			if (nombrePais == null)
				nombrePais="";
			
			ciudadModificar = (Ciudad) request.getSession().getAttribute("ciudadModificar");
			ciudadModificador.setNombreCiudad(nombreCiudad);
			ciudadModificador.setIdPais(sp.buscarPais(nombrePais).getIdPais());
			ciudadModificador.setNombrePais(nombrePais);
			ciudadModificador.setIdCiudad(sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad());

			sc.modificarCiudad(ciudadModificar, ciudadModificador);
		}catch(Exception e){
			System.out.println("Error modificacion");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/Ciudad/Home").forward(request, response);

	}

}
