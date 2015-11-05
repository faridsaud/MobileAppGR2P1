package ec.epn.edu.controller.cuenta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class Eliminar
 */
@WebServlet("/Cuenta/Eliminar")
public class EliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCuenta() {
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
		
		getServletConfig().getServletContext().getRequestDispatcher("/Cuenta/Home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceUsuario su=new ServiceUsuario();
		String email=(String)request.getParameter("emailEliminar");
		if(email==null){
			email="";
		}
		Usuario usr=(Usuario)su.buscarUsuarioByEmail(email);
		
		su.eliminarUsuario(usr);
		request.setAttribute("emailEliminar",email );
		doGet(request, response);
	}

}
