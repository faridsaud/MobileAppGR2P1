<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="ec.edu.epn.model.vo.Ciudad, ec.edu.epn.model.vo.Usuario, ec.edu.epn.model.vo.Pais, java.util.*"%>

<jsp:include page="/templates/header.jsp"></jsp:include>
<%-- --%>
    <div class="container">
    <%
    	Ciudad ciudadModificar = new Ciudad();
    	try{
    		ciudadModificar = (Ciudad) request.getSession().getAttribute("ciudadModificar");
    	}catch(Exception e){
    		
    	}
    	
    	if (ciudadModificar.getNombreCiudad() == null)
    		ciudadModificar.setNombreCiudad("");
    	
    	if (ciudadModificar.getNombrePais() == null)
    		ciudadModificar.setNombrePais("");
    	
		Usuario usrIniciado = new Usuario();
		boolean redireccion = true;
		String referenciaModificar = "#";
		String referenciaEliminar = "#";
	
		try {
			usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
			if (usrIniciado.isEstado() == true) {
				redireccion = false;
			}
		} catch (Exception e) {
			System.out.println("Error obteniendo usuario");
		}	
		if (redireccion == true) {
			getServletConfig().getServletContext().getRequestDispatcher("/Ciudad/Home").forward(request, response);
		} else if (usrIniciado.isAdmin() == true) {
			referenciaModificar = "${pageContext.request.contextPath}/Ciudad/Modificar";
			referenciaEliminar = "${pageContext.request.contextPath}/Ciudad/Eliminar";
		}

		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
	%>
      <form method="post">
      
        <div class="form-group">
      	<label for="pais">Pais</label>
      	  <input id="combos" name="combos" type="hidden" value="no"/>
      	  <select name="pais" class="form-control" id="pais" placeholder="Pais" required="true" 
          		onchange="document.getElementById('combos').value='si';
          				  document.getElementById('ciudad').value='';
          				  document.getElementById('nombrePaisModificar').value='<%=(String) request.getParameter("pais")%>'; 
          				  this.form.submit();">
            <%
            	try{
              		for (Pais p: listaPais){
              			if (p.getNombrePais().equals((String) request.getParameter("nombrePaisModificar"))){
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
          <label for="ciudad">Ciudad</label>
          <input name="ciudad" type="text" class="form-control" id="ciudad" placeholder="Ciudad" required="true" value="<%=ciudadModificar.getNombreCiudad()%>">
        </div>

        <button type="submit" class="btn btn-default">Modificar</button>
      </form>

    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>