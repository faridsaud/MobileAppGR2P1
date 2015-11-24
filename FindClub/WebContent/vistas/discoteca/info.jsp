
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ec.edu.epn.model.vo.*, ec.edu.epn.model.service.*"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<%
	Ciudad c=(Ciudad)request.getSession().getAttribute("ciudadDisco");
	Discoteca d=(Discoteca)request.getSession().getAttribute("discoInfo");
%>
<div class="container">

	<form >
		<div class="form-group">
			<label for="nombre">Nombre</label> <input name="nombre"
				type="text" class="form-control" id="nombre" readonly="true"
				value=<%=d.getNombre()%>>
		</div>

		<div class="form-group">
			<label for="ciudad">Ciudad</label> <input
				name="ciudad" type="text" class="form-control" id="ciudad"
				readonly="true" value=<%=c.getNombreCiudad()%>>
		</div>


		<div class="form-group">
			<label for="tipoMusica">Tipo de música</label> <input
				name="tipoMusica" type="text" class="form-control" id="tipoMusica"
				readonly="true" value=<%=d.getTipoMusica()%>>
		</div>


		<div class="form-group">
			<label for="descripcion">Descripción</label>
			<textarea name="descripcion" class="form-control" rows="5"
				id="descripcion" placeholder="dirección, tipo de vestimenta, etc"
				readonly="true"><%=d.getDescripcion()%></textarea>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<img src="<%=d.getImagen()%>" class="img-responsive"
					alt="Responsive image">
			</div>
		</div>
	</form>
</div>
<jsp:include page="/templates/footer.jsp"></jsp:include>