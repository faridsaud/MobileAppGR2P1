package ec.epn.edu.controller.fiesta;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.fiesta.ServiceFiesta;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Fiesta;

/**
 * Servlet implementation class InfoFiesta
 */
@WebServlet("/Fiesta/Info")
public class InfoFiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoFiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		Discoteca disco=(Discoteca)request.getSession().getAttribute("discoInfo");
		ServiceFiesta sf= new ServiceFiesta();
		List<Fiesta> listaFiestas=sf.buscarFiestaByDisco(disco);
		request.setAttribute("listaFiestasDisco", listaFiestas);
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/fiesta/info.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
