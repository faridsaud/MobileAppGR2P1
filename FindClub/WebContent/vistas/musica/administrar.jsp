<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*, ec.edu.epn.model.vo.*" %>

<jsp:include page="/templates/header.jsp"></jsp:include>

      <div class="container">
      
      
      <div class="row">
        <div class="col-xs-12">
          <form method="get">
            
            <div class="form-group">
              <label for="tipoMusica">Tipo de Música</label>
              <input name="tipoMusica" type="text" class="form-control" id="tipoMusica" >
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
                <th>Tipo de Música</th>
                <th>Acción</th>
                
              </tr>
            </thead>
            <tbody>
						<%
							List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");
							for (Musica msc : listaMusica) {
						%>
						<tr>
							<td><%=msc.getNombreTipo()%></td>
							<td>
								<form method="get"
									action="${pageContext.request.contextPath}/Musica/Modificar">
									<button type="submit" class="btn btn-default"
										value="<%=msc.getNombreTipo() %>" name="tipoMusicaModificar">
										<span class="glyphicon glyphicon-pencil"
											title="Modificar música"></span>
									</button>
								</form>
								<form method="post"
									action="${pageContext.request.contextPath}/Musica/Eliminar">
									<button type="submit" class="btn btn-default"
										value="<%=msc.getNombreTipo()%>" name="tipoMusicaEliminar">
										<span
									class="glyphicon glyphicon-remove" title="Eliminar música"></span>
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
      
      
    </div>
    
    

<jsp:include page="/templates/footer.jsp"></jsp:include>