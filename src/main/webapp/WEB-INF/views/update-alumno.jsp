<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<div class="containter">
		<h1>Modificar Alumno</h1>
		<br>
		<h4>Modifica los datos del alumno.</h4>
		<br> <br>
		
		<c:choose>
		<c:when test="${errores == null}"></c:when>
		<c:when test="${errores != null}">
		
		<div class="alert alert-danger" role="alert">
  			<strong>¡ Error !</strong> ${errores}
		</div>
		
		</c:when>
		</c:choose>
		
		

		<mvc:form method="post" action="update-alumno" modelAttribute="alumno">
		
			<div class="form-row">

				<div class="form-group col-md-4">

					<!-- DNI -->
					
					<mvc:label path="dni">DNI</mvc:label>
					<mvc:input path="dni" type="text" id="dni" class="form-control" required="required" readonly="true"/>
					<mvc:errors path="dni" cssClass="text-danger"/>
					
			
				</div>


				<div class="form-group col-md-5">

					<!-- Nombre -->
					
					<mvc:label path="nombre">Nombre</mvc:label>
					<mvc:input path="nombre" type="text" id="nombre" class="form-control" required="required"/>
					<mvc:errors path="nombre" cssClass="text-danger"/>
					
					

				</div>
			</div>

			<div class="form-row">

				<div class="form-group ">

					<!-- Edad -->
					<mvc:label path="edad">Edad</mvc:label>
					<mvc:input path="edad" type="number" id="edad" class="form-control" required="required"/>
					<mvc:errors path="edad" cssClass="text-danger"/>

				</div>
				<div class="form-group ">

					<!-- Modulo -->
					<mvc:label path="ciclo">Modulo</mvc:label>
					<mvc:input path="ciclo" type="text" id="ciclo" class="form-control" required="required"/>
					<mvc:errors path="ciclo" cssClass="text-danger"/>

				</div>

				<div class="form-group ">

					<!-- Curso -->
					<mvc:label path="curso">Curso</mvc:label>
					<mvc:input path="curso" type="number" id="curso" class="form-control" required="required"/>
					<mvc:errors path="curso" cssClass="text-danger"/>

				</div>




			</div>
			<mvc:hidden path="user"/>
			<mvc:hidden path="ts"/>
			<input type="submit" value="Modificar" class="btn btn-success"/>
<%-- 			<mvc:input path="envia" type="submit" value="Añadir" class="btn btn-success"/> --%>

		</mvc:form>


	</div>


<%@ include file="../jspf/footer.jspf" %>