package ec.epn.edu.controller.musica;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.vo.Musica;

/**
 * Servlet implementation class ModificarMusica
 */
@WebServlet("/Musica/Modificar")
public class ModificarMusica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarMusica() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipoMusica= (String)request.getParameter("tipoMusicaModificar");
		if(tipoMusica.equals("")==false && tipoMusica!=null){
			ServiceMusica sm= new ServiceMusica();
			Musica msc= sm.buscarMusica(tipoMusica);
			if(msc!=null){
				System.out.println("llamada doGet exitosa");
				request.getSession().setAttribute("musicaModicar", msc);
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/modificar.jsp").forward(request, response);
			}
		}else{
			System.out.println("entro aqui");
			getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/home.jsp").forward(request, response);
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doPost here");
		Musica msc=(Musica)request.getSession().getAttribute("musicaModicar"); 
		if(msc!=null){
			String tipoMusica=(String)request.getParameter("tipoMusica");
			String descripcion=(String)request.getParameter("descripcionMusica");
			if(tipoMusica!=null && descripcion!=null){
				System.out.println("entro aca");
				ServiceMusica sm= new ServiceMusica();
				Musica mscModificador=new Musica();
				mscModificador.setDescripcion(descripcion);
				mscModificador.setNombreTipo(tipoMusica);
				sm.modificarMusica(msc, mscModificador);
				getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/home.jsp").forward(request, response);
				
			}else{
				System.out.println("hizo un doget");
				doGet(request, response);
			}
		}else{
			System.out.println("musica es null");
		}
	}

}
