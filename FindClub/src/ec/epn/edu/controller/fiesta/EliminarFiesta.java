package ec.epn.edu.controller.fiesta;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.fiesta.ServiceFiesta;
import ec.edu.epn.model.vo.Fiesta;

/**
 * Servlet implementation class EliminarFiesta
 */
@WebServlet("/Fiesta/Eliminar")
public class EliminarFiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarFiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/Fiesta/Home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceFiesta sf = new ServiceFiesta();
		Fiesta fiesta = new Fiesta();
		int idFiesta = 0;
		try{
			idFiesta = Integer.parseInt(request.getParameter("fiestaEliminar"));
		}catch (Exception e){
			System.out.println("Error al eliminar fiesta");
		}
		
		fiesta.setIdFiesta(idFiesta);
		sf.eliminarFiesta(fiesta);
		
		doGet(request, response);
	}

}
