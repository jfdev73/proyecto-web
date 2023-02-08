<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<jsp:include page="layout/header.jsp" />
<h3 class="my-3">Bienvenido</h3>
<ul class="list-group">
	<li class="list-group-item active">Menu de Opciones</li>
	<li class="list-group-item"><a class="list-group-item list-group-item-info" href="/web-session/productos">mostrar productos html</a></li>
	<li class="list-group-item"><a class="list-group-item list-group-item-info" href="/web-session/login.html">login</a></li>
	<li class="list-group-item"><a class="list-group-item list-group-item-info" href="/web-session/logout">logout</a></li>
	<li class="list-group-item"><a class="list-group-item list-group-item-info" href="/web-session/carro/ver">Ver Carro</a></li>
</ul>

<jsp:include page="layout/footer.jsp" />