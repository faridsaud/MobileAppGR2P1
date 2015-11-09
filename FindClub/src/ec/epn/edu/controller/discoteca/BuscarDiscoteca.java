package ec.epn.edu.controller.discoteca;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class BuscarDiscoteca
 */
@WebServlet("/Discoteca/Buscar")
public class BuscarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarDiscoteca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceDiscoteca sd = new ServiceDiscoteca();
		String nombre = "";
		String pais = "";
		String ciudad = "";
		String tipoMusica = "";
		String imagen = "";
		String descripcion = "";
		String email = "";
		try{
			nombre=request.getParameter("nombre");
			pais=request.getParameter("pais");
			ciudad=request.getParameter("ciudad");
			tipoMusica=request.getParameter("tipoMusica");
			imagen=request.getParameter("imagen");
			descripcion=request.getParameter("descripcion");
			email=request.getParameter("email");
		}catch(Exception e){
			
		}
		Discoteca disco = new Discoteca();
		List<Discoteca> listarDiscotecas = sd.listarDiscoteca(nombre, disco);
		request.setAttribute("listaDiscotecas", listarDiscotecas);
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/buscar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
