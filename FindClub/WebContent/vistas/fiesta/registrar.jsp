<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ec.edu.epn.model.vo.*, java.util.*" %>

<jsp:include page="/templates/header.jsp"></jsp:include>
<%	
	List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
	List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
	List<Discoteca> listaDiscoteca = (List<Discoteca>) request.getAttribute("listaDiscoteca");
%>
    <div class="container">
      
      <form action="${pageContext.request.contextPath}/Fiesta/CargarCuidad">
      <div class="form-group">
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
       </div>
       </form>
       
       <div class="form-group">
       <label for="pais">Ciudad</label>
          <select name="ciudad" class="form-control" id="ciudad" name="ciudad1" placeholder="Ciudad" required="true">
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
       </div>
       <div class="form-group">
       <label for="pais">Discoteca</label>
          <select name="discoteca" class="form-control" id="discoteca" name="discoteca" placeholder="Discoteca" required="true">
            <%
        		contadorElementos = 0;
            	try{
              		for (Discoteca d: listaDiscoteca){
              			if (contadorElementos == 0){
                  			%><option selected value="<%=d.getNombre()%>"><%=d.getNombre()%></option><%
                  			contadorElementos++;
                  		}
                  		else{
                  			%><option value="<%=d.getNombre()%>"><%=d.getNombre()%></option><%
                  		}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
        </div>
        
      <form method="post">
        <div class="form-group">
          <label for="email">Nombre</label>
          <input type="text" class="form-control" id="nombreFiesta" placeholder="Nombre" name="nombreFiesta" required="true">
        </div>
        <div class="form-group">
          <label for="email">Fecha</label>
          <input type="date" class="form-control" id="fecha" placeholder="yyyy-mm-dd" name="fecha" required="true">
        </div>
        <div class="form-group">
          <label for="email">Hora</label>
          <input type="time" class="form-control" id="hora" placeholder="00:00:00" name="hora" required="true">
        </div>
        <div class="form-group">
          <label for="email">Descripción</label>
          <input type="text" class="form-control" id="descripcion" placeholder="Descripcion" name="descripcion" required="true">
        </div>
        <button type="submit" class="btn btn-default">Registrar</button>
      </form>
    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>