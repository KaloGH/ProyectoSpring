<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<div class="containter">
		<h1><spring:message code="modulos.explicacion" /></h1>
		<br> <br>
		
		<c:choose>
		<c:when test="${errores == null}"></c:when>
		<c:when test="${errores != null}">
		
		<div class="alert alert-danger" role="alert">
  			<strong> ${errores} </strong> 
		</div>
		
		</c:when>
		</c:choose>
		
		

		<mvc:form method="post" action="add-modulo" modelAttribute="modulo">
			<mvc:hidden path="id"/>
		
			<div class="form-row">

				<div class="form-group col-md-4">

					<!-- Nombre -->
					
					<mvc:label path="nombre"><spring:message code="etiqueta.nombre"/></mvc:label>
					<mvc:input path="nombre" type="text" id="nombre" class="form-control" required="required"/>
					<mvc:errors path="nombre" cssClass="text-danger"/>
					
			
				</div>


				<div class="form-group col-md-5">

					<!-- Horas -->
					
					<mvc:label path="horas"><spring:message code="etiqueta.horas"/></mvc:label>
					<mvc:input path="horas" type="number" id="horas" class="form-control" required="required"/>
					<mvc:errors path="horas" cssClass="text-danger"/>
					
					

				</div>
			</div>

			<div class="form-row">

				<div class="form-group ">

					<!-- Abreviatura -->
					<mvc:label path="abreviatura"><spring:message code="etiqueta.abreviatura"/></mvc:label>
					<mvc:input path="abreviatura" type="text" id="abreviatura" class="form-control" required="required"/>
					<mvc:errors path="abreviatura" cssClass="text-danger"/>
				</div>			
			</div>
			
			
			<input type="submit" value="<spring:message code="boton.anyadir"/>" class="btn btn-success"/>
<%-- 			<mvc:input path="envia" type="submit" value="Añadir" class="btn btn-success"/> --%>

		</mvc:form>


	</div>


<%@ include file="../jspf/footer.jspf" %>