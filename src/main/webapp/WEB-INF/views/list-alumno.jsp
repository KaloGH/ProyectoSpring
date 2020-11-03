<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>
	
	<div class="container">
	<h1>Listado de Alumnos</h1>
	<h3>Bienvenido ${nombre}</h3>
	
	<table class="table table-striped">
	<thead class="thead-dark">
		<th><a style="color: white;" href="/list-alumno.do?ordenar=Dni">DNI</a></th>
		<th><a style="color: white;" href="/list-alumno.do?ordenar=Nombre">Nombre</a></th>
		<th><a style="color: white;" href="/list-alumno.do?ordenar=Edad">Edad</a></th>
		<th><a style="color: white;" href="/list-alumno.do?ordenar=Ciclo">Ciclo</a></th>
		<th><a style="color: white;" href="/list-alumno.do?ordenar=Curso">Curso</a></th>
		<th>Accion</th>
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
		<td><a class="btn btn-danger" href="/del-alumno.do?dni=${alumno.getDni()}">Borrar</a></td>
		
	</tr>
		<!--  <li>${alumno.getNombre()} <a href="/del-alumno.do?dni=${alumno.getDni()}">Borrar</a></li>   -->
	</c:forEach>
	
	</table>

<a class="btn btn-success" href="/add-alumno">Añadir alumno</a>

</div>

<%@ include file="../jspf/footer.jspf" %>








