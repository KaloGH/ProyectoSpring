<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

	<div class="containter">
		<h1><spring:message code="alumno.nuevo.titulo"/></h1>
		<br>
		<h4><spring:message code="alumno.nuevo.explicacion"/></h4>
		<br> <br>
		
		<c:choose>
		<c:when test="${errores == null}"></c:when>
		<c:when test="${errores != null}">
		
		<div class="alert alert-danger" role="alert">
  			<strong>
  			${errores}
  			</strong> 
		</div>
		
		</c:when>
		</c:choose>
		
		

		<mvc:form method="post" action="add-alumno" modelAttribute="alumnoEdit">
		<mvc:errors path="*" cssClass="text-warning" />
		
			<div class="form-row">

				<div class="form-group col-md-4">

					<!-- DNI -->
					
					<mvc:label path="dni"><spring:message code="etiqueta.dni"/></mvc:label>
					<mvc:input path="dni" type="text" id="dni" class="form-control" required="required"/>
					<mvc:errors path="dni" cssClass="text-danger"/>
					
			
				</div>


				<div class="form-group col-md-5">

					<!-- Nombre -->
					
					<mvc:label path="nombre"><spring:message code="etiqueta.nombre"/></mvc:label>
					<mvc:input path="nombre" type="text" id="nombre" class="form-control" required="required"/>
					<mvc:errors path="nombre" cssClass="text-danger"/>
					
					

				</div>
			</div>

			<div class="form-row">

				<div class="form-group ">

					<!-- Edad -->
					<mvc:label path="edad">
						<spring:message code="etiqueta.edad"/>
					</mvc:label>
					<mvc:input path="edad" type="number" id="edad" class="form-control" required="required"/>
					<mvc:errors path="edad" cssClass="text-danger"/>

				</div>
				<div class="form-group ">

					<!-- Modulo -->
					<mvc:label path="ciclo"><spring:message code="etiqueta.ciclo"/></mvc:label>
					<mvc:input path="ciclo" type="text" id="ciclo" class="form-control" required="required"/>
					<mvc:errors path="ciclo" cssClass="text-danger"/>

				</div>

				<div class="form-group ">

					<!-- Curso -->
					<mvc:label path="curso"><spring:message code="etiqueta.curso"/></mvc:label>
					<mvc:input path="curso" type="number" id="curso" class="form-control" required="required"/>
					<mvc:errors path="curso" cssClass="text-danger"/>

				</div>

			</div>
			
			<div class=form-row>
			
				<div class="col">
					<mvc:label path="erasmus"><spring:message code="etiqueta.erasmus"/></mvc:label><br/>
					<mvc:checkbox path="erasmus"/>
				</div>
				
				<div class="col">
					<mvc:label path="interesadoEn"><spring:message code="etiqueta.interesado"/>:</mvc:label><br/>
					<mvc:checkboxes items="${interesadoEnLista}" path="interesadoEn" element="div"/>
<%-- 					<mvc:checkbox path="interesadoEn" value="Backend"/> Backend --%>
<%-- 					<mvc:checkbox path="interesadoEn" value="Frontend"/> Frontend --%>
				</div>
				
				<div class="col">
					<mvc:label path="lenguajeFavorito"><spring:message code="etiqueta.lenguaje.favorito"/></mvc:label><br/>
					<mvc:checkbox path="lenguajeFavorito" value="Java"/>&nbsp;Java
				</div>
				

			
				<div class="col">
				
					<mvc:label path="genero"><spring:message code="etiqueta.genero"/>:</mvc:label><br/>
					<mvc:radiobuttons path="genero" items="${generoLista}" element="div"/>
				
				</div>	
				<div class="col">
				
<!-- 				Aqui Va el resto -->
					<mvc:label path="horario"><spring:message code="etiqueta.horario"/>:</mvc:label>
					<mvc:select path="horario">
						<mvc:options path="horario" items="${horarioLista}" element="div"/>
					</mvc:select>
				
				</div>
				


				<div class="col">
				<mvc:label path="pais"><spring:message code="etiqueta.pais"/></mvc:label>
				<mvc:select path="pais">
					<mvc:option value="-" label="Ninguno" />
					<mvc:options path="pais" items="${paisLista}" element="div"/>
				</mvc:select>
				</div>
				
				<div class="col">
					<mvc:label path="matriculadoEn"><spring:message code="etiqueta.matriculado"/>: </mvc:label></br>
					<mvc:select path="matriculadoEn" items="${moduloLista}" itemValue="id" itemLabel="nombre" ></mvc:select>		
				</div>
			</div>
			
			<div class="form-row">
				<div class="col">
					<mvc:label path="hobbies"><spring:message code="etiqueta.hobbies"/>:</mvc:label></br>
					<mvc:textarea path="hobbies" rows="3" cols="70" />
				</div>
			</div>
			
			<input type="submit" value="<spring:message code="boton.anyadir"/>" class="btn btn-success"/>
<%-- 			<mvc:input path="envia" type="submit" value="Añadir" class="btn btn-success"/> --%>

		</mvc:form>


	</div>


<%@ include file="../jspf/footer.jspf" %>