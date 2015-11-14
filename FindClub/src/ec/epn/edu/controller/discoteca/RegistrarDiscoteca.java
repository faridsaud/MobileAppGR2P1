package ec.epn.edu.controller.discoteca;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.text.normalizer.UBiDiProps;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class RegistrarDiscoteca
 */
@WebServlet("/Discoteca/Registrar")
public class RegistrarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarDiscoteca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/registrar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre="";
		String pais="";
		String ciudad="";
		String tipoMusica="";
		String imagen="";
		String descripcion="";
		String emailUsr="";
		try{
			nombre=request.getParameter("nombre");
			pais=request.getParameter("pais");
			ciudad=request.getParameter("ciudad");
			tipoMusica=request.getParameter("tipoMusica");
			imagen=request.getParameter("imagen");
			descripcion=request.getParameter("descripcion");
			emailUsr=request.getParameter("emailUsuario");
			Discoteca disco = new Discoteca();
			Usuario usr = new Usuario();
			disco.setNombre(nombre);
			disco.setPais(pais);
			disco.setCiudad(ciudad);
			disco.setTipoMusica(tipoMusica);
			disco.setImagen(imagen);
			disco.setDescripcion(descripcion);
			disco.setEmailUsr(emailUsr);
			ServiceDiscoteca sd = new ServiceDiscoteca();
			sd.registrarDiscoteca(disco);
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/home.jsp").forward(request, response);
		}catch(Exception e){
			nombre="";
			pais="";
			ciudad="";
			tipoMusica="";
			imagen="";
			descripcion="";
			emailUsr="";
			doGet(request, response);
		}
	}

}
