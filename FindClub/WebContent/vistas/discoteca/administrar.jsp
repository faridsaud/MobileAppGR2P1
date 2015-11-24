<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.vo.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
	<form method="post">
		<%
			List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
			List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
			List<Discoteca> listaDiscoteca = (List<Discoteca>) request.getAttribute("listaDiscoteca");
			List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");
		%>

		<div class="form-group">
			<label for="pais">Pais</label> <input id="combos" name="combos"
				type="hidden" value="no" /> <select name="pais"
				class="form-control" id="pais" placeholder="Pais" required="true"
				onchange="document.getElementById('combos').value='si'; 
          				  document.getElementById('ciudad').value=''; 
          				  this.form.submit();">
				<%
					try {
						for (Pais p : listaPais) {
							if (p.getNombrePais().equals((String) request.getParameter("pais"))) {
				%><option selected values="<%=p.getNombrePais()%>"><%=p.getNombrePais()%></option>
				<%
					} else {
				%><option values="<%=p.getNombrePais()%>"><%=p.getNombrePais()%></option>
				<%
					}
						}
					} catch (Exception e) {

					}
				%>
			</select>
		</div>

		<div class="form-group">
			<label for="pais">Ciudad</label> <select name="ciudad"
				class="form-control" id="ciudad" placeholder="Ciudad"
				required="true"
				onchange=" document.getElementById('combos').value='si';
          				   this.form.submit()">
				<%
					try {
						for (Ciudad c : listaCiudad) {
							if (c.getNombreCiudad().equals((String) request.getParameter("ciudad"))) {
				%><option selected value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option>
				<%
					} else {
				%><option value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option>
				<%
					}
						}
					} catch (Exception e) {

					}
				%>
			</select>
		</div>
		<div class="form-group">
			<label for="tipoMusica">Tipo de musica principal</label> <select
				name="tipoMusica" class="form-control" id="tipoMusica">
				<%
					try {
						for (Musica m : listaMusica) {
				%><option selected value="<%=m.getNombreTipo()%>"><%=m.getNombreTipo()%></option>
				<%
					}
					} catch (Exception e) {

					}
				%>
			</select>
		</div>

		<div class="form-group">
			<label for="discoteca">Discoteca</label> 
			<input name="discoteca" type="text" class="form-control" id="discoteca">
		</div>

		<button type="submit" class="btn btn-default"
			onchange="this.form.method='GET'; this.form.submit()"
			onclick="document.getElementById('combos').value='si'; this.form.submit();">
			Buscar</button>
	</form>

	<div class="row">
		<div class="col-xs-12">
			<table class="table">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Descripcion</th>
						<th>Acción</th>
					</tr>
				</thead>

				<tbody>
					<%
						try {
							for (Discoteca disco : listaDiscoteca) {
					%>
					<tr>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Discoteca/Info">
								<button type="submit" class="btn btn-default"
									value="<%=disco.getIdDiscoteca()%>" name="discotecaInfo">
									<%=disco.getNombre()%>
								</button>
							</form>
						</td>
						<td><%=disco.getDescripcion()%></td>
						<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Discoteca/Modificar">
								<button type="submit" class="btn btn-default"
									value="<%=disco.getIdDiscoteca()%>" name="dModificar">
									<input type="hidden" value="<%=disco.getNombre()%>"
										name="nDiscoteca"> <span
										class="glyphicon glyphicon-pencil" title="Modificar discoteca"></span>
								</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/Discoteca/Eliminar">
								<button type="submit" class="btn btn-default"
									value="<%=disco.getIdDiscoteca()%>" name="discotecaEliminar">
									<span class="glyphicon glyphicon-remove"
										title="Eliminar discoteca"></span>
								</button>
							</form>
						</td>
					</tr>
					<%
						}
						} catch (Exception e) {

						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>