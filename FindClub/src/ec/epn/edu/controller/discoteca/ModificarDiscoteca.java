package ec.epn.edu.controller.discoteca;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class ModificarDiscoteca
 */
@WebServlet("/Discoteca/Modificar")
public class ModificarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarDiscoteca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email="";
		Discoteca disco=new Discoteca();
		ServiceDiscoteca sd=new ServiceDiscoteca();
		disco=sd.buscarDiscoteca((String)request.getParameter("nombreModificar"));
		
		request.getSession().setAttribute("discotecaModificar", disco);
	
		try{
		}
		catch(Exception e){
			System.out.println("error publicacion discoteca modificar");
		}
		
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/modificar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Discoteca discoModificador=new Discoteca();
		Discoteca discoModificar=new Discoteca();
		ServiceCiudad sc = new ServiceCiudad();
		try{
			
			discoModificar=(Discoteca)request.getSession().getAttribute("discotecaModificar");
			discoModificador.setEmailUsr(request.getParameter("email"));
			discoModificador.setNombre(request.getParameter("nombre"));
			discoModificador.setPais(request.getParameter("pais"));
			discoModificador.setCiudad(sc.buscarCiudad(request.getParameter("ciudad")).getIdCiudad());
			discoModificador.setTipoMusica(request.getParameter("tipoMusica"));
			discoModificador.setImagen(request.getParameter("imagen"));
			ServiceDiscoteca sd=new ServiceDiscoteca();
			
			sd.modificarDiscoteca(discoModificar, discoModificador);
			
		}catch(Exception e){
			System.out.println("Error modificacion");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
		
	}

}