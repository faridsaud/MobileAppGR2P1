package ec.epn.edu.controller.discoteca;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class EliminarDiscoteca
 */
@WebServlet("/Discoteca/Eliminar")
public class EliminarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarDiscoteca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usr;
		usr=(Usuario)request.getSession().getAttribute("usuarioActivo");
		if(usr==null){
			usr=new Usuario();
		}else{
			if(usr.getEmail().equals((String)request.getAttribute("emailEliminar")))
				request.getSession().invalidate();
				
		}
		
		getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceDiscoteca sd=new ServiceDiscoteca();
		String nombre=(String)request.getParameter("nombreEliminar");
		if(nombre==null){
			nombre="";
		}
		Discoteca disco=(Discoteca)sd.buscarDiscoteca(nombre);
		
		sd.eliminarDiscoteca(disco);
		request.setAttribute("nombreEliminar",nombre );
		doGet(request, response);
	}

}
