<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, ec.edu.epn.model.vo.Ciudad, ec.edu.epn.model.vo.Pais, ec.edu.epn.model.vo.Discoteca, 
	ec.edu.epn.model.vo.Fiesta"%>

<jsp:include page="/templates/header.jsp"></jsp:include>

<div class="container">
	<%
		List<Ciudad> listaCiudad = (List<Ciudad>) request.getAttribute("listaCiudad");
  		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
  		List<Discoteca> listaDiscoteca = (List<Discoteca>) request.getAttribute("listaDiscoteca");
  		List<Fiesta> listaFiesta = (List<Fiesta>) request.getAttribute("listaFiesta");
	%>

  <div class="row">
   <div class="col-xs-12">
     <form action="">
     
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
          		onchange=" document.getElementById('combos').value='si'; 
          				   document.getElementById('discoteca').value=''; 
          				   this.form.submit()">
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
          <select name="discoteca" class="form-control" id="discoteca" placeholder="Discoteca" required="true"
          	onchange="document.getElementById('combos').value='si'; 
          				  this.form.submit();">
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
              <label for="fiesta">Fiesta</label>
              <input name="fiesta" type="text" class="form-control" id="fiesta" >
            </div>

            <button type="submit" class="btn btn-default" onchange="this.form.method='GET'; this.form.submit()"
            	onclick="document.getElementById('combos').value='si'; this.form.submit();"
            	>
            	Buscar
            </button>
          </form>

        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <br><br>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <table class="table">
            <thead>
              <tr>
                <th>Fiesta</th>
                <th>Email</th>
                <th>Fecha</th>
                <th>Hora</th>
                <th>Descripcion</th>
                <th>Acción</th>
              </tr>
            </thead>
            
            <tbody>
              <%
            		try{
            			for (Fiesta f : listaFiesta){
              %>
              		<tr>
              		
              			<td><%=f.getNombreFiesta() %></td>
              			<td><%=f.getEmail() %></td>
              			<td><%=f.getFecha()%></td>	
              			<td><%=f.getHora() %></td>
              			<td><%=f.getDescripcion() %></td>
              			<td>
							<form method="get"
								action="${pageContext.request.contextPath}/Fiesta/Modificar">
								<button type="submit" class="btn btn-default"
									value="<%=f.getIdFiesta()%>" name="fModificar">
									<input type="hidden" value="<%=f.getNombreFiesta()%>" name="nFiesta">
									<span class="glyphicon glyphicon-pencil" title="Modificar fiesta"></span>
								</button>
							</form>
							<form method="post"
								action="${pageContext.request.contextPath}/Fiesta/Eliminar">
								<button type="submit" class="btn btn-default"
									value="<%=f.getIdFiesta()%>" name="fiestaEliminar">
									<span class="glyphicon glyphicon-remove" title="Eliminar fiesta"></span>
								</button>
							</form> 
						</td>
              		</tr>
              <%
            			}
            		}catch(Exception e){
            			
            		}
              %>
            </tbody>
          </table>
        </div>
      </div>
    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>