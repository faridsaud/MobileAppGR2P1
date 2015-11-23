<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.vo.*"%>
	<jsp:include page="/templates/header.jsp"></jsp:include>
	   <div class="container">
	   <form method="post">
    <%
    	Usuario usrIniciado = new Usuario();
		boolean redireccion = true;
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado.isEstado() == true) {
				redireccion = false;
			}
			if(usrIniciado.isAdmin()==true){
				redireccion = false;
			}
		} catch (Exception e) {
			System.out.println("Error obteniendo usuario");
		}	
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Discoteca/Home").forward(request, response);
		}	
	
		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
		List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
		List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");
    	
	%>
      
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input class="form-control" id="nombre" placeholder="Nombre" name="nombre" required="true">
        </div>

        <div class="form-group">
          <label for="pais">Pais</label>
          <select name="pais" class="form-control" id="pais" name="pais1" onchange="this.form.method='GET'; document.getElementById('ciudad').value=''; this.form.submit()">
            <%
            for (Pais p: listaPais){
      			if (p.getNombrePais().equals((String) request.getParameter("pais"))){
          			%><option selected values ="<%= p.getNombrePais()%>"><%=p.getNombrePais()%></option><%
      			}
      			else{
      				%><option values ="<%= p.getNombrePais()%>"><%=p.getNombrePais()%></option><%
      			}
      		}
              %>
          </select>
        </div>
        <div class="form-group">
          <label for="ciudad">Ciudad</label>
          <select name="ciudad" class="form-control" id="ciudad" name="ciudad1" 
          	onchange="this.form.method='GET'; this.form.submit()">
            	<% 
            	try{
              		for (Ciudad c: listaCiudad){
              			if(c.getNombrePais().equals(request.getParameter("pais"))){ %>
                  			<option selected value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option>
                  			<% }
                  	}
              		
              	}
            	catch(Exception e){
              		
              	}
            	%>
          </select>
        </div>


        <div class="form-group">
          <label for="tipoMusica">Tipo de musica principal</label>
          <select name="tipoMusica" class="form-control" id="tipoMusica">
            	<%
        	
            	try{
              		for (Musica m: listaMusica){
                  			%><option selected value="<%=m.getNombreTipo()%>"><%=m.getNombreTipo()%></option><%
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
        </div>

        <div class="form-group">
          <label for="descripcion">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="dirección, tipo de vestimenta, etc"></textarea>
        </div>
        <div class="form-group">
          <label for="inputFile">Imagen de la discoteca</label>
          <input type="file" id="inputFile" name="imagen">
          <p class="help-block">Foto que muestre algo característico de la discoteca</p>
        </div>
        <button type="submit" class="btn btn-default">Buscar</button>

      </form>

    </div>
          <div class="row">
			<div class="col-xs-12">
				<table class="table">
					<thead>
						<tr>
							<th>Nombre discoteca</th>
							<th>Descripción</th>
							<th>Acción</th>

						</tr>
					</thead>
					<tbody>
						<%
							List<Discoteca> listaDiscotecas = (List<Discoteca>) request.getAttribute("listaDiscotecas");
							for (Discoteca disco : listaDiscotecas) {
						%>
						<tr>
							<td><%=disco.getNombre()%></td>
							<td><%=disco.getDescripcion() %></td>
							
							<td>
								<form method="get"
									action="${pageContext.request.contextPath}/Discoteca/Modificar">
									<button type="submit" class="btn btn-default"
										value="<%=disco.getNombre()%>" name="nombreModificar">
										<span class="glyphicon glyphicon-pencil"
											title="Modificar discoteca"></span>
									</button>
								</form>
								<form method="post"
									action="${pageContext.request.contextPath}/Discoteca/Eliminar">
									<button type="submit" class="btn btn-default"
										value="<%=disco.getNombre()%>" name="nombreEliminar">
										<span
									class="glyphicon glyphicon-remove" title="Eliminar discoteca"></span>
									</button>
								</form> 
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	<jsp:include page="/templates/footer.jsp"></jsp:include>