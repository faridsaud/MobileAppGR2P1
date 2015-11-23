package ec.epn.edu.controller.fiesta;

import java.io.IOException;

import javax.jms.Session;
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
 * Servlet implementation class ModificarFiesta
 */
@WebServlet("/Fiesta/Modificar")
public class ModificarFiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarFiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServiceUsuario su = new ServiceUsuario();
		ServicePais sp = new ServicePais();
		ServiceCiudad sc = new ServiceCiudad();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		ServiceFiesta sf = new ServiceFiesta();
		Fiesta fiesta = new Fiesta();
		
		Usuario usrIniciado = new Usuario();
		usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
		
		int idFiesta = 0;
		try{
			idFiesta = Integer.parseInt(request.getParameter("nombreFiestaModificar"));
			fiesta = sf.buscarFiesta(fiesta, idFiesta);
			
			request.getSession().setAttribute("fiestaModificar", fiesta);
			
		}catch (Exception e){
			System.out.println("Error al modificar fiesta, se recibió:"+request.getParameter("fiestaModificar"));
		}
		
		fiesta.setIdFiesta(idFiesta);
		
		String nombrePais = request.getParameter("pais");
		String nombreCiudad = request.getParameter("ciudad");
		String nombreDiscoteca = request.getParameter("discoteca");
		String nombreFiesta = request.getParameter("fiesta");
		
		Pais pais = new Pais();
		Ciudad ciudad = new Ciudad();
		Discoteca discoteca = new Discoteca();

		if (nombrePais == null) {
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

		java.util.List<Discoteca> listaDiscoteca = sd.listarDiscoteca(nombreCiudad);
		request.setAttribute("listaDiscoteca", listaDiscoteca);

		if (nombreDiscoteca == null)
			nombreDiscoteca = listaDiscoteca.get(0).getNombre();

		fiesta.setIdDiscoteca(sd.buscarDiscotecaByNombre(nombreDiscoteca,
				sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad()).getIdDiscoteca());
		System.out.println(fiesta.getIdDiscoteca());
		if (nombreFiesta == null)
			nombreFiesta = "";

		fiesta.setNombreFiesta(nombreFiesta);

		java.util.List<Fiesta> listaFiesta = sf.listarFiesta(fiesta, usrIniciado);
		request.setAttribute("listaFiesta", listaFiesta);
		
		getServletConfig().getServletContext().getRequestDispatcher("/vistas/fiesta/modificar.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Fiesta fiestaModificar = new Fiesta();
		Fiesta fiestaModificador = new Fiesta();
		
		ServiceFiesta sf = new ServiceFiesta();
		ServiceDiscoteca sd = new ServiceDiscoteca();
		ServiceCiudad sc = new ServiceCiudad();
		
		String nombrePais;
		String nombreCiudad;
		String email="";
		String nombreDiscoteca="";
		String nombreFiesta="";
		String fecha="";
		String hora="";
		String descripcion="";
		
		try{
			nombreDiscoteca = request.getParameter("discoteca");
			nombreFiesta = request.getParameter("nombreFiesta");
			nombrePais = request.getParameter("pais");
			nombreCiudad = request.getParameter("ciudad");
			fecha = request.getParameter("fecha");
			hora = request.getParameter("hora");
			descripcion = request.getParameter("descripcion");

			fiestaModificar = (Fiesta) request.getSession().getAttribute("ciudadModificar");
			fiestaModificador.setNombreFiesta(nombreFiesta);
			fiestaModificador.setIdDiscoteca(sd.buscarDiscotecaByNombre(nombreDiscoteca, 
					sc.buscarCiudad(nombreCiudad, nombrePais).getIdCiudad()).getIdDiscoteca());
			fiestaModificador.setEmail(email);
			fiestaModificador.setFecha(fecha);
			fiestaModificador.setHora(hora);
			fiestaModificador.setDescripcion(descripcion);
			
			sf.modificarFiesta(fiestaModificar, fiestaModificador);
		}catch(Exception e){
			System.out.println("Error en la modificación");
		}
		
		getServletConfig().getServletContext().getRequestDispatcher("/Fiesta/Home").forward(request, response);
	}

}
