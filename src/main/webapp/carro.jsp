<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carro de Compras</title>
</head>
<body>
	<h1>Carro de compras</h1>

	<c:if test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}">
	<p>Lo sentimos no hay productos en el carro de compras !</p>
	</c:if>
	
	
	<c:if test="${sessionScope.carro != null && !sessionScope.carro.items.isEmpty()}">
	<p>Lo sentimos no hay productos en el carro de compras !</p>
	
	<form name="formcarro"
		action="${pageContext.request.contextPath }/carro/actualizar" method="post">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Precio</th>
					<th>Cantidad</th>
					<th>Total</th>
					<th>Borrar</th>
				</tr>
			</thead>
			<c:forEach items="${sessionScope.carro.items }" var="item">
			<tbody>
				<tr>
					<td>${ item.producto.id}</td>
					<td>${ item.producto.nombre}</td>
					<td>${ item.producto.precio}</td>
					<td><input type="number" size="4"
							name="cant_${ item.producto.id}"
							value="${ item.cantidad}" /></td>
					<td>${ item.importe}</td>
					<td><input type="checkbox"
							value="${ item.producto.id}" name="deleteProductos" /></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="4" style="text-align: right;">Total:</td>
					<td>${sessionScope.carro.total}</td>
				</tr>
			</tbody>
		</table>
		<a href="javascript:document.formcarro.submit();">Actualizar</a>
	</form>
	</c:if>
	<p>
		<a href="<%=request.getContextPath()%>/productos">Seguir Comprando</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/home.html">Volver</a>
	</p>
</body>
</html>