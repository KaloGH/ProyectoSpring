<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">

	<p class="text-danger">
		${errores}
	</p>
	
	<mvc:form method="post" action="login" modelAttribute="usuario">
		
		<mvc:label path="nickname">Nombre de Usuario: </mvc:label>
		<mvc:input path="nickname"/> </br>
		<mvc:errors path="nickname" cssClass="text-danger"/>
		
		 <br> <br> 
		
		<mvc:label path="password">Contraseña: </mvc:label>
		<mvc:password path="password"/></br>
		<mvc:errors path="password" cssClass="text-danger"/> 
				<br> <input type="submit" value="Login" />

	</mvc:form>
	
	
</div>


<%@ include file="../jspf/footer.jspf"%>