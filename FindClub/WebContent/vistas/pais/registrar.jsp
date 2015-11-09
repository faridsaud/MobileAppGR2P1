<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/templates/header.jsp"></jsp:include>

    <div class="container">
      <form method="post">
        <div class="form-group">
          <label for="pais">País</label>
          <input name="pais" type="text" class="form-control" id="pais" placeholder="Pais" required="true">
        </div>
        <button type="submit" class="btn btn-default">Registrar</button>
      </form>
    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>