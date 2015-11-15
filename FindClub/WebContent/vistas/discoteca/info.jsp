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
%>
<div class="container">
      <form method="post">
        <div class="form-group">
          <label for="<%= %>">Nombre</label>
          <input name="nombre" type="text" class="form-control" id="nombre" readonly="true">
        </div>

          <div class="form-group">
            <label for="<%= %>">Pais</label>
            <input name="pais" type="text" class="form-control" id="pais" readonly="true">
          </div>

            <div class="form-group">
              <label for="<%= %>">Ciudad</label>
              <input name="ciudad" type="text" class="form-control" id="ciudad" readonly="true">
            </div>


            <div class="form-group">
              <label for="<%= %>">Tipo de música</label>
              <input name="tipoMusica" type="text" class="form-control" id="tipoMusica" readonly="true">
            </div>


        <div class="form-group">
          <label for="<%= %>">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="dirección, tipo de vestimenta, etc" readonly="true"></textarea>
        </div>
      </form>
 </div>
<jsp:include page="/templates/footer.jsp"></jsp:include>