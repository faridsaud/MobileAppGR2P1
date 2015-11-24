<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, ec.edu.epn.model.vo.*"%>

<jsp:include page="/templates/header.jsp"></jsp:include>

<div class="container">
	<%
		List<Fiesta> listaFiestas = (List<Fiesta>) request.getAttribute("listaFiestasDisco");
		for (Fiesta fiesta : listaFiestas) {
	%>
	<div class="row">
		<div class="col-xs-12">
			<h2><%=fiesta.getNombreFiesta()%></h2>
			<p>Fecha:<%=fiesta.getFecha()%><p>
			<p>Hora:<%=fiesta.getHora()%><p>
			<p>Descripcion:<%=fiesta.getDescripcion()%><p>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<br>
		</div>
	</div>
	<%
	}
%>
	
</div>

<jsp:include page="/templates/footer.jsp"></jsp:include>