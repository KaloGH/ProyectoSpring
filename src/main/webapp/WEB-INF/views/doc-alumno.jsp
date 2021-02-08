<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<div class="containter">
		<h1>Documentacion del alumno</h1>
		<br> <br>
		<c:choose>
		<c:when test="${errores == null}"></c:when>
		<c:when test="${errores != null}">
		
		<div class="alert alert-danger" role="alert">
  			<strong>¡ Error !</strong> ${errores}
		</div>
		
		</c:when>
		</c:choose>
		
		<table class="table table-striped">
	

	<thead class="thead-dark">
		<th>DNI</th>
		<th>Nombre</th>
		<th>Ciclo</th>
		<th>Curso</th>
	</thead>
	<tbody>
	
	</tbody>
	
	<tr>
		<td>${alumno.getDni()}</td>
		<td>${alumno.getNombre()}</td>
		<td>${alumno.getCiclo()}</td>
		<td>${alumno.getCurso()}</td>
	</tr>
	
	</table>
	<br>
	<span>Si desea añadir nueva documentacion introduzca los datos</span><br><br>
	
	<mvc:form method="post" action="add-docAlumno" enctype="multipart/form-data" modelAttribute="docAlumno">
		<mvc:errors path="*" cssClass="text-warning" />
		

			<mvc:input path="dni" type="hidden" id="dni" class="form-control" value="${alumno.getDni()}"/>
			

				<div class="form-row">
				
					<!-- ID -->
					<div class="col">
						<mvc:label path="id">Id:</mvc:label>
						<mvc:input path="id" type="number" id="id" class="form-control" required="required" readonly="true"/>
						<mvc:errors path="id" cssClass="text-danger"/>
							<div class="row">
								<div class="col">
								<!-- Comentario -->
									<mvc:label path="comentario">Comentario:</mvc:label><br>
										<mvc:textarea class="form-control" path="comentario" rows="3" cols="70" /> 
								</div>
							</div>
					</div>
					
					
					<div class="col">
				
						<mvc:label path="tipo">Tipo:</mvc:label><br>
						<mvc:radiobuttons path="tipo" items="${listaTipos}" element="div"/>
					</div>
				
					<div class="col">				
						<!-- Boton -->
						<input type="submit" value="Añadir" class="btn btn-success"/>
					</div>
			</div>
			
	</mvc:form><br><br>
		
	<table class="table table-striped">
	

		<thead class="thead-dark">
			<th>Id</th>
			<th>Tipo</th>
			<th>Comentario</th>
			
		</thead>
		<tbody>
		
		</tbody>
	
		<c:forEach items="${alumno.getDocAlumno()}" var="documento">
			<tr>
				<td>${documento.getId()}</td>
				<td>${documento.getTipo()}</td>
				<td>${documento.getComentario()}</td>
				<td><a class="btn btn-info" href="add-alumno">Descargar</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
		
	</div>


<%@ include file="../jspf/footer.jspf" %>