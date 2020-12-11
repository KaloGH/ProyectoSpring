<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

	<div class="container">
	<h1>Eliminar modulo</h1>
	<a class="btn btn-success" href="list-modulo">Volver atras</a>
	
	<form action="del-modulo" method="POST">
	
	<label for="id">ID</label> <br> 
	<input type="number" name="id" id="id" class="form-control" readonly="readonly" value="${modulo.getId()}"> <br>
	
	<label for="id">Nombre</label> <br> 
	<input type="text" name="nombre" id="nombre" class="form-control" readonly="readonly" value="${modulo.getNombre()}"> <br>
	
	<label for="id">Horas</label> <br> 
	<input type="text" name="horas" id="horas" class="form-control" readonly="readonly" value="${modulo.getHoras()}"> <br>
	
	<label for="id">Abreviatura</label> <br> 
	<input type="text" name="abreviatura" id="abreviatura" class="form-control" readonly="readonly" value="${modulo.getAbreviatura()}"> <br>
	
	<input type="submit" class="btn btn-danger" value="Borrar">
	</form>
	
	
</div>

<%@ include file="../jspf/footer.jspf" %>
