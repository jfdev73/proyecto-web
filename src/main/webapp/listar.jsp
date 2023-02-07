<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, org.miranda.webapp.headers.models.*"%>
<%
List<Producto> productos = (List<Producto>) request.getAttribute("productos");
Optional<String> username = (Optional<String>) request.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Productos</title>
</head>
<body>
	<h1>Listado de productos</h1>
	<%
	if (username.isPresent()) {
	%>
	<div>
		Hola
		<%=username.get()%>, bienvenido!
	</div>
	<div>
		<p>
			<a href="<%=request.getContextPath()%>/productos/form">Crear[+]</a>
		</p>
	</div>
	<%
	}
	%>
	<table>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Categoria</th>
			<%
			if (username.isPresent()) {
			%>
			<th>Precio</th>
			<th>Agregar</th>
			<%
			}
			%>


		</tr>
		<%
		for (Producto p : productos) {
		%>
		<tr>

			<td><%=p.getId()%></td>
			<td><%=p.getNombre()%></td>
			<td><%=p.getCategoria().getCategoria()%></td>
			<%
			if (username.isPresent()) {
			%>
			<td><%=p.getPrecio()%></td>
			<td><a
				href="<%=request.getContextPath()%>/carro/agregar?id=<%=p.getId()%> ">Agregar
					al carro</a></td>
			<td><a
				href="<%=request.getContextPath()%>/productos/form?id=<%=p.getId()%> ">Editar</a></td>
				<td><a onclick="return confirm('esta seguro que desea eliminar?');"
        href="<%=request.getContextPath()%>/productos/eliminar?id=<%=p.getId()%>">eliminar</a></td>
			<%
			}
			%>
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>