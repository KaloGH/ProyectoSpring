<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<div class="containter">
		<h1><spring:message code="alumno.documentacion.titulo"/></h1>
		<br> <br>
		<c:choose>
		<c:when test="${errores == null}"></c:when>
		<c:when test="${errores != null}">
		
		<div class="alert alert-danger" role="alert">
  			<strong>
  			${errores}
  			</strong> 
		</div>
		
		</c:when>
		</c:choose>
		
		<table class="table table-striped">
	

	<thead class="thead-dark">
		<th><spring:message code="etiqueta.dni"/></th>
		<th><spring:message code="etiqueta.nombre"/></th>
		<th><spring:message code="etiqueta.ciclo"/></th>
		<th><spring:message code="etiqueta.curso"/></th>
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
	<span><spring:message code="alumno.documentacion.explicacion"/></span><br><br>
	
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
									<mvc:label path="comentario"><spring:message code="etiqueta.comentario"/>:</mvc:label><br>
										<mvc:textarea class="form-control" path="comentario" rows="3" cols="70" /> 
								</div>
							</div>
							<br>
							<div class="row">
								<mvc:input path="fichero" type="file"/><form:errors path="fichero"/><br>
								<mvc:errors path="fichero" cssClass="text-warning"/><br>
							</div>
					</div>
					
					
					<div class="col">
				
						<mvc:label path="tipo"><spring:message code="etiqueta.tipo"/>:</mvc:label><br>
						<mvc:radiobuttons path="tipo" items="${listaTipos}" element="div"/>
					</div>
				
					<div class="col">				
						<!-- Boton -->
						
						<button type="submit" class="btn btn-success"><spring:message code="boton.anyadir"/> &nbsp;<i class="fas fa-plus-circle"></i></button>
					</div>
			</div>
			
	</mvc:form><br><br>
		
	<table class="table table-striped">
	

		<thead class="thead-dark">
			<th>Id</th>
			<th><spring:message code="etiqueta.tipo"/></th>
			<th><spring:message code="etiqueta.comentario"/></th>
			<th><spring:message code="etiqueta.accion"/></th>
			
		</thead>
		<tbody>
		
		</tbody>
	
		<c:forEach items="${alumno.getDocAlumno()}" var="documento">
			<tr>
				<td>${documento.getId()}</td>
				<td>${documento.getTipo()}</td>
				<td>${documento.getComentario()}</td>
				<td><a class="btn btn-info" href="descargar-docAlumno/${documento.getDni()}/${documento.getId()}"><spring:message code="boton.descargar"/> <i class="far fa-arrow-alt-circle-down"></i></a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
		
	</div>


<%@ include file="../jspf/footer.jspf" %>