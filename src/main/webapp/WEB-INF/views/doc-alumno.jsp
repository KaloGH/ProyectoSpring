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
  			<strong>� Error !</strong> ${errores}
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
	<span>Si desea a�adir nueva documentacion introduzca los datos</span><br><br>
	
	<mvc:form method="post" action="doc-alumno" modelAttribute="documento">
		<mvc:errors path="*" cssClass="text-warning" />
		

			<div class="form-row">
			<mvc:input path="dni" type="hidden" id="dni" class="form-control" value="${alumno.getDni()}"/>
			

				<div class="col">
				
					<!-- ID -->
					<div class="form-row">
						<mvc:label path="id">Id:</mvc:label>
						<mvc:input path="id" type="number" id="id" class="form-control" required="required" readonly="true"/>
						<mvc:errors path="id" cssClass="text-danger"/>
					</div>
					
					<div class="form-row">
					<!-- Comentario -->
					
		
						<mvc:label path="comentario">Comentario:</mvc:label>	
							<mvc:textarea path="comentario" rows="3" cols="70" /> &nbsp
					
					</div>

				</div>
					
				<!-- Tipo -->
				<div class="col">
				
					<mvc:label path="tipo">Tipo:</mvc:label><br>
					<mvc:radiobuttons path="tipo" items="${listaTipos}" element="div"/>
				
				</div>
				
				<div class="col">
									
					<!-- Boton -->
					<input type="submit" value="A�adir" class="btn btn-success"/>
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
	
	<c:forEach items="${documentos}" var="documento">
	<tr>
		<td>${documento.getId()}</td>
		<td>${documento.getTipo()}</td>
		<td>${documento.getComentario()}</td>
	</tr>
	</c:forEach>
	
	</table>
		
	</div>


<%@ include file="../jspf/footer.jspf" %>