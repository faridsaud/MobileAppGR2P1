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
 * Servlet implementation class AdministrarPais
 */
@WebServlet("/Pais/Administrar")
public class AdministrarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarPais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
			getServletConfig().getServletContext().getRequestDispatcher("/Pais/Home").forward(request, response);
		} else{
			ServicePais sp = new ServicePais();
			Pais pais = new Pais();
			String nombrePais = (String) request.getParameter("pais");
			
			if (nombrePais == null){
				nombrePais="";
			}
			
			pais.setNombrePais(nombrePais);
			
			java.util.List<Pais> listaPais = sp.listarPais(pais);
			request.setAttribute("listaPais", listaPais);
			getServletConfig().getServletContext().
				getRequestDispatcher("/vistas/pais/administrar.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
