<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>
	
	<div class="container">
	<h1><spring:message code="alumnos.titulo" /></h1>
	<h3><spring:message code="home.bienvenida" />&nbsp;${usuario.getNickname()}</h3>
	
	<table class="table table-striped">
	
	<mvc:form action="filtro-avanzado-alumnos" method="post" modelAttribute="filtroAvanzadoAlumno">
	
		<div class="form-row">
		
			<div class="col">
				<mvc:label path="dni"><spring:message code="etiqueta.dni"/>:</mvc:label>
			</div>
			
			<div class="col">
				<mvc:label path="ciclo"><spring:message code="etiqueta.ciclo"/>:</mvc:label>
			</div>
			
			<div class="col">
				<mvc:label path="horario"><spring:message code="etiqueta.horario"/>:</mvc:label>
			</div>
			
			<div class="col">
				&nbsp;
			</div>
		
		</div>
		
		<div class="form-row">
			
			<div class="col">
				<mvc:select path="dni">
					<mvc:option value="-" label="Ninguno"/>
					<mvc:options items="${dniListaAlumnos}"/>
				</mvc:select>
			</div>
			
			<div class="col">
				<mvc:select path="ciclo">
					<mvc:option value="-" label="Ninguno"/>
					<mvc:options items="${cicloListaAlumnos}"/>
				</mvc:select>
			</div>
			
			<div class="col">
				<mvc:select path="horario">
					<mvc:option value="-" label="Ninguno"/>
					<mvc:options items="${horarioListaAlumnos}"/>
				</mvc:select>
			</div>
			
			<div class="col">
				<button type="submit" class="btn btn-success" ><spring:message code="boton.filtrar"/> &nbsp; <i class="fas fa-search"></i></button>
			</div>
			
		</div>
		<br>
					
	</mvc:form>

	<thead class="thead-dark">
		<th><a style="color: white;" href="list-alumno?ordenar=Dni"><spring:message code="etiqueta.dni"/></a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Nombre"><spring:message code="etiqueta.nombre"/></a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Edad"><spring:message code="etiqueta.edad"/></a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Ciclo"><spring:message code="etiqueta.ciclo"/></a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Curso"><spring:message code="etiqueta.curso"/></a></th>
		<th><a style="color: white;" href="#"><spring:message code="etiqueta.erasmus"/></a></th>
		<th><spring:message code="etiqueta.accion"/></th>
	</thead>
	<tbody>
	
	</tbody>
	<c:forEach items="${alumnos}" var="alumno">
	
	<tr>
		<td>${alumno.getDni()}</td>
		<td>${alumno.getNombre()}</td>
		<td>${alumno.getEdad()}</td>
		<td>${alumno.getCiclo()}</td>
		<td>${alumno.getCurso()}</td>
		<td> <input type="checkbox" readonly disabled 
		<c:if test="${alumno.isErasmus()}">checked</c:if> <%-- If si participa en Erasmus o no. --%>
		/></td>
		
		<td>
		<a class="btn btn-success" href="update-alumno?dni=${alumno.getDni()}"><spring:message code="boton.modificar"/> &nbsp; <i class="fas fa-edit"></i></a>
		<a class="btn btn-danger" href="del-alumno?dni=${alumno.getDni()}"><spring:message code="boton.borrar"/> &nbsp; <i class="fas fa-trash-alt"></i></a>
		<a class="btn btn-warning" href="docs-alumno?dni=${alumno.getDni()}"><spring:message code="boton.documentacion"/> &nbsp;<i class="fas fa-file-alt"></i></a>
		
		</td>
		
	</tr>
		<!--  <li>${alumno.getNombre()} <a href="/del-alumno.do?dni=${alumno.getDni()}">Borrar</a></li>   -->
	</c:forEach>
	
	</table>

<a class="btn btn-success" href="add-alumno"><spring:message code="boton.anyadir"/> &nbsp; <i class="far fa-plus-square"></i></a>

</div>

<%@ include file="../jspf/footer.jspf" %>








