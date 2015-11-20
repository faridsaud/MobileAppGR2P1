package ec.epn.edu.controller.fiesta;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.service.fiesta.ServiceFiesta;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Fiesta;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class AdministrarFiesta
 */
@WebServlet("/Fiesta/Administrar")
public class AdministrarFiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrarFiesta() {
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
			getServletConfig().getServletContext().getRequestDispatcher("/Fiesta/Home").forward(request, response);
		} else {
			ServiceUsuario su=new ServiceUsuario();
			ServicePais sp = new ServicePais();
			ServiceCiudad sc = new ServiceCiudad();
			ServiceDiscoteca sd = new ServiceDiscoteca();
			ServiceFiesta sf = new ServiceFiesta();
			
			String emailUsuario="";
			try{
				emailUsuario=request.getParameter("email");
			}catch(Exception e){
				
			}
			if(emailUsuario==null)
				emailUsuario="";
			
			Pais pais = new Pais();
			Ciudad ciudad = new Ciudad();
			Discoteca discoteca = new Discoteca();
			Fiesta fiesta = new Fiesta();
			
			String nombrePais = request.getParameter("pais");
			String nombreCiudad = request.getParameter("ciudad");
			String nombreDiscoteca = request.getParameter("discoteca");
			
			if (nombrePais == null){
				nombrePais = "";
			}
			pais.setNombrePais("");
			
			java.util.List<Pais> listaPais = sp.listarPais(pais);
			request.setAttribute("listaPais", listaPais);
			
			if (nombreCiudad == null)
				nombreCiudad = "";
			if (nombrePais == "")
				nombrePais = listaPais.get(0).getNombrePais();
			
			ciudad.setNombreCiudad("");
			ciudad.setNombrePais(nombrePais);
			
			java.util.List<Ciudad> listaCiudad = sc.listarCiudad(ciudad);
			request.setAttribute("listaCiudad", listaCiudad);
			
			if (nombreCiudad == "")
				nombreCiudad = listaCiudad.get(0).getNombreCiudad();
			
			discoteca.setCiudad(sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad());
			discoteca.setNombre("");
			
			java.util.List<Discoteca> listaDiscoteca = sd.listarDiscoteca(discoteca.getNombre(), discoteca);
			request.setAttribute("listaDiscoteca", listaDiscoteca);
			
			
			fiesta.setIdDiscoteca(sd.buscarDiscotecaByNombre(nombreDiscoteca, 
					sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad()).getIdDiscoteca());
			
			Usuario usr = new Usuario();
			usr = su.buscarUsuarioByEmail(emailUsuario);
			System.out.println(emailUsuario);
			java.util.List<Fiesta> listaFiesta = sf.listarFiesta(fiesta, usr);
			request.setAttribute("listaFiesta", listaFiesta);
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/fiesta/administrar.jsp").forward(request, response);
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
