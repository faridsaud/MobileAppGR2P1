<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="ec.edu.epn.model.vo.*, java.util.*" %>
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
		if(discoModificar.getDescripcion()==null)
			discoModificar.setDescripcion("");
		if(discoModificar.getPais()==null)
			discoModificar.setPais("");
		if(discoModificar.getImagen()==null)
			discoModificar.setImagen("");
		if(discoModificar.getEmailUsr()==null)
			discoModificar.setEmailUsr("");
		if(discoModificar.getTipoMusica()==null)
			discoModificar.setTipoMusica("");
		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
		List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
		List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");
	%>
      <form method="post">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input name="nombre" class="form-control" id="nombre" placeholder="Nombre" name="nombre" readonly="true" value="<%=discoModificar.getNombre()%>">
        </div>
        <div class="form-group">
          <label for="descripcion">Descripcion</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="dirección, tipo de vestimenta, etc" name="descripcion"  value ="<%=discoModificar.getDescripcion() %>"></textarea>
        </div>
       <div class="form-group">
          <label for="pais">Pais</label>
          <select name="pais" class="form-control" id="pais" name="pais"  onchange="this.form.method='GET'; document.getElementById('ciudad').value=''; this.form.submit()" >
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
          <select name="ciudad" class="form-control" id="ciudad" name="ciudad1" >
            	<% 
            	try{
              		for (Ciudad c: listaCiudad){ %>
                  			<option selected value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option><% 
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
          <label for="inputFile">Imágen de la discoteca</label>
          <input type="file" id="inputFile" value=<%= discoModificar.getImagen() %>>
          <p class="help-block">Foto que muestre algo característico de la discoteca</p>
        </div>
        <button type="submit" class="btn btn-default">Actualizar</button>
      </form>
    </div>
    <jsp:include page="/templates/footer.jsp"></jsp:include>