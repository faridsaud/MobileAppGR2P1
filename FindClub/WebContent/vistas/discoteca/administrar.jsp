<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.vo.*"%>
	<jsp:include page="/templates/header.jsp"></jsp:include>
	<div class="container">


            <div class="row">
              <div class="col-xs-12">
                <form method="get">
                  <div class="form-group">
                    <label for="nombre">Nombre</label>
                    <input name="nombre" type="text" class="form-control" id="nombre" >
                  </div>

                  <div class="form-group">
                    <label for="pais">País</label>
                    <select name="pais" class="form-control" id="pais">
                      
                    </select>
                  </div>

                  <div class="form-group">
                    <label for="ciudad">Ciudad</label>
                    <select name="ciudad" class="form-control" id="ciudad">
                      
                    </select>
                  </div>


                  <div class="form-group">
                    <label for="tipoMusica">Tipo de musica principal</label>
                    <select name="tipoMusica" class="form-control" id="tipoMusica">
                      
                    </select>
                  </div>
                  <button type="submit" class="btn btn-default">Buscar</button>
                </form>

              </div>
            </div>
            <div class="row">
			<div class="col-xs-12">
				<table class="table">
					<thead>
						<tr>
							<th>Nombre de la discoteca</th>
							<th>Acción</th>

						</tr>
					</thead>
					<tbody>
						<%
							List<Discoteca> listaDiscotecas = (List<Discoteca>) request.getAttribute("listaDiscotecas");
							for (Discoteca disco : listaDiscotecas) {
						%>
						<tr>
							<td><a href="/vistas/discoteca/info.jsp"><%=disco.getNombre()%></a></td>
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
	</div>
	<jsp:include page="/templates/footer.jsp"></jsp:include>