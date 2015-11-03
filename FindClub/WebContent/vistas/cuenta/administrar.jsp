<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,ec.edu.epn.model.vo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/templates/header.jsp"></jsp:include>
	<div class="container">


		<div class="row">
			<div class="col-xs-12">
				<form method="get">

					<div class="form-group">
						<label for="correo">Correo Eletrónico</label> <input name="email"
							type="text" class="form-control" id="email">
					</div>

					<button type="submit" class="btn btn-default">Buscar</button>
				</form>

			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<br> <br>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<table class="table">
					<thead>
						<tr>
							<th>Correo Eletrónico</th>
							<th>Acción</th>

						</tr>
					</thead>
					<tbody>
						<%
							List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
							for (Usuario usr : listaUsuarios) {
						%>
						<tr>
							<td><%=usr.getEmail()%></td>
							<td>
								<form method="get"
									action="${pageContext.request.contextPath}/Cuenta/Modificar">
									<button type="submit" class="btn btn-default"
										value="<%=usr.getEmail()%>" name="emailModificar">
										<span class="glyphicon glyphicon-pencil"
											title="Modificar cuenta"></span>
									</button>
								</form>
								<form method="post"
									action="${pageContext.request.contextPath}/Cuenta/Eliminar">
									<button type="submit" class="btn btn-default"
										value="<%=usr.getEmail()%>" name="emailEliminar">
										<span
									class="glyphicon glyphicon-remove" title="Eliminar cuenta"></span>
									</button>
								</form> 
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>

			</div>
		</div>


	</div>

</body>
</html>