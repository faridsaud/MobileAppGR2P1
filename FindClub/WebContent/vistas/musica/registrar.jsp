<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:include page="/templates/header.jsp"></jsp:include>

    <div class="container">
      <form method="post">

        <div class="form-group">
          <label for="tipoMusica">Tipo de Música</label>
          <input name="tipoMusica" type="text" class="form-control" id="tipoMusica" required="true">
        </div>


        <div class="form-group">
          <label for="descripcion">Descripción (Opcional)</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="Explicar el tipo de música" required="true"></textarea>
        </div>

        <button type="submit" class="btn btn-default">Registrar</button>
      </form>

    </div>


<jsp:include page="/templates/footer.jsp"></jsp:include>