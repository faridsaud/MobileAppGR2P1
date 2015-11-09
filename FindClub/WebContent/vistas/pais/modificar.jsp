<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="ec.edu.epn.model.vo.Pais"%>

<jsp:include page="/templates/header.jsp"></jsp:include>

    <div class="container">
    <%
    	Pais paisModificar = new Pais();
    	try{
    		paisModificar = (Pais) request.getSession().getAttribute("paisModificar");
    	}catch(Exception e){
    		
    	}
    	
    	if (paisModificar.getNombrePais() == null)
    		paisModificar.setNombrePais("");
    %>
      <form method="post">

        <div class="form-group">
          <label for="pais">Ciudad</label>
          <input name="pais" type="text" class="form-control" id="pais" name="pais1" placeholder="Pais" value=<%=paisModificar.getNombrePais()%>>
        </div>
        <button type="submit" class="btn btn-default">Actualizar</button>
      </form>

    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>