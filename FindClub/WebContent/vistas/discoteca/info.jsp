<%@page import="ec.edu.epn.model.service.musica.ServiceMusica"%>
<%@page import="ec.edu.epn.model.service.ciudad.ServiceCiudad"%>
<%@page import="ec.edu.epn.model.service.pais.ServicePais"%>
<%@page import="ec.edu.epn.model.service.discoteca.ServiceDiscoteca"%>
<%@page import="ec.edu.epn.model.vo.Discoteca"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="ec.edu.epn.model.vo.*, ec.edu.epn.model.service.*" %>
<jsp:include page="/templates/header.jsp"></jsp:include>
<%
	
	Discoteca d = new Discoteca();
	Pais p = new Pais();
	Ciudad c = new Ciudad();
	Musica m = new Musica();
	ServiceDiscoteca sd = new ServiceDiscoteca();
	ServicePais sp = new ServicePais();
	ServiceCiudad sc = new ServiceCiudad();
	ServiceMusica sm = new ServiceMusica();
	request.getParameter("nombre");
	request.getParameter("pais");
	request.getParameter("ciudad");
	request.getParameter("tipoMusica");
	request.getParameter("inputFile");
	request.getParameter("descripcion");
%>
<div class="container">
      <form method="get">
        <div class="form-group">
          <label for="<%=d.getNombre() %>">Nombre</label>
          <input name="nombre" type="text" class="form-control" id="nombre" readonly="true" value=<%d.getNombre();%>>
        </div>

          <div class="form-group">
            <label for="<%=p.getNombrePais() %>">Pais</label>
            <input name="pais" type="text" class="form-control" id="pais" readonly="true" value=<%d.getPais();%>>
          </div>

            <div class="form-group">
              <label for="<%=c.getNombreCiudad() %>">Ciudad</label>
              <input name="ciudad" type="text" class="form-control" id="ciudad" readonly="true" value=<%d.getCiudad(); %>>
            </div>


            <div class="form-group">
              <label for="<%=d.getTipoMusica() %>">Tipo de música</label>
              <input name="tipoMusica" type="text" class="form-control" id="tipoMusica" readonly="true" value=<%d.getTipoMusica(); %>>
            </div>


        <div class="form-group">
          <label for="<%=d.getDescripcion() %>">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="dirección, tipo de vestimenta, etc" readonly="true" value="<%d.getDescripcion(); %>"></textarea>
        </div>
        <div class="col-xs-12">
          <img src=<%=d.getImagen() %> class="img-responsive" alt="Cinque Terre" width="304" height="236">
        </div>
      </form>
 </div>
<jsp:include page="/templates/footer.jsp"></jsp:include>