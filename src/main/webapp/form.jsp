<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<jsp:include page="layout/header.jsp" />

	<h1>${producto.id !=null && producto.id >0 ? "Editar Producto": "Crear Producto"}</h1>

	<form action="${pageContext.request.contextPath }/productos/form"
		method="post">
		
		<div class="row mb-2">
			<label for="nombre" class="col-form-label col-sm-2">Nombre:</label>
			<div class="col-sm-4">
				<input type="text" name="nombre" id="nombre" value="${producto.nombre }" class="form-control">
			</div>

		</div>

		<c:if test="${errores!=null && errores.containsKey('nombre') }">
		<div style="color: red;"> ${errores.nombre}</div>
		</c:if>

		<div class="row">
			<label for="precio" class="col-form-label col-sm-2">Precio:</label>
			<div class="col-sm-4">
				<input type="number" name="precio" id="precio" value="${producto.precio }" class="form-control">
			</div>

		</div>
		
		<c:if test="${errores!=null && errores.containsKey('precio') }">
		<div style="color: red;"> ${errores.precio}</div>
		</c:if>

		<div class="row mb-2">
			<label for="sku" class="col-form-label col-sm-2">Sku:</label>
			<div class="col-sm-4">
				<input type="text" name="sku" id="sku" value="${producto.sku }" class="form-control">
			</div>

		</div>
		
		<c:if test="${errores!=null && errores.containsKey('sku') }">
		<div style="color: red;"> ${errores.sku}</div>
		</c:if>
		

		<div class="row mb-2">
			<label for="sku" class="col-form-label col-sm-2">Categoria:</label>
			<div class="col-sm-4">
				<select name="categoria" id="categoria" class="form-select">
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
		

		<div class="row mb-2">
			<div>
				<input class="btn btn-primary" type="submit" value="${producto.id !=null && producto.id >0 ? "Editar" : "Crear"}">
			</div>
		</div>
		
		 <input type="hidden" name="id" value="${producto.id }">
	</form>

<jsp:include page="layout/footer.jsp" />