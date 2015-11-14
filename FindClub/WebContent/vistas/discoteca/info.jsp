<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="/templates/header.jsp"></jsp:include>
<div class="container">
      <form method="post">
        <div class="form-group">
          <label for="nombre">Nombre</label>
          <input name="nombre" type="text" class="form-control" id="nombre" readonly="true">
        </div>

          <div class="form-group">
            <label for="pais">País</label>
            <input name="pais" type="text" class="form-control" id="pais" readonly="true">
          </div>

            <div class="form-group">
              <label for="ciudad">Ciudad</label>
              <input name="ciudad" type="text" class="form-control" id="ciudad" readonly="true">
            </div>


            <div class="form-group">
              <label for="tipoMusica">Tipo de Música</label>
              <input name="tipoMusica" type="text" class="form-control" id="tipoMusica" readonly="true">
            </div>


        <div class="form-group">
          <label>Tragos</label>
          <div class="checkbox">
            <label>
              <input type="checkbox" value="fuerte" name="tipoTrago" disabled="true" checked="true">
              Fuerte
            </label>
            <label>
              <input type="checkbox" value="cocteles" name="tipoTrago" disabled="true">
              Cocteles
            </label>
            <label>
              <input type="checkbox" value="cerveza" name="tipoTrago" disabled="true">
              Cervezas
            </label>

            <label>
              <input type="checkbox" value="noAlcoholicas" name="tipoTrago" disabled="true">
              Bebidas no alcohólicas
            </label>
          </div>
        </div>


        <div class="form-group">
          <label for="descripcion">Descripción</label>
          <textarea name="descripcion" class="form-control" rows="5" id="descripcion" placeholder="dirección, tipo de vestimenta, etc" readonly="true"></textarea>
        </div>
      </form>
 </div>
<jsp:include page="/templates/footer.jsp"></jsp:include>