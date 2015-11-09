<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ec.edu.epn.model.vo.*, java.util.*" %>

<jsp:include page="/templates/header.jsp"></jsp:include>
<%	
	List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
	List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
	List<Discoteca> listaDiscoteca = (List<Discoteca>) request.getAttribute("listaDiscoteca");
	List<Musica> listaMusica = (List<Musica>) request.getAttribute("listaMusica");
%>
    <div class="container">
      <form method="post">
      <label for="pais">Pais</label>
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
          <select name="ciudad" class="form-control" id="pais" name="pais1" placeholder="Pais" required="true">
            <%
        		contadorElementos = 0;
            	try{
              		for (Ciudad c: listaCiudad){
              			if (contadorElementos == 0){
                  			%><option selected value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option><%
                  			contadorElementos++;
                  		}
                  		else{
                  			%><option value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option><%
                  		}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
          <select name="ciudad" class="form-control" id="pais" name="pais1" placeholder="Pais" required="true">
            <%
        		contadorElementos = 0;
            	try{
              		for (Discoteca d: listaDiscoteca){
              			if (contadorElementos == 0){
                  			%><option selected value="<%=d.getNombreDiscoteca()%>"><%=d.getNombreDiscoteca()%></option><%
                  			contadorElementos++;
                  		}
                  		else{
                  			%><option value="<%=d.getNombreDiscoteca()%>"><%=d.getNombreDiscoteca()%></option><%
                  		}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
        <div class="form-group">
          <label for="email">Nombre</label>
          <input type="nombreFiesta" class="form-control" id="nombreFiesta" placeholder="Nombre" name="nombreFiesta" required="true">
        </div>
        <div class="form-group">
          <label for="email">Nombre</label>
          <input type="nombreFiesta" class="form-control" id="nombreFiesta" placeholder="Nombre" name="nombreFiesta" required="true">
        </div>
        <div class="form-group">
          <label for="email">Nombre</label>
          <input type="nombreFiesta" class="form-control" id="nombreFiesta" placeholder="Nombre" name="nombreFiesta" required="true">
        </div>
        <button type="submit" class="btn btn-default">Registrar</button>
      </form>
    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>