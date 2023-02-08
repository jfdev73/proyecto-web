<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Productos</title>
</head>
<body>
	<h1>Listado de productos</h1>
	<c:if test="${username.isPresent()}">
	<div>
		Hola
		<c:out 	value="${username.get()}" />, bienvenido!
	</div>
	<div>
		<p>
			<a href="<c:out value="${pageContext.request.contextPath }" />/productos/form">Crear[+]</a>
		</p>
	</div>
	</c:if>
	<table>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Categoria</th>
			<c:if test="${username.present}">
			<th>Precio</th>
			<th>Agregar</th>
			<th></th>
			<th></th>
			</c:if>

		</tr>
		
		<c:forEach items= "${productos }" var="p">
		<tr>

			<td>${p.id }</td>
			<td>${p.nombre }</td>
			<td>${p.categoria.categoria }</td>
			<c:if test="${username.present}">
			<td>${p.precio }</td>
			<td><a
				href="${pageContext.request.contextPath }/carro/agregar?id=${p.id }">Agregar
					al carro</a></td>
			<td><a
				href="${pageContext.request.contextPath }/productos/form?id=${p.id } ">Editar</a></td>
				<td><a onclick="return confirm('esta seguro que desea eliminar?');"
        href="${pageContext.request.contextPath }/productos/eliminar?id=<c:out value="${p.id }" />">eliminar</a></td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
</body>
</html>