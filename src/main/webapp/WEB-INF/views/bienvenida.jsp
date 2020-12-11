<%@ include file="../jspf/header.jspf" %>
<%@ include file="../jspf/menuSuperior.jspf" %>

<div class="container">
	<h1>Bienvenid@ ${usuario.getNickname()}</h1>
	<p>Que nadie se entere que tu password es ' <i>${usuario.getPassword()} </i>'</p>
</div>

<%@ include file="../jspf/footer.jspf" %>