package ec.epn.edu.controller.discoteca;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class BuscarDiscoteca
 */
@WebServlet("/Discoteca/Buscar")
public class BuscarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String nombre;
       
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/buscar.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceDiscoteca sd = new ServiceDiscoteca();
		String nombre = null;
		String tipoMusica = null;
		String imagen = null;
		String nombreCiudad = request.getParameter("ciudad");
		String nombrePais = request.getParameter("pais");
		String descripcion = null;
		String email = null;
		ServicePais sp = new ServicePais();
		ServiceCiudad sc = new ServiceCiudad();
		ServiceMusica sm = new ServiceMusica();
		Ciudad ciudad = new Ciudad();
		Pais pais = new Pais();
		if(nombre==null)
			nombre="";
		if(imagen==null)
			imagen="";
		if(descripcion==null)
			nombre="";
		if(email==null)
			email="";
		if(tipoMusica==null)
			tipoMusica="";
		if (nombreCiudad == null)
			nombreCiudad = "";
		ciudad.setNombreCiudad(nombreCiudad);
		if (nombrePais == null)
			nombrePais = "";
		pais.setNombrePais("");
		ciudad.setNombrePais(nombrePais);
		List<Pais> listaPais = sp.listarPais(pais);
		request.setAttribute("listaPais", listaPais);
		List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
		request.setAttribute("listaCiudad", listaCiudad);
		request.setAttribute("listaPais", listaPais);
		List<Musica> listaMusica = sm.listarMusicaByTipo(tipoMusica);
		request.setAttribute("listaMusica", listaMusica);
		try{
			nombre=request.getParameter("nombre");
			nombrePais=request.getParameter("pais");
			nombreCiudad=request.getParameter("ciudad");
			tipoMusica=request.getParameter("tipoMusica");
			imagen=request.getParameter("imagen");
			descripcion=request.getParameter("descripcion");
			email=request.getParameter("email");
		}catch(Exception e){
			nombre="";
			nombreCiudad="";
			nombrePais="";
			email="";
			tipoMusica="";
			descripcion="";
			imagen="";
		}
		Discoteca disco = new Discoteca();
		List<Discoteca> listarDiscotecas =sd.listarDiscoteca(disco);
		request.setAttribute("listaDiscotecas", listarDiscotecas);
	}

}