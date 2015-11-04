package ec.epn.edu.controller.cuenta;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class Administrar
 */
@WebServlet("/Cuenta/Administrar")
public class AdministrarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdministrarCuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usrIniciado = new Usuario();
		boolean redireccion = true;
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado.isEstado() == true) {
				redireccion = false;
			}
		} catch (Exception e) {
			System.out.println("Error obteniendo usuario");
		}
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Cuenta/Home").forward(request, response);
		} else {
			ServiceUsuario su=new ServiceUsuario();
			String emailUsuario="";
			try{
				emailUsuario=request.getParameter("email");
				
			}catch(Exception e){
				
			}
			if(emailUsuario==null){
				emailUsuario="";
			}
			List<Usuario> listaUsuarios=su.listarUsuario(usrIniciado, emailUsuario);
			request.setAttribute("listaUsuarios", listaUsuarios);
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/administrar.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
