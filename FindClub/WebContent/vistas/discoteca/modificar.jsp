<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="ec.edu.epn.model.vo.*" %>
	<jsp:include page="/templates/header.jsp"></jsp:include>
     <div class="container">
     <%
    	Discoteca discoModificar=new Discoteca();
		try{
		discoModificar=(Discoteca)request.getSession().getAttribute("discotecaModificar");
		
		}catch(Exception e){
			
		}
		if(discoModificar.getNombre()==null)
			discoModificar.setNombre("");

		if(discoModificar.getPais()==null)
			discoModificar.setPais("");

		if(discoModificar.getEmailUsr()==null)
			discoModificar.setEmailUsr("");

		if(discoModificar.getTipoMusica()==null)
			discoModificar.setTipoMusica("");
		
		if(discoModificar.getImagen()==null)
			discoModificar.setImagen("");
	%>
      <form method="post">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input type="nombre" class="form-control" id="nombre" placeholder="Nombre" name="nombre" readonly="true" value="<%=discoModificar.getNombre()%>">
        </div>
        <div class="form-group">
          <label for="pais">Pais</label>
          <input type="pais" class="form-control" id="pais" placeholder="Pais" name="pais" readonly="true" value="<%=discoModificar.getPais()%>">
        </div>

        <div class="form-group">
          <label for="ciudad">Ciudad</label>
          <input type="ciudad" class="form-control" id="ciudad" placeholder="Ciudad" name="ciudad" readonly="true" value="<%=discoModificar.getCiudad()%>">
        </div>
		<div class="form-group">
          <label for="tipoMusica">Tipo de m�sica principal</label>
          <input type="tipoMusica" class="form-control" id="tipoMusica" placeholder="Tipo de Musica" name="tipoMusica" readonly="true" value="<%=discoModificar.getTipoMusica()%>">
        </div>
        <div class="form-group">
          <label for="descripcion">Descripci�n</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="direcci�n, tipo de vestimenta, etc", name=descripcion, readonly="true", value=<%=discoModificar.getDescripcion() %> ></textarea>
        </div>
        <div class="form-group">
          <label for="imagen">Im�gen de la discoteca</label>
          <input type="file" id="imagen" value=<%= discoModificar.getImagen() %>>
          <p class="help-block">Foto que muestre algo caracter�stico de la discoteca</p>
        </div>
        <button type="submit" class="btn btn-default">Actualizar</button>
      </form>
    </div>


  </div>
    
    <jsp:include page="/templates/footer.jsp"></jsp:include>