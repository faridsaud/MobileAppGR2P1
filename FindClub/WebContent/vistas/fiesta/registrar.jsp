<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ec.edu.epn.model.vo.*, java.util.*" %>

<jsp:include page="/templates/header.jsp"></jsp:include>
<%	
	String nombreFiesta = (String) request.getParameter("nombreFiesta");
	String fecha = (String) request.getParameter("fecha");
	String hora = (String) request.getParameter("hora");
	String descripcion = (String) request.getParameter("descripcion");
	
	List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
	List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
	List<Discoteca> listaDiscoteca = (List<Discoteca>) request.getAttribute("listaDiscoteca");
	
	if (nombreFiesta == null)
		nombreFiesta = "";
	if (descripcion == null)
		descripcion = "";
	if (hora == null)
		hora = "";
	if (fecha == null)
		fecha = "";
%>
    <div class="container">
      
      <form method="post">
      
      <div class="form-group">
          <label for="email">Nombre</label>
          <input type="text" class="form-control" id="nombreFiesta" placeholder="Nombre" name="nombreFiesta" 
          		value="<%=nombreFiesta %>" required="true">
      </div>
      
      <div class="form-group">
      	<label for="pais">Pais</label>
      	  <input id="combos" name="combos" type="hidden" value="no"/>
          <select name="pais" class="form-control" id="pais" placeholder="Pais" required="true" 
          		onchange="document.getElementById('combos').value='si'; 
          				  document.getElementById('ciudad').value=''; 
          				  this.form.submit();">
            <%
            	try{
              		for (Pais p: listaPais){
              			if (p.getNombrePais().equals((String) request.getParameter("pais"))){
                  			%><option selected values ="<%= p.getNombrePais()%>"><%=p.getNombrePais()%></option><%
              			}
              			else{
              				%><option values ="<%= p.getNombrePais()%>"><%=p.getNombrePais()%></option><%
              			}
              		}
              	}catch(Exception e){
              		
              	}
              %>
          </select>
       </div>
       
       <div class="form-group">
       <label for="pais">Ciudad</label>
          <select name="ciudad" class="form-control" id="ciudad" placeholder="Ciudad" required="true"
          		onchange=" document.getElementById('combos').value='si'; this.form.submit()">
            <%
            	try{
              		for (Ciudad c: listaCiudad){
              			if (c.getNombreCiudad().equals((String) request.getParameter("ciudad"))){
                  			%><option selected value="<%=c.getNombreCiudad()%>"><%=c.getNombreCiudad()%></option><%
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
          <select name="discoteca" class="form-control" id="discoteca" placeholder="Discoteca" required="true">
            <%
            	try{
              		for (Discoteca d: listaDiscoteca){
              			if (d.getNombre().equals((String) request.getParameter("discoteca"))){
                  			%><option selected value="<%=d.getNombre()%>"><%=d.getNombre()%></option><%
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
        
        <div class="form-group">
          <label for="email">Fecha</label>
          <input type="date" class="form-control" id="fecha" placeholder="yyyy-mm-dd" name="fecha" 
          		value="<%=fecha %>" required="true">
        </div>
        <div class="form-group">
          <label for="email">Hora</label>
          <input type="time" class="form-control" id="hora" placeholder="00:00:00" name="hora" 
          		value="<%=hora %>" required="true">
        </div>
        <div class="form-group">
          <label for="email">Descripción</label>
          <input type="text" class="form-control" id="descripcion" placeholder="Descripcion" name="descripcion" 
          		required="true">
        </div>
        <button type="submit" class="btn btn-default">Registrar</button>
      </form>
    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>