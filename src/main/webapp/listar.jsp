<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<jsp:include page="layout/header.jsp" />

	
	<h1>${title }</h1>
	<c:if test="${username.isPresent()}">
	<div class="alert alert-info">
		Hola
		<c:out 	value="${username.get()}" />, bienvenido!
	</div>
	<div>
		
		<a class="btn btn-primary my-3" href="<c:out value="${pageContext.request.contextPath }" />/productos/form">Crear[+]</a>
		
	</div>
	</c:if>
	<table class="table table-hover table-striped">
		<tr class="text-center">
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
		<tr class="text-center">

			<td>${p.id }</td>
			<td>${p.nombre }</td>
			<td>${p.categoria.categoria }</td>
			<c:if test="${username.present}">
			<td>${p.precio }</td>
			<td><a class="btn btn-sm btn-primary"
				href="${pageContext.request.contextPath }/carro/agregar?id=${p.id }">Agregar
					al carro</a></td>
			<td><a class="btn btn-sm btn-success"
				href="${pageContext.request.contextPath }/productos/form?id=${p.id } ">Editar</a></td>
				<td><a class="btn btn-sm btn-danger" onclick="return confirm('esta seguro que desea eliminar?');"
        href="${pageContext.request.contextPath }/productos/eliminar?id=<c:out value="${p.id }" />">eliminar</a></td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
	<jsp:include page="layout/footer.jsp" />
	