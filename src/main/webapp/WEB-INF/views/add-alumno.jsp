<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<div class="containter">
		<h1>A�adir nuevo Alumno</h1>
		<br>
		<h4>Introduzca los datos del nuevo alumno.</h4>
		<br> <br>
		
		<c:choose>
		<c:when test="${errores == null}"></c:when>
		<c:when test="${errores != null}">
		
		<div class="alert alert-danger" role="alert">
  			<strong>� Error !</strong> ${errores}
		</div>
		
		</c:when>
		</c:choose>
		
		

		<mvc:form method="post" action="add-alumno" modelAttribute="alumno">
		
			<div class="form-row">

				<div class="form-group col-md-4">

					<!-- DNI -->
					
					<mvc:label path="dni">DNI</mvc:label>
					<mvc:input path="dni" type="text" id="dni" class="form-control" required="required"/>
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
			
			<div class=form-row>
			
				<div class="col">
					<mvc:label path="erasmus">Alumno en Erasmus</mvc:label><br/>
					<mvc:checkbox path="erasmus"/>
				</div>
				
				<div class="col">
					<mvc:label path="interesadoEn">Interesado en:</mvc:label><br/>
					<mvc:checkboxes items="${interesadoEnLista}" path="interesadoEn" element="div"/>
<%-- 					<mvc:checkbox path="interesadoEn" value="Backend"/> Backend --%>
<%-- 					<mvc:checkbox path="interesadoEn" value="Frontend"/> Frontend --%>
				</div>
				
				<div class="col">
					<mvc:label path="lenguajeFavorito">Lenguaje favorito</mvc:label><br/>
					<mvc:checkbox path="lenguajeFavorito" value="Java"/>&nbsp;Java
				</div>
				
			</div>
			
			<div class=form-row>
			
				<div class="col">
				
					<mvc:label path="genero">Genero:</mvc:label><br/>
					<mvc:radiobuttons path="genero" items="${generoLista}" element="div"/>
				
				</div>	
				<div class="col">
				
<!-- 				Aqui Va el resto -->
					<mvc:label path="horario">Horario:</mvc:label>
					<mvc:select path="horario">
						<mvc:options path="horario" items="${horarioLista}" element="div"/>
					</mvc:select>
				
				</div>
				
			</div>
			
			<input type="submit" value="A�adir" class="btn btn-success"/>
<%-- 			<mvc:input path="envia" type="submit" value="A�adir" class="btn btn-success"/> --%>

		</mvc:form>


	</div>


<%@ include file="../jspf/footer.jspf" %>