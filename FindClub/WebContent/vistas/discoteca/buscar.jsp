<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.vo.*"%>
	<jsp:include page="/templates/header.jsp"></jsp:include>
	<div class="container">
    <%
		String email = (String)request.getAttribute("emailUsr");
		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
		List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
		List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");
	%>
      <form method="post">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input type="nombre" class="form-control" id="nombre" placeholder="Nombre" name="nombre" required="true" readonly="true">
        </div>

        <div class="form-group">
          <label for="pais">Pais</label>
          <select name="pais" class="form-control" id="pais" name="pais1" placeholder="Pais">
            <%
        		int contadorElementos = 0;
            	try{
              		for (Pais p: listaPais){
              			if (contadorElementos == 0){
                  			%><option selected value="<%=p.getNombrePais()%>"><%=p.getNombrePais()%></option><%
                  			contadorElementos++;
                  		}
                  		else{
                  			%><option value="<%=p.getNombrePais()%>"><%= p.getNombrePais()%></option><%
                  		}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
        </div>

        <div class="form-group">
          <label for="ciudad">Ciudad</label>
          <select name="ciudad" class="form-control" id="pais" name="pais" placeholder="Pais">
            	<%
        		int contador2 = 0;
            	try{
              		for (Ciudad c: listaCiudad){
              			if (contador2 == 0){
                  			%><option selected value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option><%
                  			contador2++;
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
        		int contador3 = 0;
            	try{
              		for (Musica m: listaMusica){
              			if (contador3 == 0){
                  			%><option selected value="<%=m.getNombreTipo()%>"><%=m.getNombreTipo()%></option><%
                  			contador3++;
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
							

						</tr>
					</thead>
					<tbody>
						<%
							List<Discoteca> listaDiscotecas = (List<Discoteca>) request.getAttribute("listaDiscotecas");
							for (Discoteca disco : listaDiscotecas) {
						%>
						<tr>
							<td><%=disco.getNombre()%></td>
							
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	<jsp:include page="/templates/footer.jsp"></jsp:include>