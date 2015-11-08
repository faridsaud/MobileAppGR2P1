package ec.epn.edu.controller.pais;

import java.io.IOException;

import javax.security.auth.kerberos.ServicePermission;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class EliminarPais
 */
@WebServlet("/Pais/Eliminar")
public class EliminarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarPais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/Pais/Home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Pais pais = new Pais();
		ServicePais sp = new ServicePais();
		String nombrePais = (String) request.getParameter("paisEliminar");
		
		if (nombrePais == null)
			nombrePais="";
		
		pais.setNombrePais(nombrePais);
		pais = (Pais) sp.buscarPais(nombrePais);
		sp.eliminarPais(pais);
		request.setAttribute("paisEliminar", nombrePais);
		doGet(request, response);
	}

}
