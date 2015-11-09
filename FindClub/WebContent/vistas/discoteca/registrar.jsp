<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,ec.edu.epn.model.vo.*"%>
	<jsp:include page="/templates/header.jsp"></jsp:include>
	 <div class="container">
	 <%
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
		}

		List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
		List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");
		
	%>
      <form method="post">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input type="nombre" class="form-control" id="nombre" placeholder="Nombre" name="nombre" required="true">
        </div>

        <div class="form-group">
          <label for="pais">País</label>
          <select name="pais" class="form-control" id="pais" name="pais1" placeholder="Pais" required="true">
        		<%
        		int contadorElementos = 0;
            	try{
              		for (Pais p: listaPais){
              			if (contadorElementos == 0){
                  			%><option selected value="<%=p.getNombrePais()%>"><%=p.getNombrePais()%></option><%
                  			contadorElementos++;
                  		}
                  		else{
                  			%><option value="<%=p.getNombrePais()%>"><%=p.getNombrePais()%></option><%
                  		}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
        </div>

        <div class="form-group">
          <label for="ciudad">Ciudad</label>
          <select name="ciudad" class="form-control" id="pais" name="pais1" placeholder="Pais" required="true">
            	<%
        		int contadorElementos1 = 0;
            	try{
              		for (Ciudad c: listaCiudad){
              			if (contadorElementos1 == 0){
                  			%><option selected value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option><%
                  			contadorElementos1++;
                  		}
                  		else{
                  			%><option value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option><%
                  		}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
        </div>


        <div class="form-group">
          <label for="tipoMusica">Tipo de musica principal</label>
          <select name="tipoMusica" class="form-control" id="tipoMusica">
            	<%
        		int contadorElementos2 = 0;
            	try{
              		for (Musica m: listaMusica){
              			if (contadorElementos2 == 0){
                  			%><option selected value="<%=m.getNombreTipo()%>"><%=m.getNombreTipo()%></option><%
                  			contadorElementos2++;
                  		}
                  		else{
                  			%><option value="<%=m.getNombreTipo()%>"><%=m.getNombreTipo()%></option><%
                  		}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
        </div>


        <div class="form-group">
          <label>Tragos</label>
          <div class="checkbox">
            <label>
              <input type="checkbox" value="fuerte" name="tipoTrago">
              Fuerte
            </label>
            <label>
              <input type="checkbox" value="cocteles" name="tipoTrago">
              Cocteles
            </label>
            <label>
              <input type="checkbox" value="cerveza" name="tipoTrago">
              Cervezas
            </label>

            <label>
              <input type="checkbox" value="noAlcoholicas" name="tipoTrago">
              Bebidas no alcohólicas
            </label>
          </div>
        </div>


        <div class="form-group">
          <label for="descripcion">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="dirección, tipo de vestimenta, etc" required=true></textarea>
        </div>
        <div class="form-group">
          <label for="imagen">Imágen de la discoteca</label>
          <input type="file" id="imagen">
          <p class="help-block">Foto que muestre algo característico de la discoteca</p>
        </div>
        <button type="submit" class="btn btn-default">Registrar</button>
      </form>

    </div>
	<jsp:include page="/templates/footer.jsp"></jsp:include>