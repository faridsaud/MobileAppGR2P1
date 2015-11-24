<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ec.edu.epn.model.vo.*, java.util.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
	<%
		Discoteca discotecaModificar = new Discoteca();
		String nDiscoteca = "";
		try{
			
		}catch (Exception e){
			discotecaModificar = (Discoteca) request.getSession().getAttribute("discotecaModificar");
			nDiscoteca = request.getParameter("nDiscoteca");
		}
		
		String nombreDiscoteca = (String) request.getParameter("nombreDiscoteca");
		String descripcion = (String) request.getParameter("decripcion");
		String path = (String) request.getParameter("pathDiscoteca");

		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
		List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
		List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");

		if (nombreDiscoteca == null)
			nombreDiscoteca = "";
		if (descripcion == null)
			descripcion = "";
		if (path == null)
			path = "";
	%>
	<div class="form-group">
		<label for="nombre">Nombre</label> <input name="nombreDiscoteca"
			id="nombreDiscoteca" class="form-control" placeholder="Nombre"
			value="<%=discotecaModificar.getNombre()%>" required="true">
	</div>

	<div class="form-group">
		<label for="pais">Pais</label> <input id="combos" name="combos"
			type="hidden" value="no" /> <select name="pais" class="form-control"
			id="pais" placeholder="Pais" required="true"
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
			class="form-control" id="ciudad" placeholder="Ciudad" required="true"
			onchange=" document.getElementById('combos').value='si'; this.form.submit()">
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
		<label for="descripcion">Descripción</label>
		<textarea name="descripcion" class="form-control" rows="5"
			id="descripcion" placeholder="dirección, tipo de vestimenta, etc"
			required="true" value="<%=discotecaModificar.getDescripcion()%>">
			</textarea>
	</div>
	<div class="form-group">
		<label for="inputFile">Imagen de la discoteca</label> <input
			name="pathDiscoteca" id="pathDiscoteca" type="file" id="inputFile"
			name="inputFile">
		<p class="help-block">Foto que muestre algo característico de la discoteca</p>
	</div>
	<button type="submit" class="btn btn-default">Modificar</button>

	</form>

</div>
<jsp:include page="/templates/footer.jsp"></jsp:include>