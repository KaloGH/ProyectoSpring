<%@ include file="../jspf/header.jspf"%>
<%@ include file="../jspf/menuSuperior.jspf"%>

<div class="container">

	<p>
		<font color="red">${errores}</font>
	</p>
	<form action="login" method="post">
		<label for="nombre">Introduzca su nombre: </label> <input type="text"
			name="nombre" /> <br>
		<br> <label for="password">Introduzca su contraseña: </label> <input
			type="password" name="password" /> <br>
		<br> <input type="submit" value="Login" />
	</form>
</div>


<%@ include file="../jspf/footer.jspf"%>