<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="ec.edu.epn.model.vo.*" %>
	<jsp:include page="/templates/header.jsp"></jsp:include>
     <div class="container">
     <%
    	Discoteca discoModificar=new Discoteca();
		try{
		discoModificar=(Discoteca)request.getAttribute("discotecaModificar");
		
		}catch(Exception e){
			
		}
		if(discoModificar.getNombre()==null)
			discoModificar.setNombre("");

		if(discoModificar.getPais()==null)
			discoModificar.setPais("");

		if(discoModificar.getEmailUsr()==null)
			discoModificar.setEmailUsr("");
		
		if(discoModificar.getCiudad()==0)
			discoModificar.setCiudad(0);

		if(discoModificar.getTipoMusica()==null)
			discoModificar.setTipoMusica("");
		
		if(discoModificar.getImagen()==null)
			discoModificar.setImagen("");
		
		if(discoModificar.getDescripcion()==null)
			discoModificar.setDescripcion("");
	%>
      <form method="post">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input type="nombre" class="form-control" id="nombre" placeholder="Nombre" name="nombre" readonly="true" value="<%=discoModificar.getNombre()%>">
        </div>
        <div class="form-group">
          <label for="pais">Pais</label>
          <input type="pais" class="form-control" id="pais" placeholder="Pais" name="pais" value="<%=discoModificar.getPais()%>">
        </div>

        <div class="form-group">
          <label for="ciudad">Ciudad</label>
          <input type="ciudad" class="form-control" id="ciudad" placeholder="Ciudad" name="ciudad" value="<%=discoModificar.getCiudad()%>">
        </div>
		<div class="form-group">
          <label for="tipoMusica">Tipo de música principal</label>
          <input type="tipoMusica" class="form-control" id="tipoMusica" placeholder="Tipo de Musica" name="tipoMusica" value="<%=discoModificar.getTipoMusica()%>">
        </div>
        <div class="form-group">
          <label for="descripcion">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="dirección, tipo de vestimenta, etc", required="true" value=<%=discoModificar.getDescripcion() %> ></textarea>
        </div>
        <div class="form-group">
          <label for="inputFile">Imágen de la discoteca</label>
          <input type="file" id="inputFile" value=<%= discoModificar.getImagen() %>>
          <p class="help-block">Foto que muestre algo característico de la discoteca</p>
        </div>
        <button type="submit" class="btn btn-default">Actualizar</button>
      </form>
    </div>
    <jsp:include page="/templates/footer.jsp"></jsp:include>