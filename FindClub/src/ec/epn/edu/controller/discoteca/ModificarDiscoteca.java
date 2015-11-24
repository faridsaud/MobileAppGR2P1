package ec.epn.edu.controller.discoteca;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import ec.edu.epn.model.service.ciudad.ServiceCiudad;
import ec.edu.epn.model.service.cuenta.ServiceUsuario;
import ec.edu.epn.model.service.discoteca.ServiceDiscoteca;
import ec.edu.epn.model.service.fiesta.ServiceFiesta;
import ec.edu.epn.model.service.musica.ServiceMusica;
import ec.edu.epn.model.service.pais.ServicePais;
import ec.edu.epn.model.vo.Ciudad;
import ec.edu.epn.model.vo.Discoteca;
import ec.edu.epn.model.vo.Fiesta;
import ec.edu.epn.model.vo.Musica;
import ec.edu.epn.model.vo.Pais;
import ec.edu.epn.model.vo.Usuario;

/**
 * Servlet implementation class ModificarDiscoteca
 */
@WebServlet("/Discoteca/Modificar")
@MultipartConfig
public class ModificarDiscoteca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificarDiscoteca() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String combos = request.getParameter("combos");

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
			getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
		} else {
			if (combos == null || combos.equals("si")) {

				ServicePais sp = new ServicePais();
				ServiceCiudad sc = new ServiceCiudad();
				ServiceDiscoteca sd = new ServiceDiscoteca();
				ServiceMusica sm = new ServiceMusica();
				Discoteca disco = new Discoteca();

				String nombrePais = request.getParameter("pais");
				String nombreCiudad = request.getParameter("ciudad");
				String nombreDiscoteca = request.getParameter("nDiscoteca");
				String tipoMusica = request.getParameter("tipoMusica");
				String descripcion = (String) request.getParameter("descripcion");
				String path = (String) request.getParameter("pathDiscoteca");

				if (nombreDiscoteca == null)
					nombreDiscoteca = "";
				if (descripcion == null)
					descripcion = "";
				if (path == null)
					path = "";

				request.setAttribute(nombreDiscoteca, "nombreDiscoteca");
				request.setAttribute(descripcion, "descripcion");
				request.setAttribute(path, "pathDiscoteca");
				request.setAttribute(tipoMusica, "tipoMusica");

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

				java.util.List<Musica> listaMusica = sm.listarMusica();
				request.setAttribute("listaMusica", listaMusica);

				int idDiscoteca = 0;
				try {
					idDiscoteca = Integer.parseInt(request.getParameter("dModificar"));
					disco = sd.buscarDiscoteca(idDiscoteca);
					request.getSession().setAttribute("discotecaModificar", disco);
				} catch (Exception e) {
					System.out
							.println("Error al modificar discoteca, se recibió:" + request.getParameter("dModificar"));
				}

				getServletConfig().getServletContext().getRequestDispatcher("/vistas/discoteca/modificar.jsp")
						.forward(request, response);
			} else {
				Discoteca discotecaModificar = new Discoteca();
				Discoteca discotecaModificador = new Discoteca();

				ServiceDiscoteca sd = new ServiceDiscoteca();

				String nombrePais = "";
				String nombreCiudad = "";
				String email = usrIniciado.getEmail();
				String nombreDiscoteca = "";
				String tipoMusica = "";
				String descripcion = "";
				String pathImagen = "";

				try {
					nombreDiscoteca = (String) request.getParameter("nomDiscoteca");
					nombrePais = (String) request.getParameter("pais");
					nombreCiudad = (String) request.getParameter("ciudad");
					tipoMusica = (String) request.getParameter("tipoMusica");
					descripcion = (String) request.getParameter("descripcion");

					Discoteca discoRegistrada = sd.buscarDiscoteca(nombreDiscoteca);
					pathImagen = discoRegistrada.getImagen();
					discotecaModificar = (Discoteca) request.getSession().getAttribute("discotecaModificar");
					discotecaModificador.setNombre(nombreDiscoteca);
					discotecaModificador.setDescripcion(descripcion);
					discotecaModificador.setTipoMusica(tipoMusica);
					discotecaModificador.setImagen(discotecaModificar.getImagen());
					discotecaModificador.setEmailUsr(email);

					sd.modificarDiscoteca(discotecaModificar, discotecaModificador, nombrePais, nombreCiudad);

					/* Modificar archivo */
					String path = "C:/Users/farid/Documents/7mo/Aplicaciones Moviles/Proyectos/ProyectoBimestre1/MobileAppGR2P1/FindClub/WebContent/images/";
					File directorio = new File(path + discoRegistrada.getIdDiscoteca());
					directorio.mkdir();

					Part filePart = request.getPart("inputFile");
					if (filePart != null) {
						final String fileName = getFileName(filePart);
						if (fileName != null && fileName.equals("") == false) {
							System.out.println("imprimiendo archivo");
							System.out.println(fileName);
							discoRegistrada
									.setImagen("/FindClub/images/" + discoRegistrada.getIdDiscoteca() + "/" + fileName);
							try {
								sd.modificarDiscoteca(discoRegistrada, discoRegistrada);
							} catch (Exception e) {
								e.printStackTrace();
							}

							OutputStream out = null;
							InputStream filecontent = null;
							try {
								out = new FileOutputStream(new File(
										path + discoRegistrada.getIdDiscoteca() + "/" + File.separator + fileName));
								filecontent = filePart.getInputStream();

								int read = 0;
								final byte[] bytes = new byte[1024];

								while ((read = filecontent.read(bytes)) != -1) {
									out.write(bytes, 0, read);
								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								if (out != null) {
									out.close();
								}
								if (filecontent != null) {
									filecontent.close();
								}

							}

						}

					}
				} catch (Exception e) {
					System.out.println("Error en la modificación");
				}

				getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request,
						response);
			}
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
