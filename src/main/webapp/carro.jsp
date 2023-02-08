<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<jsp:include page="layout/header.jsp" />

	<h1>Carro de compras</h1>

	<c:if test="${sessionScope.carro == null || sessionScope.carro.items.isEmpty()}">
	<div class="alert alert-warning">Lo sentimos no hay productos en el carro de compras !</div>
	</c:if>
	
	
	<c:if test="${sessionScope.carro != null && !sessionScope.carro.items.isEmpty()}">
	
	<form name="formcarro"
		action="${pageContext.request.contextPath }/carro/actualizar" method="post">
		<table class="table table-hover table-striped">
			<thead class="text-center">
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
			<tbody class="text-center">
				<tr>
					<td>${ item.producto.id}</td>
					<td>${ item.producto.nombre}</td>
					<td>${ item.producto.precio}</td>
					<td><input type="number" style="width: 70px;"
							name="cant_${ item.producto.id}"
							value="${ item.cantidad}" /></td>
					<td>${ item.importe}</td>
					<td><input type="checkbox"
							value="${ item.producto.id}" name="deleteProductos" /></td>
				</tr>
				</c:forEach>
				<tr>
					<td colspan="5" style="text-align: right;">Total:</td>
					<td>${sessionScope.carro.total}</td>
				</tr>
			</tbody>
		</table>
		<div> <a class="btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a></div>
	</form>
	</c:if>
	<div class="mt-2">
		<a  class="btn btn-sm btn-secondary"href="<%=request.getContextPath()%>/index.jsp">Volver</a>
		<a  class="btn btn-sm btn-success" href="<%=request.getContextPath()%>/productos">Seguir Comprando</a>
	</div>
<jsp:include page="layout/footer.jsp" />