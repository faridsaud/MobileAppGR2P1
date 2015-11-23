<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <!-- Latest compiled and minified CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<title>Find your Club!</title>
 
</head>
<body>

    <div class="row">

      <div class="col-xs-12">
        <nav class="navbar navbar-inverse">

          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/Home">Home</a>

          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav" >


            <li><a href="${pageContext.request.contextPath}/Discoteca/Home">Discotecas</a></li>
            <li><a href="${pageContext.request.contextPath}/Fiesta/Home">Fiesta</a></li>

            <li><a href="${pageContext.request.contextPath}/Musica/Home">Música</a></li>

            <li><a href="${pageContext.request.contextPath}/Pais/Home">País</a></li>
            <li><a href="${pageContext.request.contextPath}/Ciudad/Home">Ciudad</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
            <li><a href="${pageContext.request.contextPath}/Cuenta/Home">Cuenta</a></li>


            </ul>
          </div><!-- /.navbar-collapse -->

        </nav>
      </div>

    </div>

