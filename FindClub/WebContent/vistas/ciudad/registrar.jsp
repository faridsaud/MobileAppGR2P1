<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="ec.edu.epn.model.vo.Pais, ec.edu.epn.model.vo.Ciudad, ec.edu.epn.model.vo.Usuario, java.util.*"%>

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
			getServletConfig().getServletContext().getRequestDispatcher("/Ciudad/Home").forward(request, response);
		}

		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
	%>
      <form method="post">
        <div class="form-group">
          <label for="pais">Pais</label>
          <select name="pais" class="form-control" id="pais" placeholder="Pais" required="true">
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
          <input name="ciudad" type="text" class="form-control" id="ciudad" placeholder="Ciudad" required="true" >
        </div>

        <button type="submit" class="btn btn-default">Registrar</button>
      </form>

    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>