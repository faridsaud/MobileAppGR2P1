package ec.epn.edu.controller.pais;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class ModificarPais
 * @author Samantha Molina
 */
@WebServlet("/Pais/Modificar")
public class ModificarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarPais() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Pais pais = new Pais();
		ServicePais sp = new ServicePais();
		pais = sp.buscarPais((String)request.getParameter("paisModificar"));
		
		request.getSession().setAttribute("paisModificar", pais);
		
		try{
		}catch(Exception e){
			System.out.println("error publicacion pais modificar");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/pais/modificar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombrePais="";
		Pais paisModificador = new Pais();
		Pais paisModificar = new Pais();
		ServicePais sp = new ServicePais();
		
		try{
			paisModificar = (Pais) request.getSession().getAttribute("paisModificar");
			nombrePais = request.getParameter("pais");
			paisModificador.setNombrePais(nombrePais);
			String msm = sp.modificarPais(paisModificar, paisModificador);
			
			if (!msm.equals("")){
				PrintWriter out = response.getWriter();
				out.write(
						"<div class=\"panel panel-danger \">"
						+ "<div class=\"panel-heading\">"
						+ "<h3 class=\"panel-title\">Panel danger</h3></div>"
						+ "<div class=\"panel-body\">"+msm+"</div>"
						+ "</div>"
						+ "</div>"
				);
			}
			getServletConfig().getServletContext().getRequestDispatcher("/Pais/Home").forward(request, response);
		}catch(Exception e){
			nombrePais = "";
			doGet(request, response);
		}
	}

}
