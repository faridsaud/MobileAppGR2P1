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
import ec.edu.epn.model.vo.Fiesta;
import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Pais;

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
		ServicePais sp = new ServicePais();
		Pais p = new Pais();
		p.setNombrePais("");
		List<Pais> listaPais = sp.listarPais(p);
		request.setAttribute("listaPais", listaPais);
		
		ServiceCiudad sc = new ServiceCiudad();
		Ciudad c = new Ciudad();
		c.setNombrePais("");
		List<Ciudad> listaCiudad = sc.listarCiudad(c);
		request.setAttribute("listaCiudad", listaCiudad);
		request.setAttribute("listaPais", listaPais);
		
		ServiceMusica sm = new ServiceMusica();
		Musica m = new Musica();
		m.setNombreTipo("");
		List<Musica> listaMusica = sm.listarMusica();
		request.setAttribute("listaMusica", listaMusica);
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
			emailUsr=request.getParameter("emailUsr");
			Discoteca disco = new Discoteca();
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
