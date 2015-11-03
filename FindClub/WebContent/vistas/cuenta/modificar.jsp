<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="ec.edu.epn.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/templates/header.jsp"></jsp:include>
    <div class="container">
    <%
    	Usuario usrModificar=new Usuario();
		try{
		usrModificar=(Usuario)request.getSession().getAttribute("usuarioModificar");
		
		}catch(Exception e){
			
		}
		if(usrModificar.getNombre()==null)
			usrModificar.setNombre("");

		if(usrModificar.getApellido()==null)
			usrModificar.setApellido("");

		if(usrModificar.getEmail()==null)
			usrModificar.setEmail("");

		if(usrModificar.getPassword()==null)
			usrModificar.setPassword("");
	%>
      <form method="post">
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" placeholder="Email" name="email" readonly="true" value="<%=usrModificar.getEmail()%>">
        </div>
        <div class="form-group">
          <label for="password">Contraseña</label>
          <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="true" value="<%=usrModificar.getPassword()%>">
        </div>

        <div class="form-group">
          <label for="nombre1">Nombre</label>
          <input type="text" class="form-control" id="nombre1" name="nombre1" placeholder="Nombre"  required="true" value="<%=usrModificar.getNombre()%>">
        </div>

        <div class="form-group">
          <label for="apellido1">Apellido</label>
          <input type="text" class="form-control" id="apellido1" name="apellido1" placeholder="Apellido" value="<%=usrModificar.getApellido()%>">
        </div>
        <button type="submit" class="btn btn-default">Actualizar</button>
      </form>
    </div>
</body>
</html>