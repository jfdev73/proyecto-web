<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="org.miranda.webapp.headers.models.*"%>
<%
Carro carro = (Carro) session.getAttribute("carro");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carro de Compras</title>
</head>
<body>
	<h1>Carro de compras</h1>

	<%
	if (carro == null || carro.getItems().isEmpty()) {
	%>
	<p>Lo sentimos no hay productos en el carro de compras !</p>
	<%
	} else {
	%>
	<form name="formcarro"
		action="<%=request.getContextPath()%>/carro/actualizar" method="post">
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
			<%
			for (ItemCarro item : carro.getItems()) {
			%>
			<tbody>
				<tr>
					<td><%=item.getProducto().getId()%></td>
					<td><%=item.getProducto().getNombre()%></td>
					<td><%=item.getProducto().getPrecio()%></td>
					<td><input type="number" size="4"
							name="cant_<%=item.getProducto().getId()%>"
							value="<%=item.getCantidad()%>" /></td>
					<td><%=item.getImporte()%></td>
					<td><input type="checkbox"
							value="<%=item.getProducto().getId()%>" name="deleteProductos" /></td>
				</tr>
				<%
				}
				%>
				<tr>
					<td colspan="4" style="text-align: right;">Total:</td>
					<td><%=carro.getTotal()%></td>
				</tr>
			</tbody>
		</table>
		<a href="javascript:document.formcarro.submit();">Actualizar</a>
	</form>
	<%
	}
	%>
	<p>
		<a href="<%=request.getContextPath()%>/productos">Seguir Comprando</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/home.html">Volver</a>
	</p>
</body>
</html>