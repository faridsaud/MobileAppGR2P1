<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*,ec.edu.epn.model.vo.*" %>

<jsp:include page="/templates/header.jsp"></jsp:include>
<%Musica msc=(Musica)request.getSession().getAttribute("musicaModicar"); 
if(msc==null){
	getServletConfig().getServletContext().getRequestDispatcher("/vistas/musica/home.jsp").forward(request, response);
}else{
%>
    <div class="container">
      <form method="post">

        <div class="form-group">
          <label for="tipoMusica">Tipo de Música</label>
          <input name="tipoMusica" type="text" class="form-control" id="tipoMusica" required="true" value=<%=msc.getNombreTipo() %>>
        </div>


        <div class="form-group">
          <label for="descripcion">Descripción (Opcional)</label>
          <textarea name="descripcionMusica" class="form-control" rows="5" id="descripcion" placeholder="Explicar el tipo de música" value=<%=msc.getDescripcion()%>></textarea>
        </div>

        <button type="submit" class="btn btn-default">Modificar</button>
      </form>

    </div>
<%} %>

<jsp:include page="/templates/footer.jsp"></jsp:include>