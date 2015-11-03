package ec.epn.edu.controller.cuenta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.vo.Usuario;
import sun.usagetracker.UsageTrackerClient;

/**
 * Servlet implementation class Modificar
 */
@WebServlet("/Cuenta/Modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modificar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email="";
		Usuario usr=new Usuario();
		ServiceUsuario su=new ServiceUsuario();
		usr=su.buscarUsuarioByEmail((String)request.getParameter("emailModificar"));
		
		request.getSession().setAttribute("usuarioModificar", usr);
	
		try{
		}
		catch(Exception e){
			System.out.println("error publicacion usuario modificar");
		}
		
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/modificar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usrModificador=new Usuario();
		Usuario usrModificar=new Usuario();
		try{
			
			usrModificar=(Usuario)request.getSession().getAttribute("usuarioModificar");
			usrModificador.setEmail(request.getParameter("email"));
			usrModificador.setPassword(request.getParameter("password"));
			usrModificador.setNombre(request.getParameter("nombre1"));
			usrModificador.setApellido(request.getParameter("apellido1"));
			ServiceUsuario su=new ServiceUsuario();
			
			su.modificarUsuario(usrModificar, usrModificador);
			
		}catch(Exception e){
			System.out.println("Error modificacion");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/Cuenta/Home").forward(request, response);
	}

}
