<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>
	
	<div class="container">
	<h1>Listado de Modulos</h1>
	<h3>Bienvenido ${usuario.getNombre()}</h3>
	
	<table class="table table-striped">
	<thead class="thead-dark">
		<th><a style="color: white;" href="list-modulo?criterio=">ID</a></th>
		<th><a style="color: white;" href="list-alumno?criterio=nombre">Nombre</a></th>
		<th><a style="color: white;" href="list-alumno?criterio=horas">Horas</a></th>
		<th><a style="color: white;" href="list-alumno?criterio=abv">Abreviatura</a></th>
		<th>Acciones</th>
	</thead>
	<tbody>
	
	</tbody>
	<c:forEach items="${modulos}" var="modulo">
	<tr>
		<td>${modulo.getId()}</td>
		<td>${modulo.getNombre()}</td>
		<td>${modulo.getHoras()}</td>
		<td>${modulo.getAbreviatura()}</td>		
		<td>
		<a class="btn btn-danger" href="del-modulo?id=${modulo.getId()}">Borrar</a>
		</td>
		
	</tr>
	</c:forEach>
	
	</table>

<a class="btn btn-success" href="add-modulo">Añadir modulo</a>

</div>

<%@ include file="../jspf/footer.jspf" %>








