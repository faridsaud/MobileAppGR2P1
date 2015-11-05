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
 * Servlet implementation class Registrar
 */
@WebServlet("/Cuenta/Registrar")
public class RegistrarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/registrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email="";
		String password="";
		String nombre="";
		String apellido="";
		try{
			email=request.getParameter("email");
			password=request.getParameter("password");
			nombre=request.getParameter("nombre1");
			apellido=request.getParameter("apellido1");
			Usuario usr= new Usuario();
			usr.setApellido(apellido);
			usr.setEmail(email);
			usr.setPassword(password);
			usr.setNombre(nombre);
			ServiceUsuario su= new ServiceUsuario();
			su.registrarUsuario(usr);
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/home.jsp").forward(request, response);
			
		}catch(Exception e){
			email="";
			password="";
			nombre="";
			apellido="";
			doGet(request, response);
		}
		
	}

}
