<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

	<div class="container">
	<h1>Listado de Errores</h1> <br>
	
	
	
	<form action="list-logerror" method="GET">
	
		<div class="form-row">
			<div class="form-group col-md-4">
		
				<select name="campoFiltro" required>
					<option>ID</option>
					<option>Tipo</option>
					<option>Explicacion</option>
				</select>
		
			</div>
			<div class="form-group col-md-6">
		
				<label for="textoFiltro">Como:</label>
				<input type="text" name="textoFiltro" id="textoFiltro">
				
			</div>
			
			<div class="form-group col-md-1">
		
				<input type="submit" class="btn btn-success" value="Filtrar">
				
			</div>
			
		</div>
	
	</form>
	
	<table class="table table-striped">
	<thead class="thead-dark">
		<th><a style="color: white;" href="list-logerror?ordenar=id">ID</a></th>
		<th><a style="color: white;" href="list-logerror?ordenar=tipo">Tipo</a></th>
		<th><a style="color: white;" href="list-logerror?ordenar=info">Explicacion</a></th>
		<th>Accion</th>
	</thead>
	<tbody>
	
	</tbody>
	<c:forEach items="${listaErrores}" var="logerror">
	<tr>
		<td>${logerror.getId()}</td>
		<td>${logerror.getTipo()}</td>
		<td>${logerror.getInfo()}</td>
		<td><a class="btn btn-danger" href="del-logerror?id=${logerror.getId()}">Borrar</a></td>
		
	</tr>
	</c:forEach>
	
	</table>
</div>

<%@ include file="../jspf/footer.jspf" %>
