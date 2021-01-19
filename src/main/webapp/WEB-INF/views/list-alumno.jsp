<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>
	
	<div class="container">
	<h1>Listado de Alumnos</h1>
	<h3>Bienvenido ${nombre}</h3>
	
	<table class="table table-striped">
	
	<mvc:form action="filtro-avanzado-alumnos" method="post" modelAttribute="filtroAvanzadoAlumno">
	
		<div class="form-row">
		
			<div class="col">
				<mvc:label path="dni">DNI:</mvc:label>
			</div>
			
			<div class="col">
				<mvc:label path="ciclo">Ciclo:</mvc:label>
			</div>
			
			<div class="col">
				<mvc:label path="horario">Horario:</mvc:label>
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
				<input type="submit" class="btn btn-success" value="Filtrar">
			</div>
			
		</div>
					
	</mvc:form>

	<thead class="thead-dark">
		<th><a style="color: white;" href="list-alumno?ordenar=Dni">DNI</a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Nombre">Nombre</a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Edad">Edad</a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Ciclo">Ciclo</a></th>
		<th><a style="color: white;" href="list-alumno?ordenar=Curso">Curso</a></th>
		<th><a style="color: white;" href="#">Erasmus</a></th>
		<th>Acciones</th>
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
		<a class="btn btn-success" href="update-alumno?dni=${alumno.getDni()}">Modificar</a>
		<a class="btn btn-danger" href="del-alumno?dni=${alumno.getDni()}">Borrar</a>
		<a class="btn btn-warning" href="docs-alumno?dni=${alumno.getDni()}">Documentacion</a>
		
		</td>
		
	</tr>
		<!--  <li>${alumno.getNombre()} <a href="/del-alumno.do?dni=${alumno.getDni()}">Borrar</a></li>   -->
	</c:forEach>
	
	</table>

<a class="btn btn-success" href="add-alumno">Añadir alumno</a>

</div>

<%@ include file="../jspf/footer.jspf" %>








