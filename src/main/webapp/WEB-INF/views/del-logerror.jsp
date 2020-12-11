<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

	<div class="container">
	<h1>Eliminar error</h1>
	<a class="btn btn-success" href="list-logerror">Volver atras</a>
	
	<form action="del-logerror" method="POST">
	
	<label for="id">ID</label> <br> 
	<input type="number" name="id" id="id" class="form-control" readonly="readonly" value="${logError.getId()}"> <br>
	
	<label for="id">Tipo</label> <br> 
	<input type="text" name="tipo" id="tipo" class="form-control" readonly="readonly" value="${logError.getTipo()}"> <br>
	
	<label for="id">Explicacion</label> <br> 
	<input type="text" name="info" id="info" class="form-control" readonly="readonly" value="${logError.getInfo()}"> <br>
	
	<input type="submit" class="btn btn-danger" value="Borrar">
	</form>
	
	
</div>

<%@ include file="../jspf/footer.jspf" %>
