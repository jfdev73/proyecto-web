<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Fromulario de Productos</title>
</head>
<body>
	<h1>${producto.id !=null && producto.id >0 ? "Editar Producto": "Crear Producto"}</h1>

	<form action="${pageContext.request.contextPath }/productos/form"
		method="post">
		<div>
			<label for="nombre">Nombre:</label>
			<div>
				<input type="text" name="nombre" id="nombre" value="${producto.nombre }">
			</div>

		</div>

		<c:if test="${errores!=null && errores.containsKey('nombre') }">
		<div style="color: red;"> ${errores.nombre}</div>
		</c:if>

		<div>
			<label for="precio">Precio:</label>
			<div>
				<input type="number" name="precio" id="precio" value="${producto.precio }">
			</div>

		</div>
		
		<c:if test="${errores!=null && errores.containsKey('precio') }">
		<div style="color: red;"> ${errores.precio}</div>
		</c:if>

		<div>
			<label for="sku">Sku:</label>
			<div>
				<input type="text" name="sku" id="sku" value="${producto.sku }">
			</div>

		</div>
		
		<c:if test="${errores!=null && errores.containsKey('sku') }">
		<div style="color: red;"> ${errores.sku}</div>
		</c:if>
		

		<div>
			<label for="sku">Categoria:</label>
			<div>
				<select name="categoria" id="categoria">
					<option value="">--- seleccionar ---</option>
					
					
					
					<c:forEach items="${categorias}" var="c">
                	<option value="${c.id}" ${c.id.equals(producto.categoria.id)? "selected": ""}>${c.categoria}</option>
                	</c:forEach>
				</select>
			</div>
		</div>
		<c:if test="${errores!=null && errores.containsKey('categoria') }">
		<div style="color: red;"> ${errores.categoria}</div>
		</c:if>
		

		<div>
			<div>
				<input type="submit" value="${producto.id !=null && producto.id >0 ? "Editar" : "Crear"}">
			</div>
		</div>
		
		<input type="hidden" name="id" value="${producto.id }">
	</form>


</body>
</html>