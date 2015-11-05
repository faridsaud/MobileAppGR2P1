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
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/Cuenta/IniciarSesion")
public class IniciarSesionCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IniciarSesionCuenta() {
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
		boolean redireccion = false;
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado != null)
				if (usrIniciado.isEstado() == true) {
					redireccion = true;
					getServletConfig().getServletContext().getRequestDispatcher("/Cuenta/Home").forward(request,
							response);

				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (redireccion == false)
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/cuenta/iniciarSesion.jsp")
					.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String email = "";
		String password = "";
		try {
			email = request.getParameter("email");
			password = request.getParameter("password");
		} catch (Exception e) {
			email = "";
			password = "";
		}
		ServiceUsuario se = new ServiceUsuario();
		request.getSession().setAttribute("usuarioActivo", se.buscarUsuario(email, password));
		System.out.println(se.buscarUsuario(email, password).getNombre());
		doGet(request, response);

	}

}
