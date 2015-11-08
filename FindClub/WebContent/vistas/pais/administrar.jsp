<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, ec.edu.epn.model.vo.Pais, ec.edu.epn.model.vo.Usuario"%>

<jsp:include page="/templates/header.jsp"></jsp:include>

<div class="container">

      <div class="row">
        <div class="col-xs-12">
          <form method="post">

            <div class="form-group">
              <label for="pais">País</label>
              <input name="pais" type="text" class="form-control" id="pais" >
            </div>

            <button type="submit" class="btn btn-default">Buscar</button>
          </form>

        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <br><br>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <table class="table">
            <thead>
              <tr>
                <th>País</th>
                <th>Acción</th>
              </tr>
            </thead>
            
            <tbody>
              <%
              		Usuario usrIniciado = new Usuario();
      				boolean redireccion = true;
            		String referenciaModificar = "#";
            		String referenciaEliminar = "#";
            		
      				try {
      					usrIniciado = (Usuario) request.getSession().getAttribute("usuarioActivo");
      					if (usrIniciado.isEstado() == true) {
      						redireccion = false;
      					}
      				} catch (Exception e) {
      					System.out.println("Error obteniendo usuario");
      				}	
		      		if (redireccion == true) {
		      			getServletConfig().getServletContext().getRequestDispatcher("/Pais/Home").forward(request, response);
		      		} else if (usrIniciado.isAdmin() == true) {
		      			referenciaModificar = "Modificar";
		      			referenciaEliminar = "Eliminar";
		      		}
              
              		List<Pais> listaPais = (List<Pais>) request.getAttribute("listaPais");
            		try{
            			for (Pais p : listaPais){
              %>
              		<tr>
              		
              			<td><%=p.getNombrePais()%></td>	
              			<td>
							<form method="get"
								action="<%=referenciaModificar%>">
								<button type="submit" class="btn btn-default"
									value="<%=p.getNombrePais()%>" name="paisModificar">
									<span class="glyphicon glyphicon-pencil" title="Modificar país"></span>
								</button>
							</form>
							<form method="post"
								action="<%=referenciaEliminar%>">
								<button type="submit" class="btn btn-default"
									value="<%=p.getNombrePais()%>" name="paisEliminar">
									<span
								class="glyphicon glyphicon-remove" title="Eliminar país"></span>
								</button>
							</form> 
						</td>
              		</tr>
              <%
            			}
            		}catch(Exception e){
            			
            		}
              %>
            </tbody>
          </table>
        </div>
      </div>
    </div>

<jsp:include page="/templates/footer.jsp"></jsp:include>