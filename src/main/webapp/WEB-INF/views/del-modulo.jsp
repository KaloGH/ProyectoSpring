<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

	<div class="container">
	<br>
	<a class="btn btn-success" href="list-modulo"><spring:message code="boton.volver"/></a>
	
	<form action="del-modulo" method="POST">
	
	<label for="id">ID</label> <br> 
	<input type="number" name="id" id="id" class="form-control" readonly="readonly" value="${modulo.getId()}"> <br>
	
	<label for="nombre"><spring:message code="etiqueta.nombre"/></label> <br> 
	<input type="text" name="nombre" id="nombre" class="form-control" readonly="readonly" value="${modulo.getNombre()}"> <br>
	
	<label for="horas"><spring:message code="etiqueta.horas"/></label> <br> 
	<input type="text" name="horas" id="horas" class="form-control" readonly="readonly" value="${modulo.getHoras()}"> <br>
	
	<label for="abreviatura"><spring:message code="etiqueta.abreviatura"/></label> <br> 
	<input type="text" name="abreviatura" id="abreviatura" class="form-control" readonly="readonly" value="${modulo.getAbreviatura()}"> <br>
	
	<input type="submit" class="btn btn-danger" value="<spring:message code="boton.borrar"/>">
	</form>
	
	
</div>

<%@ include file="../jspf/footer.jspf" %>
