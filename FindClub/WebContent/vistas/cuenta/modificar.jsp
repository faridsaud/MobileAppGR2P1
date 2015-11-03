<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="ec.edu.epn.model.vo.*" %>

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

<jsp:include page="/templates/footer.jsp"></jsp:include>