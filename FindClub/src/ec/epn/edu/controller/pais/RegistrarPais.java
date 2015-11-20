package ec.epn.edu.controller.pais;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class RegistrarPais
 * @author Samantha Molina
 */
@WebServlet("/Pais/Registrar")
public class RegistrarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarPais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			getServletConfig().getServletContext().getRequestDispatcher("/Pais/Home").forward(request, response);
		} else if (usrIniciado.isAdmin())
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/pais/registrar.jsp").forward(request, response);
		else
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/pais/home.jsp").forward(request, response);			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombrePais = "";
		
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
			getServletConfig().getServletContext().getRequestDispatcher("/Pais/Home").forward(request, response);
		} else if (usrIniciado.isAdmin()) {
			try{
				nombrePais = request.getParameter("pais");
				Pais pais = new Pais();
				pais.setNombrePais(nombrePais);
				
				ServicePais sp = new ServicePais();
				sp.registrarPais(pais);
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/pais/home.jsp").forward(request, response);
	
			}catch(Exception e){
				nombrePais = "";
				doGet(request, response);
			}
		}
	}

}
