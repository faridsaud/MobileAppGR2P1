<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.util.*, ec.edu.epn.model.vo.Ciudad, ec.edu.epn.model.vo.Usuario, ec.edu.epn.model.vo.Pais"%>

<jsp:include page="/templates/header.jsp"></jsp:include>

<div class="container">
	<%
		Usuario usrIniciado = new Usuario();
		boolean redireccion = true;
		String referenciaModificar = "#";
		String referenciaEliminar = "#";
	
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado.isEstado() == true) {
				redireccion = false;
			}
		} catch (Exception e) {
			System.out.println("Error obteniendo usuario");
		}	
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Ciudad/Home").forward(request, response);
		} else if (usrIniciado.isAdmin() == true) {
			referenciaModificar = "Modificar";
			referenciaEliminar = "Eliminar";
		}

		List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
  		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
	%>

      <div class="row">
        <div class="col-xs-12">
          <form method="post">
            <div class="form-group">
              <label for="pais">Pais</label>
              <select name="pais" class="form-control" id="pais">
              <%
              	for (Pais p: listaPais){
              		%><option value="<%=p.getNombrePais() %>"><%=p.getNombrePais() %></option><%
              	}
              %>
              </select>
            </div>

            <div class="form-group">
              <label for="ciudad">Ciudad</label>
              <input name="ciudad" type="text" class="form-control" id="ciudad" >
            </div>

            <button type="submit" class="btn btn-default">Buscar</button>
          </form>

        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <br><br>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <table class="table">
            <thead>
              <tr>
                <th>Ciudad</th>
                <th>País</th>
                <th>Acción</th>
              </tr>
            </thead>
            
            <tbody>
              <%
            		try{
            			for (Ciudad c : listaCiudad){
              %>
              		<tr>
              		
              			<td><%=c.getNombreCiudad()%></td>
              			<td><%=c.getNombrePais() %></td>	
              			<td>
							<form method="get"
								action="<%=referenciaModificar%>">
								<button type="submit" class="btn btn-default"
									value="<%=c.getNombreCiudad()%>" name="nombreCiudadModificar">
									<span class="glyphicon glyphicon-pencil" title="Modificar ciudad"></span>
								</button>
								<input type="hidden" value="<%=c.getNombrePais()%>" name="nombrePaisModificar">
							</form>
							<form method="post"
								action="<%=referenciaEliminar%>">
								<button type="submit" class="btn btn-default"
									value="<%=c.getNombreCiudad()%>" name="nombreCiudadEliminar">
									<span class="glyphicon glyphicon-remove" title="Eliminar ciudad"></span>
								</button>
								<input type="hidden" value="<%=c.getNombrePais()%>" name="nombrePaisEliminar">
							</form> 
						</td>
              		</tr>
              <%
            			}
            		}catch(Exception e){
            			
            		}
              %>
            </tbody>
          </table>
        </div>
      </div>
    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>