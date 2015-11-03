<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/templates/header.jsp"></jsp:include>
    <div class="container">
      <form method="post">
        <div class="form-group">
          <label for="Email">Email</label>
          <input type="email" class="form-control" name="email" id="Email" placeholder="Email" required="true">
        </div>
        <div class="form-group">
          <label for="Password">Contraseña</label>
          <input type="password" class="form-control" name="password" id="Password" placeholder="Password" required="true">
        </div>
        
        
        <button type="submit" class="btn btn-default">Iniciar Sesión</button>
      </form>
    </div>
    
<jsp:include page="/templates/footer.jsp"></jsp:include>